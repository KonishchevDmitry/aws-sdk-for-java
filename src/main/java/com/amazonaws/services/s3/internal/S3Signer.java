/*
 * Copyright 2010 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.s3.internal;

import java.security.SignatureException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SignatureVersion;
import com.amazonaws.auth.Signer;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.s3.Headers;

/**
 * Implementation of the {@linkplain Signer} interface specific to S3's signing
 * algorithm.
 */
public class S3Signer<T> implements Signer<T> {
    
    /** AWS Credentials */
    private final AWSCredentials credentials;

    /** Shared log for signing debug output */
    private static final Log log = LogFactory.getLog(S3Signer.class);
    
    /**
     * The HTTP verb (GET, PUT, HEAD, DELETE) the request to sign 
     * is using.
     * 
     * TODO: We need to know the HTTP verb in order to
     *       create the authentication signature, but we don't
     *       have easy access to it through the request object.
     *       
     *       Maybe it'd be better for the S3 signer (or all signers?)
     *       to work directly off of the HttpRequest instead of
     *       the Request object? 
     */    
    private final String httpVerb;

    /** 
     * The canonical resource path portion of the S3 string to sign.  
     * Examples: "/", "/<bucket name>/", or "/<bucket name>/<key>"
     * 
     * TODO: We don't want to hold the resource path as member data in the S3
     *       signer, but we need access to it and can't get it through the
     *       request yet.
     */
    private final String resourcePath;

    /**
     * Constructs a new S3Signer to sign requests based on the
     * AWS credentials, HTTP method and canonical S3 resource path.
     * 
     * @param credentials
     *            The AWS credentials to use to sign the request.
     * @param httpVerb
     *            The HTTP verb (GET, PUT, POST, HEAD, DELETE) the request is
     *            using.
     * @param resourcePath
     *            The canonical S3 resource path (ex: "/", "/<bucket name>/", or
     *            "/<bucket name>/<key>".
     */
    public S3Signer(AWSCredentials credentials, String httpVerb, String resourcePath) {
        this.credentials = credentials;
        this.httpVerb = httpVerb;
        this.resourcePath = resourcePath;
        
        if (resourcePath == null)
            throw new IllegalArgumentException("Parameter resourcePath is empty");
    }
    
    /* (non-Javadoc)
     * @see com.amazonaws.auth.Signer#sign(com.amazonaws.Request, com.amazonaws.auth.SignatureVersion, com.amazonaws.auth.SigningAlgorithm)
     */
    public void sign(Request<T> request, SignatureVersion version,
            SigningAlgorithm algorithm) throws SignatureException {
        
        request.addHeader(Headers.DATE, ServiceUtils.formatRfc822Date(new Date()));

        String canonicalString = RestUtils.makeS3CanonicalString(
                httpVerb, resourcePath, request, null);
        log.debug("Calculated string to sign:\n\"" + canonicalString + "\"");

        try {
            String signature = ServiceUtils.signWithHmacSha1(
                    credentials.getAWSSecretKey(), canonicalString);

            request.addHeader("Authorization", 
                    "AWS " + credentials.getAWSAccessKeyId() + ":" + signature);
        } catch (AmazonClientException e) {
            throw new SignatureException("Unable to sign request: " + e.getMessage(), e);
        }
    }

}

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
package com.amazonaws.services.s3;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.AmazonServiceException.ErrorType;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.http.HttpRequest;
import com.amazonaws.services.s3.internal.BucketNameUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.internal.CopyObjectResponseHandler;
import com.amazonaws.services.s3.internal.MD5DigestCalculatingInputStream;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.internal.RepeatableFileInputStream;
import com.amazonaws.services.s3.internal.RepeatableInputStream;
import com.amazonaws.services.s3.internal.S3ErrorResponseHandler;
import com.amazonaws.services.s3.internal.S3MetadataResponseHandler;
import com.amazonaws.services.s3.internal.S3ObjectResponseHandler;
import com.amazonaws.services.s3.internal.S3QueryStringSigner;
import com.amazonaws.services.s3.internal.S3Signer;
import com.amazonaws.services.s3.internal.S3XmlResponseHandler;
import com.amazonaws.services.s3.internal.ServiceUtils;
import com.amazonaws.services.s3.internal.XmlWriter;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketLoggingConfiguration;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteVersionRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.MultiFactorAuthentication;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.SetBucketLoggingConfigurationRequest;
import com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.model.VersionListing;
import com.amazonaws.services.s3.model.transform.AclXmlFactory;
import com.amazonaws.services.s3.model.transform.BucketConfigurationXmlFactory;
import com.amazonaws.services.s3.model.transform.Unmarshallers;
import com.amazonaws.services.s3.model.transform.XmlResponsesSaxParser.CopyObjectResultHandler;

/**
 * <p>
 * Provides the client for accessing the Amazon S3 web service.
 * </p>
 * <p>
 * For more information about Amazon S3, please see
 * <a href="http://aws.amazon.com/s3">
 * http://aws.amazon.com/s3</a>
 * </p>
 */
public class AmazonS3Client extends AmazonWebServiceClient implements AmazonS3 {

    /** Shared logger for client events */
    private static Log log = LogFactory.getLog(AmazonS3Client.class);

    /**
     * The AWS credentials (access key ID and secret key) to use when
     * authenticating with AWS services.
     */
    private AWSCredentials awsCredentials;

    /**
     * Low level client for sending requests to AWS services.
     */
    protected final HttpClient client;

    /**
     * Optional request handlers for additional request processing.
     */
    private List<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();

    /**
     * Responsible for handling error responses from all S3 service calls.
     */
    private S3ErrorResponseHandler errorResponseHandler =
        new S3ErrorResponseHandler();

    /**
     * Shared response handler for operations that don't need to unmarshall
     * anything.
     */
    private S3XmlResponseHandler<Void> voidResponseHandler =
        new S3XmlResponseHandler<Void>(null);

    /** Utilities for validating bucket names */
    private final BucketNameUtils bucketNameUtils = new BucketNameUtils();

    /** Shared factory for converting configuration objects to XML */
    private static final BucketConfigurationXmlFactory bucketConfigurationXmlFactory = new BucketConfigurationXmlFactory();


    /**
     * <p>
     * Constructs a new Amazon S3 client that will make <b>anonymous</b>
     * requests to Amazon S3.
     * </p>
     * <p>
     * Only a subset of the Amazon S3 API will work with anonymous
     * <i>(i.e. unsigned)</i> requests, but this can prove useful in some situations.
     * For example:
     * <ul>
     * 	<li>If an Amazon S3 bucket has {@link Permission#Read} permission for the
     * 	{@link GroupGrantee#AllUsers} group, anonymous clients can call
     * 	{@link #listObjects(String)} to see what objects are stored in a bucket.</li>
     * 	<li>If an object has {@link Permission#Read} permission for the
     * 	{@link GroupGrantee#AllUsers} group, anonymous clients can call
     * 	{@link #getObject(String, String)} and
     * 	{@link #getObjectMetadata(String, String)} to pull object content and
     * 	metadata.</li>
     * 	<li>If a bucket has {@link Permission#Write} permission for the
     * 	{@link GroupGrantee#AllUsers} group, anonymous clients can upload objects
     * 	to the bucket.</li>
     * </ul>
     * </p>
     */
    public AmazonS3Client() {
        this(null);
    }

    /**
     * <p>
     * Constructs a new Amazon S3 client using the specified AWS credentials to
     * access Amazon S3.
     * </p>
     *
     * @param awsCredentials
     *            The AWS credentials to use when making requests to Amazon S3
     *            with this client.
     */
    public AmazonS3Client(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    /**
     * <p>
     * Constructs a new Amazon S3 client using the specified AWS credentials and
     * client configuration to access Amazon S3.
     * </p>
     *
     * @param awsCredentials
     *            The AWS credentials to use when making requests to Amazon S3
     *            with this client.
     * @param clientConfiguration
     *            The client configuration options controlling how this client
     *            connects to Amazon S3 (ex: proxy settings, retry counts, etc).
     */
    public AmazonS3Client(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentials = awsCredentials;

        setEndpoint(Constants.S3_HOSTNAME);
        client = new HttpClient(clientConfiguration);

        requestHandlers = new HandlerChainFactory().newRequestHandlerChain(
                "/com/amazonaws/services/s3/request.handlers");
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listNextBatchOfVersions(com.amazonaws.services.s3.model.S3VersionListing)
     */
    public VersionListing listNextBatchOfVersions(VersionListing previousVersionListing)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(previousVersionListing,
            "The previous version listing parameter must be specified when listing the next batch of versions in a bucket");

        if (!previousVersionListing.isTruncated()) {
            VersionListing emptyListing = new VersionListing();
            emptyListing.setBucketName(previousVersionListing.getBucketName());
            emptyListing.setDelimiter(previousVersionListing.getDelimiter());
            emptyListing.setKeyMarker(previousVersionListing.getNextKeyMarker());
            emptyListing.setVersionIdMarker(previousVersionListing.getNextVersionIdMarker());
            emptyListing.setMaxKeys(previousVersionListing.getMaxKeys());
            emptyListing.setPrefix(previousVersionListing.getPrefix());
            emptyListing.setTruncated(false);

            return emptyListing;
        }

        return listVersions(new ListVersionsRequest(
                previousVersionListing.getBucketName(),
                previousVersionListing.getPrefix(),
                previousVersionListing.getNextKeyMarker(),
                previousVersionListing.getNextVersionIdMarker(),
                previousVersionListing.getDelimiter(),
                new Integer( previousVersionListing.getMaxKeys() ) ));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listVersions(java.lang.String, java.lang.String)
     */
    public VersionListing listVersions(String bucketName, String prefix)
            throws AmazonClientException, AmazonServiceException {
        return listVersions(new ListVersionsRequest(bucketName, prefix, null, null, null, null));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listVersions(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
     */
    public VersionListing listVersions(String bucketName, String prefix, String keyMarker, String versionIdMarker, String delimiter, Integer maxKeys)
            throws AmazonClientException, AmazonServiceException {

        ListVersionsRequest request = new ListVersionsRequest()
            .withBucketName(bucketName)
            .withPrefix(prefix)
            .withDelimiter(delimiter)
            .withKeyMarker(keyMarker)
            .withVersionIdMarker(versionIdMarker)
            .withMaxResults(maxKeys);
        return listVersions(request);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listVersions(com.amazonaws.services.s3.model.ListVersionsRequest)
     */
    public VersionListing listVersions(ListVersionsRequest listVersionsRequest)
            throws AmazonClientException, AmazonServiceException {
        String bucketName = listVersionsRequest.getBucketName();
        String prefix = listVersionsRequest.getPrefix();
        String keyMarker = listVersionsRequest.getKeyMarker();
        String versionIdMarker = listVersionsRequest.getVersionIdMarker();
        String delimiter = listVersionsRequest.getDelimiter();
        Integer maxResults = listVersionsRequest.getMaxResults();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when listing versions in a bucket");

        Request<Void> request = createRequest(bucketName, null, listVersionsRequest);
        request.addParameter("versions", null);

        if (prefix != null) request.addParameter("prefix", prefix);
        if (keyMarker != null) request.addParameter("key-marker", keyMarker);
        if (versionIdMarker != null) request.addParameter("version-id-marker", versionIdMarker);
        if (delimiter != null) request.addParameter("delimiter", delimiter);
        if (maxResults != null && maxResults.intValue() >= 0) request.addParameter("max-keys", maxResults.toString());

        signRequest(request, HttpMethodName.GET, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<VersionListing> responseHandler =
            new S3XmlResponseHandler<VersionListing>(new Unmarshallers.VersionListUnmarshaller());

        return (VersionListing)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listObjects(java.lang.String)
     */
    public ObjectListing listObjects(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        return listObjects(new ListObjectsRequest(bucketName, null, null, null, null));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listObjects(java.lang.String, java.lang.String)
     */
    public ObjectListing listObjects(String bucketName, String prefix)
            throws AmazonClientException, AmazonServiceException {
        return listObjects(new ListObjectsRequest(bucketName, prefix, null, null, null));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listObjects(com.amazonaws.services.s3.model.ListObjectsRequest)
     */
    public ObjectListing listObjects(ListObjectsRequest listObjectsRequest)
            throws AmazonClientException, AmazonServiceException {
        String bucketName = listObjectsRequest.getBucketName();
        String prefix = listObjectsRequest.getPrefix();
        String marker = listObjectsRequest.getMarker();
        String delimiter = listObjectsRequest.getDelimiter();
        Integer maxKeys = listObjectsRequest.getMaxKeys();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when listing objects in a bucket");

        Request<Void> request = createRequest(bucketName, null, listObjectsRequest);
        if (prefix != null) request.addParameter("prefix", prefix);
        if (marker != null) request.addParameter("marker", marker);
        if (delimiter != null) request.addParameter("delimiter", delimiter);
        if (maxKeys != null && maxKeys.intValue() > 0) request.addParameter("max-keys", maxKeys.toString());

        signRequest(request, HttpMethodName.GET, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<ObjectListing> responseHandler =
            new S3XmlResponseHandler<ObjectListing>(new Unmarshallers.ListObjectsUnmarshaller());

        return (ObjectListing)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listNextBatchOfObjects(com.amazonaws.services.s3.model.S3ObjectListing)
     */
    public ObjectListing listNextBatchOfObjects(ObjectListing previousObjectListing)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(previousObjectListing,
                "The previous object listing parameter must be specified when listing the next batch of objects in a bucket");

        if (!previousObjectListing.isTruncated()) {
            ObjectListing emptyListing = new ObjectListing();
            emptyListing.setBucketName(previousObjectListing.getBucketName());
            emptyListing.setDelimiter(previousObjectListing.getDelimiter());
            emptyListing.setMarker(previousObjectListing.getNextMarker());
            emptyListing.setMaxKeys(previousObjectListing.getMaxKeys());
            emptyListing.setPrefix(previousObjectListing.getPrefix());
            emptyListing.setTruncated(false);

            return emptyListing;
        }

        return listObjects(new ListObjectsRequest(
                previousObjectListing.getBucketName(),
                previousObjectListing.getPrefix(),
                previousObjectListing.getNextMarker(),
                previousObjectListing.getDelimiter(),
                new Integer( previousObjectListing.getMaxKeys() ) ));
    }


    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getS3AccountOwner()
     */
    public Owner getS3AccountOwner()
            throws AmazonClientException, AmazonServiceException {
        Request<Void> request = createRequest(null, null, null);

        signRequest(request, HttpMethodName.GET, null, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<Owner> responseHandler =
            new S3XmlResponseHandler<Owner>(new Unmarshallers.ListBucketsOwnerUnmarshaller());

        return (Owner)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#listBuckets()
     */
    public List<Bucket> listBuckets()
            throws AmazonClientException, AmazonServiceException {
        Request<Void> request = createRequest(null, null, null);

        signRequest(request, HttpMethodName.GET, null, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<List<Bucket>> responseHandler =
            new S3XmlResponseHandler<List<Bucket>>(new Unmarshallers.ListBucketsUnmarshaller());

        return (List<Bucket>)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getBucketLocation(java.lang.String)
     */
    public String getBucketLocation(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's location");

        Request<Void> request = createRequest(bucketName, null, null);
        request.addParameter("location", null);

        signRequest(request, HttpMethodName.GET, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<String> responseHandler =
            new S3XmlResponseHandler<String>(new Unmarshallers.BucketLocationUnmarshaller());

        return (String)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String)
     */
    public Bucket createBucket(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String, com.amazonaws.services.s3.model.Region)
     */
    public Bucket createBucket(String bucketName, Region region)
            throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName, region));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#createBucket(java.lang.String, java.lang.String)
     */
    public Bucket createBucket(String bucketName, String region)
            throws AmazonClientException, AmazonServiceException {
        return createBucket(new CreateBucketRequest(bucketName, region));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#createBucket(com.amazonaws.services.s3.model.CreateBucketRequest)
     */
    public Bucket createBucket(CreateBucketRequest createBucketRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(createBucketRequest, "The CreateBucketRequest parameter must be specified when creating a bucket");

        String bucketName = createBucketRequest.getBucketName();
        String region = createBucketRequest.getRegion();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when creating a bucket");

        if (bucketName != null) bucketName = bucketName.trim();
        bucketNameUtils.validateBucketName(bucketName);

        Request<Void> request = createRequest(bucketName, null, createBucketRequest);
        signRequest(request, HttpMethodName.PUT, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);

        /*
         * We can only send the CreateBucketConfiguration if we're *not*
         * creating a bucket in the US region.
         */
        if (region != null && !region.toUpperCase().equals(Region.US_Standard.toString())) {
            XmlWriter xml = new XmlWriter();
            xml.start("CreateBucketConfiguration", "xmlns", Constants.XML_NAMESPACE);
            xml.start("LocationConstraint").value(region).end();
            xml.end();

            httpRequest.setContent(new ByteArrayInputStream(xml.getBytes()));
        }

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);

        return new Bucket(bucketName);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObjectAcl(java.lang.String, java.lang.String)
     */
    public AccessControlList getObjectAcl(String bucketName, String key)
            throws AmazonClientException, AmazonServiceException {
        return getObjectAcl(bucketName, key, null);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObjectAcl(java.lang.String, java.lang.String, java.lang.String)
     */
    public AccessControlList getObjectAcl(String bucketName, String key, String versionId)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when requesting an object's ACL");

        return getAcl(bucketName, key, versionId);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setObjectAcl(java.lang.String, java.lang.String, com.amazonaws.services.s3.model.AccessControlList)
     */
    public void setObjectAcl(String bucketName, String key, AccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        setObjectAcl(bucketName, key, null, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setObjectAcl(java.lang.String, java.lang.String, com.amazonaws.services.s3.model.CannedAccessControlList)
     */
    public void setObjectAcl(String bucketName, String key, CannedAccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        setObjectAcl(bucketName, key, null, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setObjectAcl(java.lang.String, java.lang.String, java.lang.String, com.amazonaws.services.s3.model.AccessControlList)
     */
    public void setObjectAcl(String bucketName, String key, String versionId, AccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when setting an object's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting an object's ACL");

        setAcl(bucketName, key, versionId, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setObjectAcl(java.lang.String, java.lang.String, java.lang.String, com.amazonaws.services.s3.model.CannedAccessControlList)
     */
    public void setObjectAcl(String bucketName, String key, String versionId, CannedAccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting an object's ACL");
        assertParameterNotNull(key, "The key parameter must be specified when setting an object's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting an object's ACL");

        setAcl(bucketName, key, versionId, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getBucketAcl(java.lang.String)
     */
    public AccessControlList getBucketAcl(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting a bucket's ACL");

        return getAcl(bucketName, null, null);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setBucketAcl(java.lang.String, com.amazonaws.services.s3.model.AccessControlList)
     */
    public void setBucketAcl(String bucketName, AccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting a bucket's ACL");

        setAcl(bucketName, null, null, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setBucketAcl(java.lang.String, com.amazonaws.services.s3.model.CannedAccessControlList)
     */
    public void setBucketAcl(String bucketName, CannedAccessControlList acl)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when setting a bucket's ACL");
        assertParameterNotNull(acl, "The ACL parameter must be specified when setting a bucket's ACL");

        setAcl(bucketName, null, null, acl);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObjectMetadata(java.lang.String, java.lang.String)
     */
    public ObjectMetadata getObjectMetadata(String bucketName, String key)
            throws AmazonClientException, AmazonServiceException {
        return getObjectMetadata(new GetObjectMetadataRequest(bucketName, key));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObjectMetadata(com.amazonaws.services.s3.model.GetObjectMetadataRequest)
     */
    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getObjectMetadataRequest, "The GetObjectMetadataRequest parameter must be specified when requesting an object's metadata");

        String bucketName = getObjectMetadataRequest.getBucketName();
        String key = getObjectMetadataRequest.getKey();
        String versionId = getObjectMetadataRequest.getVersionId();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when requesting an object's metadata");
        assertParameterNotNull(key, "The key parameter must be specified when requesting an object's metadata");

        Request<Void> request = createRequest(bucketName, key, getObjectMetadataRequest);
        if (versionId != null) request.addParameter("versionId", versionId);

        signRequest(request, HttpMethodName.HEAD, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.HEAD);

        S3MetadataResponseHandler responseHandler = new S3MetadataResponseHandler();
        return (ObjectMetadata)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObject(java.lang.String, java.lang.String)
     */
    public S3Object getObject(String bucketName, String key)
            throws AmazonClientException, AmazonServiceException {
        return getObject(new GetObjectRequest(bucketName, key));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#doesBucketExist(java.lang.String)
     */
    public boolean doesBucketExist(String bucketName)
        throws AmazonClientException, AmazonServiceException {

        try {
            listObjects(new ListObjectsRequest(bucketName, null, null, null, 0));

            // it exists and the current account owns it
            return true;
        } catch (AmazonServiceException ase) {
            switch (ase.getStatusCode()) {
            case 403:
                /*
                 * A permissions error means the bucket exists, but is owned by
                 * another account.
                 */
                return true;
            case 404:
                return false;
            default:
                throw ase;
            }
        }
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#changeStorageClass(java.lang.String, java.lang.String, java.lang.String)
     */
    public void changeObjectStorageClass(String bucketName, String key, StorageClass newStorageClass)
        throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName,
            "The bucketName parameter must be specified when changing an object's storage class");
        assertParameterNotNull(key,
            "The key parameter must be specified when changing an object's storage class");
        assertParameterNotNull(newStorageClass,
            "The newStorageClass parameter must be specified when changing an object's storage class");

        copyObject(new CopyObjectRequest(bucketName, key, bucketName, key)
            .withStorageClass(newStorageClass.toString()));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObject(com.amazonaws.services.s3.model.GetObjectRequest)
     */
    public S3Object getObject(GetObjectRequest getObjectRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(getObjectRequest,
                "The GetObjectRequest parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getBucketName(),
                "The bucket name parameter must be specified when requesting an object");
        assertParameterNotNull(getObjectRequest.getKey(),
                "The key parameter must be specified when requesting an object");

        String bucketName = getObjectRequest.getBucketName();
        String key = getObjectRequest.getKey();

        Request<Void> request = createRequest(bucketName, key, getObjectRequest);

        if (getObjectRequest.getVersionId() != null) {
            request.addParameter("versionId", getObjectRequest.getVersionId());
        }

        // Range
        if (getObjectRequest.getRange() != null) {
            long[] range = getObjectRequest.getRange();
            request.addHeader(Headers.RANGE, "bytes=" + Long.toString(range[0]) + "-" + Long.toString(range[1]));
        }

        addDateHeader(request, Headers.GET_OBJECT_IF_MODIFIED_SINCE,
                getObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, Headers.GET_OBJECT_IF_UNMODIFIED_SINCE,
                getObjectRequest.getUnmodifiedSinceConstraint());
        addStringListHeader(request, Headers.GET_OBJECT_IF_MATCH,
                getObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, Headers.GET_OBJECT_IT_NONE_MATCH,
                getObjectRequest.getNonmatchingETagConstraints());

        signRequest(request, HttpMethodName.GET, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        try {
            S3ObjectResponseHandler responseHandler = new S3ObjectResponseHandler();
            S3Object s3Object = (S3Object)client.execute(httpRequest, responseHandler, errorResponseHandler);

            /*
             * TODO: For now, it's easiest to set there here in the client, but
             *       we could push this back into the response handler with a
             *       little more work.
             */
            s3Object.setBucketName(bucketName);
            s3Object.setKey(key);

            /*
             * TODO: It'd be nice to check the integrity of the data was received from S3,
             *       but we'd have to read off the stream and buffer the contents somewhere
             *       in order to do that.
             *
             *       We could consider adding an option for this in the future, or wrapping
             *       the InputStream in another implementation of FilterInputStream that
             *       would calculate the checksum when the user reads the data and then
             *       notify them somehow if there was a problem.
             */
            return s3Object;
        } catch (AmazonS3Exception ase) {
            /*
             * If the request failed because one of the specified constraints
             * was not met (ex: matching ETag, modified since date, etc.), then
             * return null, so that users don't have to wrap their code in
             * try/catch blocks and check for this status code if they want to
             * use constraints.
             */
            if (ase.getStatusCode() == 412 || ase.getStatusCode() == 304) {
                return null;
            }

            throw ase;
        }
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getObject(com.amazonaws.services.s3.model.GetObjectRequest, java.io.File)
     */
    public ObjectMetadata getObject(GetObjectRequest getObjectRequest, File destinationFile)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(destinationFile,
                "The destination file parameter must be specified when downloading an object directly to a file");

        S3Object s3Object = getObject(getObjectRequest);
        // getObject can return null if constraints were specified but not met
        if (s3Object == null) return null;

        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));
            byte[] buffer = new byte[1024*10];
            int bytesRead;
            while ((bytesRead = s3Object.getObjectContent().read(buffer)) > -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new AmazonClientException(
                    "Unable to store object contents to disk: " + e.getMessage(), e);
        } finally {
            try {outputStream.close();} catch (Exception e) {}
            try {s3Object.getObjectContent().close();} catch (Exception e) {}
        }

        try {
            byte[] clientSideHash = ServiceUtils.computeMD5Hash(new FileInputStream(destinationFile));
            byte[] serverSideHash = ServiceUtils.fromHex(s3Object.getObjectMetadata().getETag());

            if (!Arrays.equals(clientSideHash, serverSideHash)) {
                throw new AmazonClientException("Unable to verify integrity of data download.  " +
                        "Client calculated content hash didn't match hash calculated by Amazon S3.  " +
                        "The data stored in '" + destinationFile.getAbsolutePath() + "' may be corrupt.");
            }
        } catch (Exception e) {
            log.warn("Unable to calculate MD5 hash to validate download: " + e.getMessage(), e);
        }

        return s3Object.getObjectMetadata();
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteBucket(java.lang.String)
     */
    public void deleteBucket(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        deleteBucket(new DeleteBucketRequest(bucketName));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteBucket(com.amazonaws.services.s3.model.DeleteBucketRequest)
     */
    public void deleteBucket(DeleteBucketRequest deleteBucketRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteBucketRequest, "The DeleteBucketRequest parameter must be specified when deleting a bucket");

        String bucketName = deleteBucketRequest.getBucketName();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when deleting a bucket");

        Request<Void> request = createRequest(bucketName, null, deleteBucketRequest);

        signRequest(request, HttpMethodName.DELETE, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.DELETE);

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#putObject(java.lang.String, java.lang.String, java.io.File)
     */
    public PutObjectResult putObject(String bucketName, String key, File file)
            throws AmazonClientException, AmazonServiceException {
        return putObject(new PutObjectRequest(bucketName, key, file)
            .withMetadata(new ObjectMetadata()));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#putObject(java.lang.String, java.lang.String, java.io.InputStream, com.amazonaws.services.s3.model.S3ObjectMetadata)
     */
    public PutObjectResult putObject(String bucketName, String key, InputStream input, ObjectMetadata metadata)
            throws AmazonClientException, AmazonServiceException {
        return putObject(new PutObjectRequest(bucketName, key, input, metadata));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#putObject(com.amazonaws.services.s3.model.PutObjectRequest)
     */
    public PutObjectResult putObject(PutObjectRequest putObjectRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(putObjectRequest, "The PutObjectRequest parameter must be specified when uploading an object");

        String bucketName = putObjectRequest.getBucketName();
        String key = putObjectRequest.getKey();
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        InputStream input = putObjectRequest.getInputStream();
        if (metadata == null) metadata = new ObjectMetadata();

        assertParameterNotNull(bucketName, "The bucket name parameter must be specified when uploading an object");
        assertParameterNotNull(key, "The key parameter must be specified when uploading an object");

        // If a file is specified for upload, we need to pull some additional
        // information from it to auto-configure a few options
        if (putObjectRequest.getFile() != null) {
            File file = putObjectRequest.getFile();

            // Always set the content length, even if it's already set
            metadata.setContentLength(file.length());

            // Only set the content type if it hasn't already been set
            if (metadata.getContentType() == null) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }

            try {
                byte[] md5Hash = ServiceUtils.computeMD5Hash(new FileInputStream(file));
                metadata.setContentMD5(ServiceUtils.toBase64(md5Hash));
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Unable to calculate MD5 hash: " + e.getMessage(), e);
            }

            try {
                input = new RepeatableFileInputStream(file);
            } catch (FileNotFoundException fnfe) {
                throw new AmazonClientException("Unable to find file to upload", fnfe);
            }
        }

        Request<Void> request = createRequest(bucketName, key, putObjectRequest);

        if (putObjectRequest.getCannedAcl() != null) {
            request.addHeader(Headers.S3_CANNED_ACL, putObjectRequest.getCannedAcl().toString());
        }

        if (putObjectRequest.getStorageClass() != null) {
            request.addHeader(Headers.STORAGE_CLASS, putObjectRequest.getStorageClass());
        }

        if (metadata.getContentLength() <= 0) {
            /*
             * There's nothing we can do except for let the HTTP client buffer
             * the input stream contents if the caller doesn't tell us how much
             * data to expect in a stream since we have to explicitly tell
             * Amazon S3 how much we're sending before we start sending any of
             * it.
             */
            log.warn("No content length specified for stream data.  " +
                     "Stream contents will be buffered in memory and could result in " +
                     "out of memory errors.");
        }

        if (metadata.getContentMD5() == null) {
            /*
             * If the user hasn't set the content MD5, then we don't want to
             * buffer the whole stream in memory just to calculate it. Instead,
             * we can calculate it on the fly and validate it with the returned
             * ETag from the object upload.
             */
            try {
                input = new MD5DigestCalculatingInputStream(input);
            } catch (NoSuchAlgorithmException e) {
                log.warn("No MD5 digest algorithm available.  Unable to calculate " +
                         "checksum and verify data integrity.", e);
            }
        }

        if (metadata.getContentType() == null) {
            /*
             * Default to the "application/octet-stream" if the user hasn't
             * specified a content type.
             */
            metadata.setContentType(Mimetypes.MIMETYPE_OCTET_STREAM);
        }

        if (!input.markSupported()) {
            input = new RepeatableInputStream(input, Constants.DEFAULT_STREAM_BUFFER_SIZE);
        }

        populateRequestMetadata(request, metadata);
        signRequest(request, HttpMethodName.PUT, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);
        httpRequest.setContent(input);

        ObjectMetadata returnedMetadata = null;
        try {
            S3MetadataResponseHandler responseHandler = new S3MetadataResponseHandler();
            returnedMetadata = (ObjectMetadata)client.execute(httpRequest, responseHandler, errorResponseHandler);
        } finally {
            try {input.close();} catch (Exception e) {
                log.warn("Unable to cleanly close input stream: " + e.getMessage(), e);
            }
        }

        String contentMd5 = metadata.getContentMD5();
        if (input instanceof MD5DigestCalculatingInputStream) {
            MD5DigestCalculatingInputStream md5DigestInputStream = (MD5DigestCalculatingInputStream)input;
            contentMd5 = ServiceUtils.toBase64(md5DigestInputStream.getMd5Digest());
        }

        if (returnedMetadata != null && contentMd5 != null) {
            byte[] clientSideHash = ServiceUtils.fromBase64(contentMd5);
            byte[] serverSideHash = ServiceUtils.fromHex(returnedMetadata.getETag());

            if (!Arrays.equals(clientSideHash, serverSideHash)) {
                throw new AmazonClientException("Unable to verify integrity of data upload.  " +
                        "Client calculated content hash didn't match hash calculated by Amazon S3.  " +
                        "You may need to delete the data stored in Amazon S3.");
            }
        }

        PutObjectResult result = new PutObjectResult();
        result.setETag(returnedMetadata.getETag());
        result.setVersionId(returnedMetadata.getVersionId());
        return result;
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#copyObject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public CopyObjectResult copyObject(String sourceBucketName, String sourceKey,
                                       String destinationBucketName, String destinationKey)
            throws AmazonClientException, AmazonServiceException {
        return copyObject(new CopyObjectRequest(sourceBucketName, sourceKey,
                                                destinationBucketName, destinationKey));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#copyObject(com.amazonaws.services.s3.model.CopyObjectRequest)
     */
    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(copyObjectRequest.getSourceBucketName(),
                "The source bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getSourceKey(),
                "The source object key must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationBucketName(),
                "The destination bucket name must be specified when copying an object");
        assertParameterNotNull(copyObjectRequest.getDestinationKey(),
                "The destination object key must be specified when copying an object");

        String destinationKey = copyObjectRequest.getDestinationKey();
        String destinationBucketName = copyObjectRequest.getDestinationBucketName();

        Request<Void> request = createRequest(destinationBucketName, destinationKey, copyObjectRequest);

        populateRequestWithCopyObjectParameters(request, copyObjectRequest);
        /*
         * We can't send the Content-Length header if the user specified it,
         * otherwise it messes up the HTTP connection when the remote server
         * thinks there's more data to pull.
         */
        request.getHeaders().remove(Headers.CONTENT_LENGTH);

        signRequest(request, HttpMethodName.PUT, destinationBucketName, destinationKey);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);

        CopyObjectResultHandler copyObjectResultHandler = null;
        try {
            // TODO: Should we move some more of this logic into CopyObjectResponseHandler?
            //       For example, detecting the different failure modes?
            CopyObjectResponseHandler responseHandler = new CopyObjectResponseHandler();
            copyObjectResultHandler =
                (CopyObjectResultHandler)client.execute(httpRequest, responseHandler, errorResponseHandler);
        } catch (AmazonS3Exception ase) {
            /*
             * If the request failed because one of the specified constraints
             * was not met (ex: matching ETag, modified since date, etc.), then
             * return null, so that users don't have to wrap their code in
             * try/catch blocks and check for this status code if they want to
             * use constraints.
             */
            if (ase.getStatusCode() == Constants.FAILED_PRECONDITION_STATUS_CODE) {
               return null;
            }

            throw ase;
        }

        /*
         * CopyObject has two failure modes:
         *  1 - An HTTP error code is returned and the error is processed like any
         *      other error response.
         *  2 - An HTTP 200 OK code is returned, but the response content contains
         *      an XML error response.
         *
         * This makes it very difficult for the client runtime to cleanly detect
         * this case and handle it like any other error response.  We could
         * extend the runtime to have a more flexible/customizable definition of
         * success/error (per request), but it's probably overkill for this
         * one special case.
         */
        if (copyObjectResultHandler.getErrorCode() != null) {
            String errorCode = copyObjectResultHandler.getErrorCode();
            String errorMessage = copyObjectResultHandler.getErrorMessage();
            String requestId = copyObjectResultHandler.getErrorRequestId();
            String hostId = copyObjectResultHandler.getErrorHostId();

            AmazonS3Exception ase = new AmazonS3Exception(errorMessage);
            ase.setErrorCode(errorCode);
            ase.setErrorType(ErrorType.Service);
            ase.setRequestId(requestId);
            ase.setExtendedRequestId(hostId);
            ase.setServiceName(request.getServiceName());
            ase.setStatusCode(200);

            throw ase;
        }

        // TODO: Might be nice to create this in our custom CopyObjectResponseHandler
        CopyObjectResult copyObjectResult = new CopyObjectResult();
        copyObjectResult.setETag(copyObjectResultHandler.getETag());
        copyObjectResult.setLastModifiedDate(
                copyObjectResultHandler.getLastModified());
        copyObjectResult.setVersionId(copyObjectResultHandler.getVersionId());

        return copyObjectResult;
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteObject(java.lang.String, java.lang.String)
     */
    public void deleteObject(String bucketName, String key)
            throws AmazonClientException, AmazonServiceException {
        deleteObject(new DeleteObjectRequest(bucketName, key));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteObject(com.amazonaws.services.s3.DeleteObjectRequest)
     */
    public void deleteObject(DeleteObjectRequest deleteObjectRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteObjectRequest,
            "The delete object request must be specified when deleting an object");

        String bucketName = deleteObjectRequest.getBucketName();
        String key = deleteObjectRequest.getKey();

        assertParameterNotNull(bucketName, "The bucket name must be specified when deleting an object");
        assertParameterNotNull(key, "The key must be specified when deleting an object");

        Request<Void> request = createRequest(bucketName, key, deleteObjectRequest);
        signRequest(request, HttpMethodName.DELETE, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.DELETE);

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteObjectVersion(java.lang.String, java.lang.String, java.lang.String)
     */
    public void deleteVersion(String bucketName, String key, String versionId)
            throws AmazonClientException, AmazonServiceException {
        deleteVersion(new DeleteVersionRequest(bucketName, key, versionId));
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#deleteVersion(com.amazonaws.services.s3.model.DeleteVersionRequest)
     */
    public void deleteVersion(DeleteVersionRequest deleteVersionRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(deleteVersionRequest,
            "The delete version request object must be specified when deleting a version");

        String bucketName = deleteVersionRequest.getBucketName();
        String key = deleteVersionRequest.getKey();
        String versionId = deleteVersionRequest.getVersionId();

        assertParameterNotNull(bucketName, "The bucket name must be specified when deleting a version");
        assertParameterNotNull(key, "The key must be specified when deleting a version");
        assertParameterNotNull(versionId, "The version ID must be specified when deleting a version");

        Request<Void> request = createRequest(bucketName, key, deleteVersionRequest);
        if (versionId != null) request.addParameter("versionId", versionId);

        if (deleteVersionRequest.getMfa() != null) {
            populateRequestWithMfaDetails(request, deleteVersionRequest.getMfa());
        }

        signRequest(request, HttpMethodName.DELETE, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.DELETE);

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setBucketVersioningConfiguration(com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest)
     */
    public void setBucketVersioningConfiguration(SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest)
        throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketVersioningConfigurationRequest,
            "The SetBucketVersioningConfigurationRequest object must be specified when setting versioning configuration");

        String bucketName = setBucketVersioningConfigurationRequest.getBucketName();
        BucketVersioningConfiguration versioningConfiguration = setBucketVersioningConfigurationRequest.getVersioningConfiguration();

        assertParameterNotNull(bucketName,
            "The bucket name parameter must be specified when setting versioning configuration");
        assertParameterNotNull(versioningConfiguration,
            "The bucket versioning parameter must be specified when setting versioning configuration");
        if (versioningConfiguration.isMfaDeleteEnabled() != null) {
            assertParameterNotNull(setBucketVersioningConfigurationRequest.getMfa(),
                "The MFA parameter must be specified when changing MFA Delete status in the versioning configuration");
        }

        Request<Void> request = createRequest(bucketName, null, setBucketVersioningConfigurationRequest);
        request.addParameter("versioning", null);

        if (versioningConfiguration.isMfaDeleteEnabled() != null) {
            if (setBucketVersioningConfigurationRequest.getMfa() != null) {
                populateRequestWithMfaDetails(request, setBucketVersioningConfigurationRequest.getMfa());
            }
        }

        signRequest(request, HttpMethodName.PUT, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);

        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(versioningConfiguration);
        httpRequest.setContent(new ByteArrayInputStream(bytes));

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getBucketVersioningConfiguration(java.lang.String)
     */
    public BucketVersioningConfiguration getBucketVersioningConfiguration(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName,
                "The bucket name parameter must be specified when querying versioning configuration");

        Request<Void> request = createRequest(bucketName, null, null);
        request.addParameter("versioning", null);

        signRequest(request, HttpMethodName.GET, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<BucketVersioningConfiguration> responseHandler =
            new S3XmlResponseHandler<BucketVersioningConfiguration>(new Unmarshallers.BucketVersioningConfigurationUnmarshaller());

        return (BucketVersioningConfiguration)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#getBucketLoggingConfiguration(java.lang.String)
     */
    public BucketLoggingConfiguration getBucketLoggingConfiguration(String bucketName)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(bucketName,
                "The bucket name parameter must be specified when requesting a bucket's logging status");

        Request<Void> request = createRequest(bucketName, null, null);
        request.addParameter("logging", null);

        signRequest(request, HttpMethodName.GET, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<BucketLoggingConfiguration> responseHandler =
            new S3XmlResponseHandler<BucketLoggingConfiguration>(new Unmarshallers.BucketLoggingConfigurationnmarshaller());

        return (BucketLoggingConfiguration)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#setBucketLoggingConfiguration(com.amazonaws.services.s3.SetBucketLoggingConfigurationRequest)
     */
    public void setBucketLoggingConfiguration(SetBucketLoggingConfigurationRequest setBucketLoggingConfigurationRequest)
            throws AmazonClientException, AmazonServiceException {
        assertParameterNotNull(setBucketLoggingConfigurationRequest,
            "The set bucket logging configuration request object must be specified when enabling server access logging");

        String bucketName = setBucketLoggingConfigurationRequest.getBucketName();
        BucketLoggingConfiguration loggingConfiguration = setBucketLoggingConfigurationRequest.getLoggingConfiguration();

        assertParameterNotNull(bucketName,
            "The bucket name parameter must be specified when enabling server access logging");
        assertParameterNotNull(bucketName,
            "The logging configuration parameter must be specified when enabling server access logging");

        Request<Void> request = createRequest(bucketName, null, setBucketLoggingConfigurationRequest);
        request.addParameter("logging", null);

        signRequest(request, HttpMethodName.PUT, bucketName, null);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);

        byte[] bytes = bucketConfigurationXmlFactory.convertToXmlByteArray(loggingConfiguration);
        httpRequest.setContent(new ByteArrayInputStream(bytes));

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /* (non-Javadoc)
     * @see com.amazonaws.services.s3.AmazonS3#generatePresignedUrl(java.lang.String, java.lang.String, java.util.Date)
     */
    public URL generatePresignedUrl(String bucketName, String key, Date expiration)
            throws AmazonClientException {
        assertParameterNotNull(bucketName,
                "The bucket name parameter must be specified when generating a pre-signed URL");
        assertParameterNotNull(expiration,
                "The expiration parameter must be specified when generating a pre-signed URL");

        Request<Void> request = createRequest(bucketName, key, null);
        presignRequest(request, HttpMethodName.GET, bucketName, key, expiration, null);

        return ServiceUtils.convertRequestToUrl(request);
    }


    /*
     * Private Interface
     */

    /**
     * Asserts that the specified parameter value is not null and if it is,
     * throws an IllegalArgumentException with the specified error message.
     *
     * @param parameterValue
     *            The parameter value being checked.
     * @param errorMessage
     *            The error message to include in the IllegalArgumentException
     *            if the specified parameter is null.
     */
    private void assertParameterNotNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Returns the S3 ACL for the specified resource (bucket if only the
     * bucketName parameter is specified, otherwise the object with the
     * specified key in the bucket).
     *
     * @param bucketName
     *            The name of the bucket whose ACL should be returned if the key
     *            parameter is not specified, otherwise the bucket containing
     *            the specified key.
     * @param key
     *            The object key whose ACL should be retrieve. If not specified,
     *            the bucket's ACL will be returned.
     * @param versionId
     *            The version ID of the object version whose ACL is being
     *            retrieved.
     *
     * @return The S3 ACL for the specified resource.
     */
    private AccessControlList getAcl(String bucketName, String key, String versionId) {
        Request<Void> request = createRequest(bucketName, key, null);
        request.addParameter("acl", null);
        if (versionId != null) request.addParameter("versionId", versionId);

        signRequest(request, HttpMethodName.GET, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.GET);

        S3XmlResponseHandler<AccessControlList> responseHandler =
            new S3XmlResponseHandler<AccessControlList>(new Unmarshallers.AccessControlListUnmarshaller());

        return (AccessControlList)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }

    /**
     * Creates and initializes a new request object for the specified S3
     * resource. This method is responsible for determining the right way to
     * address resources. For example, bucket names that are not DNS addressable
     * cannot be addressed in V2, virtual host, style, and instead must use V1,
     * path style. The returned request object has the service name, endpoint
     * and resource path correctly populated. Callers can take the request, add
     * any additional headers or parameters, then sign and execute the request.
     *
     * @param bucketName
     *            An optional parameter indicating the name of the bucket
     *            containing the resource involved in the request.
     * @param key
     *            An optional parameter indicating the key under which the
     *            desired resource is stored in the specified bucket.
     * @param originalRequest
     *            The original request, as created by the user.
     *
     * @return A new request object, populated with endpoint, resource path, and
     *         service name, ready for callers to populate any additional
     *         headers or parameters, and execute.
     */
    private Request<Void> createRequest(String bucketName, String key, AmazonWebServiceRequest originalRequest) {
        Request<Void> request = new DefaultRequest<Void>(originalRequest, Constants.S3_SERVICE_NAME);
        if (bucketNameUtils.isValidV2BucketName(bucketName)) {
            request.setEndpoint(convertToVirtualHostEndpoint(bucketName));
            request.setResourcePath(ServiceUtils.urlEncode(key));
        } else {
            request.setEndpoint(endpoint);

            if (bucketName != null) {
                /*
                 * We don't URL encode the bucket name, since it shouldn't
                 * contain any characters that need to be encoded based on
                 * Amazon S3's naming restrictions.
                 */
                request.setResourcePath(bucketName + "/"
                        + (key != null ? ServiceUtils.urlEncode(key) : ""));
            }
        }

        return request;
    }

    /**
     * Sets the Canned ACL for the specified resource in S3. If only bucketName
     * is specified, the canned ACL will be applied to the bucket, otherwise if
     * bucketName and key are specified, the canned ACL will be applied to the
     * object.
     *
     * @param bucketName
     *            The name of the bucket containing the specified key, or if no
     *            key is listed, the bucket whose ACL will be set.
     * @param key
     *            The optional object key within the specified bucket whose ACL
     *            will be set. If not specified, the bucket ACL will be set.
     * @param versionId
     *            The version ID of the object version whose ACL is being set.
     * @param cannedAcl
     *            The canned ACL to apply to the resource.
     */
    private void setAcl(String bucketName, String key, String versionId, CannedAccessControlList cannedAcl) {
        Request<Void> request = createRequest(bucketName, key, null);
        request.addParameter("acl", null);
        request.addHeader(Headers.S3_CANNED_ACL, cannedAcl.toString());
        if (versionId != null) request.addParameter("versionId", versionId);

        signRequest(request, HttpMethodName.PUT, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /**
     * Sets the ACL for the specified resource in S3. If only bucketName is
     * specified, the ACL will be applied to the bucket, otherwise if bucketName
     * and key are specified, the ACL will be applied to the object.
     *
     * @param bucketName
     *            The name of the bucket containing the specified key, or if no
     *            key is listed, the bucket whose ACL will be set.
     * @param key
     *            The optional object key within the specified bucket whose ACL
     *            will be set. If not specified, the bucket ACL will be set.
     * @param versionId
     *            The version ID of the object version whose ACL is being set.
     * @param acl
     *            The ACL to apply to the resource.
     */
    private void setAcl(String bucketName, String key, String versionId, AccessControlList acl) {
        Request<Void> request = createRequest(bucketName, key, null);
        request.addParameter("acl", null);
        if (versionId != null) request.addParameter("versionId", versionId);

        byte[] aclAsXml = new AclXmlFactory().convertToXmlByteArray(acl);
        request.addHeader("Content-Type", "text/plain");
        request.addHeader("Content-Length", String.valueOf(aclAsXml.length));

        signRequest(request, HttpMethodName.PUT, bucketName, key);
        HttpRequest httpRequest = convertToHttpRequest(request, HttpMethodName.PUT);
        httpRequest.setContent(new ByteArrayInputStream(aclAsXml));

        client.execute(httpRequest, voidResponseHandler, errorResponseHandler);
    }

    /**
     * Signs the specified request.
     *
     * @param request
     *            The request to sign.
     * @param methodName
     *            The HTTP method (GET, PUT, DELETE, HEAD) for the specified
     *            request.
     * @param bucketName
     *            The name of the bucket involved in the request. If the request
     *            is not an operation on a bucket this parameter should be null.
     * @param key
     *            The object key involved in the request. If the request is not
     *            an operation on an object, this parameter should be null.
     */
    protected <T> void signRequest(Request<T> request, HttpMethodName methodName, String bucketName, String key) {
        // Run any additional request handlers if necessary
        if (requestHandlers != null) {
            for (RequestHandler requestHandler : requestHandlers) {
                request = requestHandler.handleRequest(request);
            }
        }

        // Nothing to sign if the client is anonymous and we have no credentials
        if (awsCredentials == null) return;

        /*
         * The string we sign needs to include the exact headers that we
         * send with the request, but the client runtime layer adds the
         * Content-Type header before the request is sent if one isn't set, so
         * we have to set something here otherwise the request will fail.
         */
        if (request.getHeaders().get("Content-Type") == null) {
            request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        }
        try {
            String resourcePath =
                "/" +
                ((bucketName != null) ? bucketName + "/" : "") +
                ((key != null) ? ServiceUtils.urlEncode(key) : "");
            new S3Signer<T>(awsCredentials, methodName.toString(), resourcePath).sign(request, null, null);
        } catch (SignatureException e) {
            throw new AmazonClientException("Unable to sign request: " + e.getMessage(), e);
        }
    }

    /**
     * Pre-signs the specified request, using a signature query-string
     * parameter.
     *
     * @param request
     *            The request to sign.
     * @param methodName
     *            The HTTP method (GET, PUT, DELETE, HEAD) for the specified
     *            request.
     * @param bucketName
     *            The name of the bucket involved in the request. If the request
     *            is not an operation on a bucket this parameter should be null.
     * @param key
     *            The object key involved in the request. If the request is not
     *            an operation on an object, this parameter should be null.
     * @param expiration
     *            The time at which the signed request is no longer valid, and
     *            will stop working.
     * @param subResource
     *            The optional sub-resource being requested as part of the
     *            request (ex: "location", "acl", "logging", or "torrent".
     */
    private <T> void presignRequest(Request<T> request, HttpMethodName methodName,
            String bucketName, String key, Date expiration, String subResource) {
        try {
            String resourcePath =
                "/" +
                ((bucketName != null) ? bucketName + "/" : "") +
                ((key != null) ? ServiceUtils.urlEncode(key) : "") +
                ((subResource != null) ? "?" + subResource : "");
            new S3QueryStringSigner<T>(awsCredentials, methodName.toString(), resourcePath, expiration).sign(request);
        } catch (SignatureException e) {
            throw new AmazonClientException("Unable to sign request: " + e.getMessage(), e);
        }
    }

    /**
     * Converts the current endpoint set for this client into virtual addressing
     * style, by placing the name of the specified bucket before the S3 service
     * endpoint.
     *
     * @param bucketName
     *            The name of the bucket to use in the virtual addressing style
     *            of the returned URI.
     *
     * @return A new URI, creating from the current service endpoint URI and the
     *         specified bucket.
     */
    private URI convertToVirtualHostEndpoint(String bucketName) {
        try {
            return new URI(endpoint.getScheme() + "://" + bucketName + "." + endpoint.getAuthority());
        } catch (URISyntaxException e) {
            throw new AmazonClientException("Can't turn bucket name into a URI: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Request<T> object into an HttpRequest object. Copies all the
     * headers, parameters, etc. from the Request into the new HttpRequest.
     *
     * @param request
     *            The request to convert.
     * @param methodName
     *            The HTTP method (GET, PUT, DELETE, HEAD) to use in the
     *            converted HttpRequest object.
     *
     * @return A new HttpRequest object created from the details of the
     *         specified Request<T> object.
     */
    protected <T> HttpRequest convertToHttpRequest(Request<T> request, HttpMethodName methodName) {
        HttpRequest httpRequest = new HttpRequest(methodName);
        for (Entry<String, String> parameter : request.getParameters().entrySet()) {
            httpRequest.addParameter(parameter.getKey(), parameter.getValue());
        }

        for (Entry<String, String> parameter : request.getHeaders().entrySet()) {
            httpRequest.addHeader(parameter.getKey(), parameter.getValue());
        }

        httpRequest.setServiceName(request.getServiceName());
        httpRequest.setEndpoint(request.getEndpoint());
        httpRequest.setResourcePath(request.getResourcePath());

        return httpRequest;
    }

    /**
     * Populates the specified request object with the appropriate headers from
     * the S3ObjectMetadata object.
     *
     * @param request
     *            The request to populate with headers.
     * @param metadata
     *            The metadata containing the header information to include in
     *            the request.
     */
    private static void populateRequestMetadata(Request<Void> request, ObjectMetadata metadata) {
        Map<String, Object> rawMetadata = metadata.getRawMetadata();
        if (rawMetadata != null) {
            for (Entry<String, Object> entry : rawMetadata.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        Map<String, String> userMetadata = metadata.getUserMetadata();
        if (userMetadata != null) {
            for (Entry<String, String> entry : userMetadata.entrySet()) {
                request.addHeader(Headers.S3_USER_METADATA_PREFIX + entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Populates the specified request with the specified Multi-Factor
     * Authentication details, including the MFA header (with device serial
     * number and generated token) as well as configuring the request object to
     * use HTTPS instead of HTTP, since all requests which include the MFA
     * header must be sent over HTTPS.
     *
     * @param request
     *            The request to populate.
     * @param mfa
     *            The Multi-Factor Authentication information.
     */
    private void populateRequestWithMfaDetails(Request<Void> request, MultiFactorAuthentication mfa) {
        if (mfa == null) return;

        String endpoint = request.getEndpoint().toString();
        if (endpoint.startsWith("http://")) {
            String httpsEndpoint = endpoint.replace("http://", "https://");
            request.setEndpoint(URI.create(httpsEndpoint));
            log.info("Overriding current endpoint to use HTTPS " +
                    "as required by S3 for requests containing an MFA header");
        }

        request.addHeader(Headers.S3_MFA,
                mfa.getDeviceSerialNumber() + " " + mfa.getToken());
    }

    /**
     * Populates the specified request with the numerous options available in
     * CopyObjectRequest.
     *
     * @param request
     *            The request to populate with headers to represent all the
     *            options expressed in the CopyObjectRequest object.
     * @param copyObjectRequest
     *            The object containing all the options for copying an object in
     *            S3.
     */
    private static void populateRequestWithCopyObjectParameters(Request<Void> request, CopyObjectRequest copyObjectRequest) {
        String copySourceHeader =
             "/" + ServiceUtils.urlEncode(copyObjectRequest.getSourceBucketName())
           + "/" + ServiceUtils.urlEncode(copyObjectRequest.getSourceKey());
        if (copyObjectRequest.getSourceVersionId() != null) {
            copySourceHeader += "?versionId=" + copyObjectRequest.getSourceVersionId();
        }
        request.addHeader("x-amz-copy-source", copySourceHeader);

        addDateHeader(request, Headers.COPY_SOURCE_IF_MODIFIED_SINCE,
                copyObjectRequest.getModifiedSinceConstraint());
        addDateHeader(request, Headers.COPY_SOURCE_IF_UNMODIFIED_SINCE,
                copyObjectRequest.getUnmodifiedSinceConstraint());

        addStringListHeader(request, Headers.COPY_SOURCE_IF_MATCH,
                copyObjectRequest.getMatchingETagConstraints());
        addStringListHeader(request, Headers.COPY_SOURCE_IF_NO_MATCH,
                copyObjectRequest.getNonmatchingETagConstraints());

        if (copyObjectRequest.getCannedAccessControlList() != null) {
            request.addHeader(Headers.S3_CANNED_ACL,
                    copyObjectRequest.getCannedAccessControlList().toString());
        }

        if (copyObjectRequest.getStorageClass() != null) {
            request.addHeader(Headers.STORAGE_CLASS, copyObjectRequest.getStorageClass());
        }

        ObjectMetadata newObjectMetadata = copyObjectRequest.getNewObjectMetadata();
        if (newObjectMetadata != null) {
            request.addHeader(Headers.METADATA_DIRECTIVE, "REPLACE");
            populateRequestMetadata(request, newObjectMetadata);
        }
    }

    /**
     * Adds the specified date header (in RFC 822 date format) to the specified
     * request, if the specified value is not null.
     *
     * @param request
     *            The request to add the header to.
     * @param header
     *            The header name.
     * @param value
     *            The header value.
     */
    private static void addDateHeader(Request<?> request, String header, Date value) {
        if (value != null) {
            request.addHeader(header, ServiceUtils.formatRfc822Date(value));
        }
    }

    /**
     * Adds the specified string list header (joined together separated with
     * commas) to the specified request, if the specified value is not null or
     * empty.
     *
     * @param request
     *            The request to add the header to.
     * @param header
     *            The header name.
     * @param values
     *            The list of strings to join together for the header value.
     */
    private static void addStringListHeader(Request<?> request, String header, List<String> values) {
        if (values != null && !values.isEmpty()) {
            request.addHeader(header, ServiceUtils.join(values));
        }
    }

}

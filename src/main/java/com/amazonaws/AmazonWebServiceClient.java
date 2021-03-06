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
package com.amazonaws;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Abstract base class for Amazon Web Service Java clients.
 * <p>
 * Responsible for basic client capabilities that are the same across all AWS
 * SDK Java clients (ex: setting the client endpoint).
 */
public abstract class AmazonWebServiceClient {

    /**
     * The service endpoint to which this client will send requests.
     */
    protected URI endpoint;

    /** The client configuration */
    protected final ClientConfiguration clientConfiguration;

    /**
     * Constructs a new AmazonWebServiceClient object using the specified
     * configuration.
     *
     * @param clientConfiguration
     *            The client configuration for this client.
     */
    public AmazonWebServiceClient(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    /**
     * Overrides the default endpoint for this client. Callers can use this
     * method to control which AWS region they want to work with.
     * <p>
     * Callers can pass in just the endpoint (ex: "ec2.amazonaws.com") or a full
     * URL, including the protocol (ex: "https://ec2.amazonaws.com"). If the
     * protocol is not specified here, the default protocol from this client's
     * {@link ClientConfiguration} will be used, which by default is HTTPS.
     *
     * @param endpoint
     *            The endpoint (ex: "ec2.amazonaws.com") or a full URL,
     *            including the protocol (ex: "https://ec2.amazonaws.com") of
     *            the region specific AWS endpoint this client will communicate
     *            with.
     *
     * @throws IllegalArgumentException
     *             If any problems are detected with the specified endpoint.
     */
    public void setEndpoint(String endpoint) throws IllegalArgumentException {
        /*
         * If the endpoint doesn't explicitly specify a protocol to use, then
         * we'll defer to the default protocol specified in the client
         * configuration.
         */
        if (endpoint.contains("://") == false) {
            endpoint = clientConfiguration.getProtocol().toString() + "://" + endpoint;
        }

        try {
            this.endpoint = new URI(endpoint);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

}

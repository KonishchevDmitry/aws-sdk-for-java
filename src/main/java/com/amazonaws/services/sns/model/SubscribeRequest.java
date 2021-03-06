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
package com.amazonaws.services.sns.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Subscribe Request
 */
public class SubscribeRequest extends AmazonWebServiceRequest {

    /**
     * The ARN of topic you want to subscribe to.
     */
    private String topicArn;

    /**
     * The protocol you want to use. Supported protocols include: <ul>
     * <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     * <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     * <li>email -- delivery of message via SMTP</li> <li>email-json --
     * delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     * JSON-encoded message to an Amazon SQS queue</li> </ul>
     */
    private String protocol;

    /**
     * The endpoint that you want to receive notifications. Endpoints vary by
     * protocol: <ul> <li>For the http protocol, the endpoint is an URL
     * beginning with "http://"</li> <li>For the https protocol, the endpoint
     * is a URL beginning with "https://"</li> <li>For the email protocol,
     * the endpoint is an e-mail address</li> <li>For the email-json
     * protocol, the endpoint is an e-mail address</li> <li>For the sqs
     * protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     */
    private String endpoint;

    /**
     * Default constructor for a new SubscribeRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public SubscribeRequest() {}
    
    /**
     * Constructs a new SubscribeRequest object and initializes the specified
     * object members.  Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param topicArn The ARN of topic you want to subscribe to.
     * @param protocol The protocol you want to use. Supported protocols
     * include: <ul> <li>http -- delivery of JSON-encoded message via HTTP
     * POST</li> <li>https -- delivery of JSON-encoded message via HTTPS
     * POST</li> <li>email -- delivery of message via SMTP</li>
     * <li>email-json -- delivery of JSON-encoded message via SMTP</li>
     * <li>sqs -- delivery of JSON-encoded message to an Amazon SQS
     * queue</li> </ul>
     * @param endpoint The endpoint that you want to receive notifications.
     * Endpoints vary by protocol: <ul> <li>For the http protocol, the
     * endpoint is an URL beginning with "http://"</li> <li>For the https
     * protocol, the endpoint is a URL beginning with "https://"</li> <li>For
     * the email protocol, the endpoint is an e-mail address</li> <li>For the
     * email-json protocol, the endpoint is an e-mail address</li> <li>For
     * the sqs protocol, the endpoint is the ARN of an Amazon SQS queue</li>
     * </ul>
     */
    public SubscribeRequest(String topicArn, String protocol, String endpoint) {
        this.topicArn = topicArn;
        this.protocol = protocol;
        this.endpoint = endpoint;
    }
    
    /**
     * The ARN of topic you want to subscribe to.
     *
     * @return The ARN of topic you want to subscribe to.
     */
    public String getTopicArn() {
        return topicArn;
    }
    
    /**
     * The ARN of topic you want to subscribe to.
     *
     * @param topicArn The ARN of topic you want to subscribe to.
     */
    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }
    
    /**
     * The ARN of topic you want to subscribe to.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param topicArn The ARN of topic you want to subscribe to.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SubscribeRequest withTopicArn(String topicArn) {
        this.topicArn = topicArn;
        return this;
    }
    
    
    /**
     * The protocol you want to use. Supported protocols include: <ul>
     * <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     * <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     * <li>email -- delivery of message via SMTP</li> <li>email-json --
     * delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     * JSON-encoded message to an Amazon SQS queue</li> </ul>
     *
     * @return The protocol you want to use. Supported protocols include: <ul>
     *         <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     *         <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     *         <li>email -- delivery of message via SMTP</li> <li>email-json --
     *         delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     *         JSON-encoded message to an Amazon SQS queue</li> </ul>
     */
    public String getProtocol() {
        return protocol;
    }
    
    /**
     * The protocol you want to use. Supported protocols include: <ul>
     * <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     * <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     * <li>email -- delivery of message via SMTP</li> <li>email-json --
     * delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     * JSON-encoded message to an Amazon SQS queue</li> </ul>
     *
     * @param protocol The protocol you want to use. Supported protocols include: <ul>
     *         <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     *         <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     *         <li>email -- delivery of message via SMTP</li> <li>email-json --
     *         delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     *         JSON-encoded message to an Amazon SQS queue</li> </ul>
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    /**
     * The protocol you want to use. Supported protocols include: <ul>
     * <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     * <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     * <li>email -- delivery of message via SMTP</li> <li>email-json --
     * delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     * JSON-encoded message to an Amazon SQS queue</li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param protocol The protocol you want to use. Supported protocols include: <ul>
     *         <li>http -- delivery of JSON-encoded message via HTTP POST</li>
     *         <li>https -- delivery of JSON-encoded message via HTTPS POST</li>
     *         <li>email -- delivery of message via SMTP</li> <li>email-json --
     *         delivery of JSON-encoded message via SMTP</li> <li>sqs -- delivery of
     *         JSON-encoded message to an Amazon SQS queue</li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SubscribeRequest withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }
    
    
    /**
     * The endpoint that you want to receive notifications. Endpoints vary by
     * protocol: <ul> <li>For the http protocol, the endpoint is an URL
     * beginning with "http://"</li> <li>For the https protocol, the endpoint
     * is a URL beginning with "https://"</li> <li>For the email protocol,
     * the endpoint is an e-mail address</li> <li>For the email-json
     * protocol, the endpoint is an e-mail address</li> <li>For the sqs
     * protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     *
     * @return The endpoint that you want to receive notifications. Endpoints vary by
     *         protocol: <ul> <li>For the http protocol, the endpoint is an URL
     *         beginning with "http://"</li> <li>For the https protocol, the endpoint
     *         is a URL beginning with "https://"</li> <li>For the email protocol,
     *         the endpoint is an e-mail address</li> <li>For the email-json
     *         protocol, the endpoint is an e-mail address</li> <li>For the sqs
     *         protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     */
    public String getEndpoint() {
        return endpoint;
    }
    
    /**
     * The endpoint that you want to receive notifications. Endpoints vary by
     * protocol: <ul> <li>For the http protocol, the endpoint is an URL
     * beginning with "http://"</li> <li>For the https protocol, the endpoint
     * is a URL beginning with "https://"</li> <li>For the email protocol,
     * the endpoint is an e-mail address</li> <li>For the email-json
     * protocol, the endpoint is an e-mail address</li> <li>For the sqs
     * protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     *
     * @param endpoint The endpoint that you want to receive notifications. Endpoints vary by
     *         protocol: <ul> <li>For the http protocol, the endpoint is an URL
     *         beginning with "http://"</li> <li>For the https protocol, the endpoint
     *         is a URL beginning with "https://"</li> <li>For the email protocol,
     *         the endpoint is an e-mail address</li> <li>For the email-json
     *         protocol, the endpoint is an e-mail address</li> <li>For the sqs
     *         protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    
    /**
     * The endpoint that you want to receive notifications. Endpoints vary by
     * protocol: <ul> <li>For the http protocol, the endpoint is an URL
     * beginning with "http://"</li> <li>For the https protocol, the endpoint
     * is a URL beginning with "https://"</li> <li>For the email protocol,
     * the endpoint is an e-mail address</li> <li>For the email-json
     * protocol, the endpoint is an e-mail address</li> <li>For the sqs
     * protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param endpoint The endpoint that you want to receive notifications. Endpoints vary by
     *         protocol: <ul> <li>For the http protocol, the endpoint is an URL
     *         beginning with "http://"</li> <li>For the https protocol, the endpoint
     *         is a URL beginning with "https://"</li> <li>For the email protocol,
     *         the endpoint is an e-mail address</li> <li>For the email-json
     *         protocol, the endpoint is an e-mail address</li> <li>For the sqs
     *         protocol, the endpoint is the ARN of an Amazon SQS queue</li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SubscribeRequest withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("TopicArn: " + topicArn + ", ");
        sb.append("Protocol: " + protocol + ", ");
        sb.append("Endpoint: " + endpoint + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
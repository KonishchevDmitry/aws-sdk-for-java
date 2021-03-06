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
package com.amazonaws.services.elasticloadbalancing;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.security.SignatureException;

import com.amazonaws.*;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.QueryStringSigner;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.http.DefaultResponseHandler;
import com.amazonaws.http.DefaultErrorResponseHandler;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.http.HttpRequest;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.VoidUnmarshaller;
import com.amazonaws.transform.StandardErrorUnmarshaller;

import com.amazonaws.services.elasticloadbalancing.model.*;
import com.amazonaws.services.elasticloadbalancing.model.transform.*;


/**
 * Client for accessing AmazonElasticLoadBalancing.  All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * 
 */
public class AmazonElasticLoadBalancingClient extends AmazonWebServiceClient implements AmazonElasticLoadBalancing {

    /**
     * The AWS credentials (access key ID and secret key) to use when
     * authenticating with AWS services.
     */
    private AWSCredentials awsCredentials;

    /**
     * List of exception unmarshallers for all AmazonElasticLoadBalancing exceptions.
     */
    protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers;

    /**
     * Low level client for sending requests to AWS services.
     */
    protected final HttpClient client;

    /**
     * Optional request handlers for additional request processing.
     */
    private List<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();


    /**
     * Constructs a new client to invoke service methods on
     * AmazonElasticLoadBalancing using the specified AWS account credentials.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     */
    public AmazonElasticLoadBalancingClient(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonElasticLoadBalancing using the specified AWS account credentials
     * and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonElasticLoadBalancing
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonElasticLoadBalancingClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentials = awsCredentials;

        exceptionUnmarshallers = new ArrayList<Unmarshaller<AmazonServiceException, Node>>();
        exceptionUnmarshallers.add(new ListenerNotFoundExceptionUnmarshaller());
        exceptionUnmarshallers.add(new DuplicatePolicyNameExceptionUnmarshaller());
        exceptionUnmarshallers.add(new InvalidInstanceExceptionUnmarshaller());
        exceptionUnmarshallers.add(new LoadBalancerNotFoundExceptionUnmarshaller());
        exceptionUnmarshallers.add(new InvalidConfigurationRequestExceptionUnmarshaller());
        exceptionUnmarshallers.add(new PolicyNotFoundExceptionUnmarshaller());
        exceptionUnmarshallers.add(new TooManyPoliciesExceptionUnmarshaller());
        exceptionUnmarshallers.add(new TooManyLoadBalancersExceptionUnmarshaller());
        exceptionUnmarshallers.add(new DuplicateLoadBalancerNameExceptionUnmarshaller());
        
        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        setEndpoint("elasticloadbalancing.amazonaws.com");

        requestHandlers = new HandlerChainFactory().newRequestHandlerChain(
                "/com/amazonaws/services/elasticloadbalancing/request.handlers");
        client = new HttpClient(clientConfiguration);
    }

    
    /**
     *
     * @param setLoadBalancerPoliciesOfListenerRequest Container for the
     *           necessary parameters to execute the SetLoadBalancerPoliciesOfListener
     *           service method on AmazonElasticLoadBalancing.
     * 
     * @return The response from the SetLoadBalancerPoliciesOfListener
     *         service method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws ListenerNotFoundException
     * @throws PolicyNotFoundException
     * @throws InvalidConfigurationRequestException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public SetLoadBalancerPoliciesOfListenerResult setLoadBalancerPoliciesOfListener(SetLoadBalancerPoliciesOfListenerRequest setLoadBalancerPoliciesOfListenerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<SetLoadBalancerPoliciesOfListenerRequest> request = new SetLoadBalancerPoliciesOfListenerRequestMarshaller().marshall(setLoadBalancerPoliciesOfListenerRequest);
        return invoke(request, "//SetLoadBalancerPoliciesOfListenerResult", new SetLoadBalancerPoliciesOfListenerResultUnmarshaller());
    }
    
    /**
     *
     * @param describeLoadBalancersRequest Container for the necessary
     *           parameters to execute the DescribeLoadBalancers service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the DescribeLoadBalancers service method, as
     *         returned by AmazonElasticLoadBalancing.
     * 
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLoadBalancersResult describeLoadBalancers(DescribeLoadBalancersRequest describeLoadBalancersRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeLoadBalancersRequest> request = new DescribeLoadBalancersRequestMarshaller().marshall(describeLoadBalancersRequest);
        return invoke(request, "//DescribeLoadBalancersResult", new DescribeLoadBalancersResultUnmarshaller());
    }
    
    /**
     *
     * @param createAppCookieStickinessPolicyRequest Container for the
     *           necessary parameters to execute the CreateAppCookieStickinessPolicy
     *           service method on AmazonElasticLoadBalancing.
     * 
     * @return The response from the CreateAppCookieStickinessPolicy service
     *         method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidConfigurationRequestException
     * @throws DuplicatePolicyNameException
     * @throws LoadBalancerNotFoundException
     * @throws TooManyPoliciesException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public CreateAppCookieStickinessPolicyResult createAppCookieStickinessPolicy(CreateAppCookieStickinessPolicyRequest createAppCookieStickinessPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<CreateAppCookieStickinessPolicyRequest> request = new CreateAppCookieStickinessPolicyRequestMarshaller().marshall(createAppCookieStickinessPolicyRequest);
        return invoke(request, "//CreateAppCookieStickinessPolicyResult", new CreateAppCookieStickinessPolicyResultUnmarshaller());
    }
    
    /**
     *
     * @param disableAvailabilityZonesForLoadBalancerRequest Container for
     *           the necessary parameters to execute the
     *           DisableAvailabilityZonesForLoadBalancer service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the DisableAvailabilityZonesForLoadBalancer
     *         service method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidConfigurationRequestException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DisableAvailabilityZonesForLoadBalancerResult disableAvailabilityZonesForLoadBalancer(DisableAvailabilityZonesForLoadBalancerRequest disableAvailabilityZonesForLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DisableAvailabilityZonesForLoadBalancerRequest> request = new DisableAvailabilityZonesForLoadBalancerRequestMarshaller().marshall(disableAvailabilityZonesForLoadBalancerRequest);
        return invoke(request, "//DisableAvailabilityZonesForLoadBalancerResult", new DisableAvailabilityZonesForLoadBalancerResultUnmarshaller());
    }
    
    /**
     *
     * @param describeInstanceHealthRequest Container for the necessary
     *           parameters to execute the DescribeInstanceHealth service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the DescribeInstanceHealth service method,
     *         as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidInstanceException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeInstanceHealthResult describeInstanceHealth(DescribeInstanceHealthRequest describeInstanceHealthRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeInstanceHealthRequest> request = new DescribeInstanceHealthRequestMarshaller().marshall(describeInstanceHealthRequest);
        return invoke(request, "//DescribeInstanceHealthResult", new DescribeInstanceHealthResultUnmarshaller());
    }
    
    /**
     *
     * @param createLoadBalancerRequest Container for the necessary
     *           parameters to execute the CreateLoadBalancer service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the CreateLoadBalancer service method, as
     *         returned by AmazonElasticLoadBalancing.
     * 
     * @throws TooManyLoadBalancersException
     * @throws DuplicateLoadBalancerNameException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public CreateLoadBalancerResult createLoadBalancer(CreateLoadBalancerRequest createLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<CreateLoadBalancerRequest> request = new CreateLoadBalancerRequestMarshaller().marshall(createLoadBalancerRequest);
        return invoke(request, "//CreateLoadBalancerResult", new CreateLoadBalancerResultUnmarshaller());
    }
    
    /**
     *
     * @param registerInstancesWithLoadBalancerRequest Container for the
     *           necessary parameters to execute the RegisterInstancesWithLoadBalancer
     *           service method on AmazonElasticLoadBalancing.
     * 
     * @return The response from the RegisterInstancesWithLoadBalancer
     *         service method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidInstanceException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public RegisterInstancesWithLoadBalancerResult registerInstancesWithLoadBalancer(RegisterInstancesWithLoadBalancerRequest registerInstancesWithLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<RegisterInstancesWithLoadBalancerRequest> request = new RegisterInstancesWithLoadBalancerRequestMarshaller().marshall(registerInstancesWithLoadBalancerRequest);
        return invoke(request, "//RegisterInstancesWithLoadBalancerResult", new RegisterInstancesWithLoadBalancerResultUnmarshaller());
    }
    
    /**
     *
     * @param deregisterInstancesFromLoadBalancerRequest Container for the
     *           necessary parameters to execute the
     *           DeregisterInstancesFromLoadBalancer service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the DeregisterInstancesFromLoadBalancer
     *         service method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidInstanceException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeregisterInstancesFromLoadBalancerResult deregisterInstancesFromLoadBalancer(DeregisterInstancesFromLoadBalancerRequest deregisterInstancesFromLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeregisterInstancesFromLoadBalancerRequest> request = new DeregisterInstancesFromLoadBalancerRequestMarshaller().marshall(deregisterInstancesFromLoadBalancerRequest);
        return invoke(request, "//DeregisterInstancesFromLoadBalancerResult", new DeregisterInstancesFromLoadBalancerResultUnmarshaller());
    }
    
    /**
     *
     * @param deleteLoadBalancerRequest Container for the necessary
     *           parameters to execute the DeleteLoadBalancer service method on
     *           AmazonElasticLoadBalancing.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteLoadBalancer(DeleteLoadBalancerRequest deleteLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteLoadBalancerRequest> request = new DeleteLoadBalancerRequestMarshaller().marshall(deleteLoadBalancerRequest);
        invoke(request, null, null);
    }
    
    /**
     *
     * @param configureHealthCheckRequest Container for the necessary
     *           parameters to execute the ConfigureHealthCheck service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the ConfigureHealthCheck service method, as
     *         returned by AmazonElasticLoadBalancing.
     * 
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public ConfigureHealthCheckResult configureHealthCheck(ConfigureHealthCheckRequest configureHealthCheckRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ConfigureHealthCheckRequest> request = new ConfigureHealthCheckRequestMarshaller().marshall(configureHealthCheckRequest);
        return invoke(request, "//ConfigureHealthCheckResult", new ConfigureHealthCheckResultUnmarshaller());
    }
    
    /**
     *
     * @param enableAvailabilityZonesForLoadBalancerRequest Container for the
     *           necessary parameters to execute the
     *           EnableAvailabilityZonesForLoadBalancer service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the EnableAvailabilityZonesForLoadBalancer
     *         service method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public EnableAvailabilityZonesForLoadBalancerResult enableAvailabilityZonesForLoadBalancer(EnableAvailabilityZonesForLoadBalancerRequest enableAvailabilityZonesForLoadBalancerRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<EnableAvailabilityZonesForLoadBalancerRequest> request = new EnableAvailabilityZonesForLoadBalancerRequestMarshaller().marshall(enableAvailabilityZonesForLoadBalancerRequest);
        return invoke(request, "//EnableAvailabilityZonesForLoadBalancerResult", new EnableAvailabilityZonesForLoadBalancerResultUnmarshaller());
    }
    
    /**
     *
     * @param createLBCookieStickinessPolicyRequest Container for the
     *           necessary parameters to execute the CreateLBCookieStickinessPolicy
     *           service method on AmazonElasticLoadBalancing.
     * 
     * @return The response from the CreateLBCookieStickinessPolicy service
     *         method, as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidConfigurationRequestException
     * @throws DuplicatePolicyNameException
     * @throws LoadBalancerNotFoundException
     * @throws TooManyPoliciesException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public CreateLBCookieStickinessPolicyResult createLBCookieStickinessPolicy(CreateLBCookieStickinessPolicyRequest createLBCookieStickinessPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<CreateLBCookieStickinessPolicyRequest> request = new CreateLBCookieStickinessPolicyRequestMarshaller().marshall(createLBCookieStickinessPolicyRequest);
        return invoke(request, "//CreateLBCookieStickinessPolicyResult", new CreateLBCookieStickinessPolicyResultUnmarshaller());
    }
    
    /**
     *
     * @param deleteLoadBalancerPolicyRequest Container for the necessary
     *           parameters to execute the DeleteLoadBalancerPolicy service method on
     *           AmazonElasticLoadBalancing.
     * 
     * @return The response from the DeleteLoadBalancerPolicy service method,
     *         as returned by AmazonElasticLoadBalancing.
     * 
     * @throws InvalidConfigurationRequestException
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteLoadBalancerPolicyResult deleteLoadBalancerPolicy(DeleteLoadBalancerPolicyRequest deleteLoadBalancerPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteLoadBalancerPolicyRequest> request = new DeleteLoadBalancerPolicyRequestMarshaller().marshall(deleteLoadBalancerPolicyRequest);
        return invoke(request, "//DeleteLoadBalancerPolicyResult", new DeleteLoadBalancerPolicyResultUnmarshaller());
    }
    
    /**
     * 
     * @return The response from the DescribeLoadBalancers service method, as
     *         returned by AmazonElasticLoadBalancing.
     * 
     * @throws LoadBalancerNotFoundException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticLoadBalancing indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLoadBalancersResult describeLoadBalancers() throws AmazonServiceException, AmazonClientException {
        return describeLoadBalancers(new DescribeLoadBalancersRequest());
    }
    

    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, String responseElement, Unmarshaller<X, Node> unmarshaller) {
        request.setEndpoint(endpoint);
        for (Entry<String, String> entry : request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

        // Apply any additional service specific request handlers that need to be run
        if (requestHandlers != null) {
            for (RequestHandler requestHandler : requestHandlers) {
                request = requestHandler.handleRequest(request);
            }
        }

        QueryStringSigner<Y> signer = new QueryStringSigner<Y>(awsCredentials);
        try {
            signer.sign(request);
        } catch (SignatureException e) {
            throw new AmazonServiceException("Unable to sign request", e);
        }

        HttpRequest httpRequest = new HttpRequest(HttpMethodName.POST);
        for (Entry<String, String> parameter : request.getParameters().entrySet()) {
            httpRequest.addParameter(parameter.getKey(), parameter.getValue());
        }
        httpRequest.setServiceName(request.getServiceName());
        httpRequest.setEndpoint(request.getEndpoint());
        httpRequest.setResourcePath(request.getResourcePath());

        
        DefaultResponseHandler<X> responseHandler = new DefaultResponseHandler<X>(unmarshaller, responseElement);
        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

        return (X)client.execute(httpRequest, responseHandler, errorResponseHandler);
    }
}
        
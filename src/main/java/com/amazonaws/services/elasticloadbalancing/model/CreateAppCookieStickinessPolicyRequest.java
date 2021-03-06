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
package com.amazonaws.services.elasticloadbalancing.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Create App Cookie Stickiness Policy Request
 */
public class CreateAppCookieStickinessPolicyRequest extends AmazonWebServiceRequest {

    private String loadBalancerName;

    private String policyName;

    private String cookieName;

    /**
     * Returns the value of the LoadBalancerName property for this object.
     *
     * @return The value of the LoadBalancerName property for this object.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * Sets the value of the LoadBalancerName property for this object.
     *
     * @param loadBalancerName The new value for the LoadBalancerName property for this object.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * Sets the value of the LoadBalancerName property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The new value for the LoadBalancerName property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateAppCookieStickinessPolicyRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * Returns the value of the PolicyName property for this object.
     *
     * @return The value of the PolicyName property for this object.
     */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
     * Sets the value of the PolicyName property for this object.
     *
     * @param policyName The new value for the PolicyName property for this object.
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
     * Sets the value of the PolicyName property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyName The new value for the PolicyName property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateAppCookieStickinessPolicyRequest withPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }
    
    
    /**
     * Returns the value of the CookieName property for this object.
     *
     * @return The value of the CookieName property for this object.
     */
    public String getCookieName() {
        return cookieName;
    }
    
    /**
     * Sets the value of the CookieName property for this object.
     *
     * @param cookieName The new value for the CookieName property for this object.
     */
    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }
    
    /**
     * Sets the value of the CookieName property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param cookieName The new value for the CookieName property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateAppCookieStickinessPolicyRequest withCookieName(String cookieName) {
        this.cookieName = cookieName;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("LoadBalancerName: " + loadBalancerName + ", ");
        sb.append("PolicyName: " + policyName + ", ");
        sb.append("CookieName: " + cookieName + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
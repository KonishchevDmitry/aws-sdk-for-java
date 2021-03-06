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
package com.amazonaws.services.ec2.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * <p>
 * A request to describe the bundle tasks for the user's account.
 * </p>
 */
public class DescribeBundleTasksRequest extends AmazonWebServiceRequest {

    /**
     * The list of bundle task IDs to describe.
     */
    private java.util.List<String> bundleIds;

    /**
     * The list of bundle task IDs to describe.
     *
     * @return The list of bundle task IDs to describe.
     */
    public java.util.List<String> getBundleIds() {
        if (bundleIds == null) {
            bundleIds = new java.util.ArrayList<String>();
        }
        return bundleIds;
    }
    
    /**
     * The list of bundle task IDs to describe.
     *
     * @param bundleIds The list of bundle task IDs to describe.
     */
    public void setBundleIds(java.util.Collection<String> bundleIds) {
        java.util.List<String> bundleIdsCopy = new java.util.ArrayList<String>();
        if (bundleIds != null) {
            bundleIdsCopy.addAll(bundleIds);
        }
        this.bundleIds = bundleIdsCopy;
    }
    
    /**
     * The list of bundle task IDs to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bundleIds The list of bundle task IDs to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeBundleTasksRequest withBundleIds(String... bundleIds) {
        for (String value : bundleIds) {
            getBundleIds().add(value);
        }
        return this;
    }
    
    /**
     * The list of bundle task IDs to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bundleIds The list of bundle task IDs to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeBundleTasksRequest withBundleIds(java.util.Collection<String> bundleIds) {
        java.util.List<String> bundleIdsCopy = new java.util.ArrayList<String>();
        if (bundleIds != null) {
            bundleIdsCopy.addAll(bundleIds);
        }
        this.bundleIds = bundleIdsCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("BundleIds: " + bundleIds + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
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
 * 
 * </p>
 */
public class DeleteSubnetRequest extends AmazonWebServiceRequest {

    /**
     * 
     */
    private String subnetId;

    /**
     * 
     *
     * @return 
     */
    public String getSubnetId() {
        return subnetId;
    }
    
    /**
     * 
     *
     * @param subnetId 
     */
    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }
    
    /**
     * 
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param subnetId 
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteSubnetRequest withSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("SubnetId: " + subnetId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
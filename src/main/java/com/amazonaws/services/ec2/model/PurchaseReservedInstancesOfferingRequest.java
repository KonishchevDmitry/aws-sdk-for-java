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
 * Purchases a Reserved Instance for use with your account.
 * </p>
 * <p>
 * With Amazon EC2 Reserved Instances, you purchase the right to launch
 * Amazon EC2 instances for a period of time (without getting
 * insufficient capacity errors) and pay a lower usage rate for the
 * actual time used.
 * </p>
 */
public class PurchaseReservedInstancesOfferingRequest extends AmazonWebServiceRequest {

    /**
     * The unique ID of the Reserved Instances offering being purchased.
     */
    private String reservedInstancesOfferingId;

    /**
     * The number of Reserved Instances to purchase.
     */
    private Integer instanceCount;

    /**
     * The unique ID of the Reserved Instances offering being purchased.
     *
     * @return The unique ID of the Reserved Instances offering being purchased.
     */
    public String getReservedInstancesOfferingId() {
        return reservedInstancesOfferingId;
    }
    
    /**
     * The unique ID of the Reserved Instances offering being purchased.
     *
     * @param reservedInstancesOfferingId The unique ID of the Reserved Instances offering being purchased.
     */
    public void setReservedInstancesOfferingId(String reservedInstancesOfferingId) {
        this.reservedInstancesOfferingId = reservedInstancesOfferingId;
    }
    
    /**
     * The unique ID of the Reserved Instances offering being purchased.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param reservedInstancesOfferingId The unique ID of the Reserved Instances offering being purchased.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PurchaseReservedInstancesOfferingRequest withReservedInstancesOfferingId(String reservedInstancesOfferingId) {
        this.reservedInstancesOfferingId = reservedInstancesOfferingId;
        return this;
    }
    
    
    /**
     * The number of Reserved Instances to purchase.
     *
     * @return The number of Reserved Instances to purchase.
     */
    public Integer getInstanceCount() {
        return instanceCount;
    }
    
    /**
     * The number of Reserved Instances to purchase.
     *
     * @param instanceCount The number of Reserved Instances to purchase.
     */
    public void setInstanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
    }
    
    /**
     * The number of Reserved Instances to purchase.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceCount The number of Reserved Instances to purchase.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PurchaseReservedInstancesOfferingRequest withInstanceCount(Integer instanceCount) {
        this.instanceCount = instanceCount;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("ReservedInstancesOfferingId: " + reservedInstancesOfferingId + ", ");
        sb.append("InstanceCount: " + instanceCount + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
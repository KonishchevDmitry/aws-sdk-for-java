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
 * Request to describe the Reserved Instance offerings that are
 * available for purchase.
 * </p>
 * <p>
 * With Amazon EC2 Reserved Instances, you purchase the right to launch
 * Amazon EC2 instances for a period of time (without getting
 * insufficient capacity errors) and pay a lower usage rate for the
 * actual time used.
 * </p>
 */
public class DescribeReservedInstancesOfferingsRequest extends AmazonWebServiceRequest {

    /**
     * An optional list of the unique IDs of the Reserved Instance offerings
     * to describe.
     */
    private java.util.List<String> reservedInstancesOfferingIds;

    /**
     * The instance type on which the Reserved Instance can be used.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge
     */
    private String instanceType;

    /**
     * The Availability Zone in which the Reserved Instance can be used.
     */
    private String availabilityZone;

    /**
     * The Reserved Instance product description.
     */
    private String productDescription;

    /**
     * An optional list of the unique IDs of the Reserved Instance offerings
     * to describe.
     *
     * @return An optional list of the unique IDs of the Reserved Instance offerings
     *         to describe.
     */
    public java.util.List<String> getReservedInstancesOfferingIds() {
        if (reservedInstancesOfferingIds == null) {
            reservedInstancesOfferingIds = new java.util.ArrayList<String>();
        }
        return reservedInstancesOfferingIds;
    }
    
    /**
     * An optional list of the unique IDs of the Reserved Instance offerings
     * to describe.
     *
     * @param reservedInstancesOfferingIds An optional list of the unique IDs of the Reserved Instance offerings
     *         to describe.
     */
    public void setReservedInstancesOfferingIds(java.util.Collection<String> reservedInstancesOfferingIds) {
        java.util.List<String> reservedInstancesOfferingIdsCopy = new java.util.ArrayList<String>();
        if (reservedInstancesOfferingIds != null) {
            reservedInstancesOfferingIdsCopy.addAll(reservedInstancesOfferingIds);
        }
        this.reservedInstancesOfferingIds = reservedInstancesOfferingIdsCopy;
    }
    
    /**
     * An optional list of the unique IDs of the Reserved Instance offerings
     * to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param reservedInstancesOfferingIds An optional list of the unique IDs of the Reserved Instance offerings
     *         to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeReservedInstancesOfferingsRequest withReservedInstancesOfferingIds(String... reservedInstancesOfferingIds) {
        for (String value : reservedInstancesOfferingIds) {
            getReservedInstancesOfferingIds().add(value);
        }
        return this;
    }
    
    /**
     * An optional list of the unique IDs of the Reserved Instance offerings
     * to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param reservedInstancesOfferingIds An optional list of the unique IDs of the Reserved Instance offerings
     *         to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeReservedInstancesOfferingsRequest withReservedInstancesOfferingIds(java.util.Collection<String> reservedInstancesOfferingIds) {
        java.util.List<String> reservedInstancesOfferingIdsCopy = new java.util.ArrayList<String>();
        if (reservedInstancesOfferingIds != null) {
            reservedInstancesOfferingIdsCopy.addAll(reservedInstancesOfferingIds);
        }
        this.reservedInstancesOfferingIds = reservedInstancesOfferingIdsCopy;

        return this;
    }
    
    /**
     * The instance type on which the Reserved Instance can be used.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge
     *
     * @return The instance type on which the Reserved Instance can be used.
     *
     * @see InstanceType
     */
    public String getInstanceType() {
        return instanceType;
    }
    
    /**
     * The instance type on which the Reserved Instance can be used.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge
     *
     * @param instanceType The instance type on which the Reserved Instance can be used.
     *
     * @see InstanceType
     */
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }
    
    /**
     * The instance type on which the Reserved Instance can be used.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge
     *
     * @param instanceType The instance type on which the Reserved Instance can be used.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see InstanceType
     */
    public DescribeReservedInstancesOfferingsRequest withInstanceType(String instanceType) {
        this.instanceType = instanceType;
        return this;
    }
    
    
    /**
     * The Availability Zone in which the Reserved Instance can be used.
     *
     * @return The Availability Zone in which the Reserved Instance can be used.
     */
    public String getAvailabilityZone() {
        return availabilityZone;
    }
    
    /**
     * The Availability Zone in which the Reserved Instance can be used.
     *
     * @param availabilityZone The Availability Zone in which the Reserved Instance can be used.
     */
    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }
    
    /**
     * The Availability Zone in which the Reserved Instance can be used.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZone The Availability Zone in which the Reserved Instance can be used.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeReservedInstancesOfferingsRequest withAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
        return this;
    }
    
    
    /**
     * The Reserved Instance product description.
     *
     * @return The Reserved Instance product description.
     */
    public String getProductDescription() {
        return productDescription;
    }
    
    /**
     * The Reserved Instance product description.
     *
     * @param productDescription The Reserved Instance product description.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    /**
     * The Reserved Instance product description.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param productDescription The Reserved Instance product description.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeReservedInstancesOfferingsRequest withProductDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("ReservedInstancesOfferingIds: " + reservedInstancesOfferingIds + ", ");
        sb.append("InstanceType: " + instanceType + ", ");
        sb.append("AvailabilityZone: " + availabilityZone + ", ");
        sb.append("ProductDescription: " + productDescription + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
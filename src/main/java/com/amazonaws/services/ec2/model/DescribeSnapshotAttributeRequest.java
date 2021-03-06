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
 * A request to describe an attribute of an EBS snapshot. Only one
 * attribute can be specified per request.
 * </p>
 */
public class DescribeSnapshotAttributeRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the EBS snapshot whose attribute is being described.
     */
    private String snapshotId;

    /**
     * The name of the EBS attribute to describe. <p> Available attribute
     * names: createVolumePermission
     */
    private String attribute;

    /**
     * The ID of the EBS snapshot whose attribute is being described.
     *
     * @return The ID of the EBS snapshot whose attribute is being described.
     */
    public String getSnapshotId() {
        return snapshotId;
    }
    
    /**
     * The ID of the EBS snapshot whose attribute is being described.
     *
     * @param snapshotId The ID of the EBS snapshot whose attribute is being described.
     */
    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }
    
    /**
     * The ID of the EBS snapshot whose attribute is being described.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param snapshotId The ID of the EBS snapshot whose attribute is being described.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeSnapshotAttributeRequest withSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }
    
    
    /**
     * The name of the EBS attribute to describe. <p> Available attribute
     * names: createVolumePermission
     *
     * @return The name of the EBS attribute to describe. <p> Available attribute
     *         names: createVolumePermission
     */
    public String getAttribute() {
        return attribute;
    }
    
    /**
     * The name of the EBS attribute to describe. <p> Available attribute
     * names: createVolumePermission
     *
     * @param attribute The name of the EBS attribute to describe. <p> Available attribute
     *         names: createVolumePermission
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    /**
     * The name of the EBS attribute to describe. <p> Available attribute
     * names: createVolumePermission
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attribute The name of the EBS attribute to describe. <p> Available attribute
     *         names: createVolumePermission
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeSnapshotAttributeRequest withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("SnapshotId: " + snapshotId + ", ");
        sb.append("Attribute: " + attribute + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
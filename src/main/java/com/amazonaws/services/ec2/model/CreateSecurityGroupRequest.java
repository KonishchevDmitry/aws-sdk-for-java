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
 * Represents a request to create a new EC2 security group.
 * </p>
 */
public class CreateSecurityGroupRequest extends AmazonWebServiceRequest {

    /**
     * Name of the security group.
     */
    private String groupName;

    /**
     * Description of the group. This is informational only.
     */
    private String description;

    /**
     * Name of the security group.
     *
     * @return Name of the security group.
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * Name of the security group.
     *
     * @param groupName Name of the security group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * Name of the security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupName Name of the security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateSecurityGroupRequest withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    
    
    /**
     * Description of the group. This is informational only.
     *
     * @return Description of the group. This is informational only.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Description of the group. This is informational only.
     *
     * @param description Description of the group. This is informational only.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Description of the group. This is informational only.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param description Description of the group. This is informational only.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateSecurityGroupRequest withDescription(String description) {
        this.description = description;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("GroupName: " + groupName + ", ");
        sb.append("Description: " + description + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
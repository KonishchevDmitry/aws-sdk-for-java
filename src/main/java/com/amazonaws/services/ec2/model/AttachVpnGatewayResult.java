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

/**
 * <p>
 * 
 * </p>
 */
public class AttachVpnGatewayResult {

    /**
     * 
     */
    private VpcAttachment vpcAttachement;

    /**
     * 
     *
     * @return 
     */
    public VpcAttachment getVpcAttachement() {
        return vpcAttachement;
    }
    
    /**
     * 
     *
     * @param vpcAttachement 
     */
    public void setVpcAttachement(VpcAttachment vpcAttachement) {
        this.vpcAttachement = vpcAttachement;
    }
    
    /**
     * 
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param vpcAttachement 
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AttachVpnGatewayResult withVpcAttachement(VpcAttachment vpcAttachement) {
        this.vpcAttachement = vpcAttachement;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("VpcAttachement: " + vpcAttachement + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
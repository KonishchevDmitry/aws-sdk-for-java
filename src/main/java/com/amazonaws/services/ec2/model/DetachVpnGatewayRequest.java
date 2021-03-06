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
public class DetachVpnGatewayRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the VPN gateway to detach from the VPC.
     */
    private String vpnGatewayId;

    /**
     * The ID of the VPC to detach the VPN gateway from.
     */
    private String vpcId;

    /**
     * The ID of the VPN gateway to detach from the VPC.
     *
     * @return The ID of the VPN gateway to detach from the VPC.
     */
    public String getVpnGatewayId() {
        return vpnGatewayId;
    }
    
    /**
     * The ID of the VPN gateway to detach from the VPC.
     *
     * @param vpnGatewayId The ID of the VPN gateway to detach from the VPC.
     */
    public void setVpnGatewayId(String vpnGatewayId) {
        this.vpnGatewayId = vpnGatewayId;
    }
    
    /**
     * The ID of the VPN gateway to detach from the VPC.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param vpnGatewayId The ID of the VPN gateway to detach from the VPC.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DetachVpnGatewayRequest withVpnGatewayId(String vpnGatewayId) {
        this.vpnGatewayId = vpnGatewayId;
        return this;
    }
    
    
    /**
     * The ID of the VPC to detach the VPN gateway from.
     *
     * @return The ID of the VPC to detach the VPN gateway from.
     */
    public String getVpcId() {
        return vpcId;
    }
    
    /**
     * The ID of the VPC to detach the VPN gateway from.
     *
     * @param vpcId The ID of the VPC to detach the VPN gateway from.
     */
    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }
    
    /**
     * The ID of the VPC to detach the VPN gateway from.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param vpcId The ID of the VPC to detach the VPN gateway from.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DetachVpnGatewayRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("VpnGatewayId: " + vpnGatewayId + ", ");
        sb.append("VpcId: " + vpcId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
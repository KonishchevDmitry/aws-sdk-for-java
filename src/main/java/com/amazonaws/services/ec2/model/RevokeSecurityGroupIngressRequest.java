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
 * A request to revoke permissions from a security group. The
 * permissions used to revoke must be specified using the same values
 * used to grant the permissions.
 * </p>
 * <p>
 * Permissions are specified by IP protocol (TCP, UDP, or ICMP), the
 * source of the request (by IP range or an Amazon EC2 user-group pair),
 * the source and destination port ranges (for TCP and UDP), and the ICMP
 * codes and types (for ICMP).
 * </p>
 * <p>
 * Permission changes are quickly propagated to instances within the
 * security group. However, depending on the number of instances in the
 * group, a small delay might occur.
 * </p>
 */
public class RevokeSecurityGroupIngressRequest extends AmazonWebServiceRequest {

    /**
     * The name of the security group from which to remove permissions.
     */
    private String groupName;

    /**
     * Name of the source security group. Cannot be used when specifying a
     * CIDR IP address.
     */
    private String sourceSecurityGroupName;

    /**
     * AWS user ID of an account. Cannot be used when specifying a CIDR IP
     * address.
     */
    private String sourceSecurityGroupOwnerId;

    /**
     * IP protocol. <p> Valid values: tcp, udp, icmp
     */
    private String ipProtocol;

    /**
     * Start of port range for the TCP and UDP protocols, or an ICMP type
     * number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     * type number).
     */
    private Integer fromPort;

    /**
     * End of port range for the TCP and UDP protocols, or an ICMP code. An
     * ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     */
    private Integer toPort;

    /**
     * CIDR range.
     */
    private String cidrIp;

    /**
     * The name of the security group from which to remove permissions.
     *
     * @return The name of the security group from which to remove permissions.
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * The name of the security group from which to remove permissions.
     *
     * @param groupName The name of the security group from which to remove permissions.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of the security group from which to remove permissions.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupName The name of the security group from which to remove permissions.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    
    
    /**
     * Name of the source security group. Cannot be used when specifying a
     * CIDR IP address.
     *
     * @return Name of the source security group. Cannot be used when specifying a
     *         CIDR IP address.
     */
    public String getSourceSecurityGroupName() {
        return sourceSecurityGroupName;
    }
    
    /**
     * Name of the source security group. Cannot be used when specifying a
     * CIDR IP address.
     *
     * @param sourceSecurityGroupName Name of the source security group. Cannot be used when specifying a
     *         CIDR IP address.
     */
    public void setSourceSecurityGroupName(String sourceSecurityGroupName) {
        this.sourceSecurityGroupName = sourceSecurityGroupName;
    }
    
    /**
     * Name of the source security group. Cannot be used when specifying a
     * CIDR IP address.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param sourceSecurityGroupName Name of the source security group. Cannot be used when specifying a
     *         CIDR IP address.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withSourceSecurityGroupName(String sourceSecurityGroupName) {
        this.sourceSecurityGroupName = sourceSecurityGroupName;
        return this;
    }
    
    
    /**
     * AWS user ID of an account. Cannot be used when specifying a CIDR IP
     * address.
     *
     * @return AWS user ID of an account. Cannot be used when specifying a CIDR IP
     *         address.
     */
    public String getSourceSecurityGroupOwnerId() {
        return sourceSecurityGroupOwnerId;
    }
    
    /**
     * AWS user ID of an account. Cannot be used when specifying a CIDR IP
     * address.
     *
     * @param sourceSecurityGroupOwnerId AWS user ID of an account. Cannot be used when specifying a CIDR IP
     *         address.
     */
    public void setSourceSecurityGroupOwnerId(String sourceSecurityGroupOwnerId) {
        this.sourceSecurityGroupOwnerId = sourceSecurityGroupOwnerId;
    }
    
    /**
     * AWS user ID of an account. Cannot be used when specifying a CIDR IP
     * address.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param sourceSecurityGroupOwnerId AWS user ID of an account. Cannot be used when specifying a CIDR IP
     *         address.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withSourceSecurityGroupOwnerId(String sourceSecurityGroupOwnerId) {
        this.sourceSecurityGroupOwnerId = sourceSecurityGroupOwnerId;
        return this;
    }
    
    
    /**
     * IP protocol. <p> Valid values: tcp, udp, icmp
     *
     * @return IP protocol. <p> Valid values: tcp, udp, icmp
     */
    public String getIpProtocol() {
        return ipProtocol;
    }
    
    /**
     * IP protocol. <p> Valid values: tcp, udp, icmp
     *
     * @param ipProtocol IP protocol. <p> Valid values: tcp, udp, icmp
     */
    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol = ipProtocol;
    }
    
    /**
     * IP protocol. <p> Valid values: tcp, udp, icmp
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ipProtocol IP protocol. <p> Valid values: tcp, udp, icmp
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withIpProtocol(String ipProtocol) {
        this.ipProtocol = ipProtocol;
        return this;
    }
    
    
    /**
     * Start of port range for the TCP and UDP protocols, or an ICMP type
     * number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     * type number).
     *
     * @return Start of port range for the TCP and UDP protocols, or an ICMP type
     *         number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     *         type number).
     */
    public Integer getFromPort() {
        return fromPort;
    }
    
    /**
     * Start of port range for the TCP and UDP protocols, or an ICMP type
     * number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     * type number).
     *
     * @param fromPort Start of port range for the TCP and UDP protocols, or an ICMP type
     *         number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     *         type number).
     */
    public void setFromPort(Integer fromPort) {
        this.fromPort = fromPort;
    }
    
    /**
     * Start of port range for the TCP and UDP protocols, or an ICMP type
     * number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     * type number).
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param fromPort Start of port range for the TCP and UDP protocols, or an ICMP type
     *         number. An ICMP type number of -1 indicates a wildcard (i.e., any ICMP
     *         type number).
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withFromPort(Integer fromPort) {
        this.fromPort = fromPort;
        return this;
    }
    
    
    /**
     * End of port range for the TCP and UDP protocols, or an ICMP code. An
     * ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     *
     * @return End of port range for the TCP and UDP protocols, or an ICMP code. An
     *         ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     */
    public Integer getToPort() {
        return toPort;
    }
    
    /**
     * End of port range for the TCP and UDP protocols, or an ICMP code. An
     * ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     *
     * @param toPort End of port range for the TCP and UDP protocols, or an ICMP code. An
     *         ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     */
    public void setToPort(Integer toPort) {
        this.toPort = toPort;
    }
    
    /**
     * End of port range for the TCP and UDP protocols, or an ICMP code. An
     * ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param toPort End of port range for the TCP and UDP protocols, or an ICMP code. An
     *         ICMP code of -1 indicates a wildcard (i.e., any ICMP code).
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withToPort(Integer toPort) {
        this.toPort = toPort;
        return this;
    }
    
    
    /**
     * CIDR range.
     *
     * @return CIDR range.
     */
    public String getCidrIp() {
        return cidrIp;
    }
    
    /**
     * CIDR range.
     *
     * @param cidrIp CIDR range.
     */
    public void setCidrIp(String cidrIp) {
        this.cidrIp = cidrIp;
    }
    
    /**
     * CIDR range.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param cidrIp CIDR range.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RevokeSecurityGroupIngressRequest withCidrIp(String cidrIp) {
        this.cidrIp = cidrIp;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("GroupName: " + groupName + ", ");
        sb.append("SourceSecurityGroupName: " + sourceSecurityGroupName + ", ");
        sb.append("SourceSecurityGroupOwnerId: " + sourceSecurityGroupOwnerId + ", ");
        sb.append("IpProtocol: " + ipProtocol + ", ");
        sb.append("FromPort: " + fromPort + ", ");
        sb.append("ToPort: " + toPort + ", ");
        sb.append("CidrIp: " + cidrIp + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
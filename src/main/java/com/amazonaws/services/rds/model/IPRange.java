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
package com.amazonaws.services.rds.model;

/**
 * <p>
 * 
 * </p>
 */
public class IPRange {

    /**
     * Specifies the status of the IP range.
     */
    private String status;

    /**
     * Specifies the IP range.
     */
    private String cIDRIP;

    /**
     * Specifies the status of the IP range.
     *
     * @return Specifies the status of the IP range.
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Specifies the status of the IP range.
     *
     * @param status Specifies the status of the IP range.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Specifies the status of the IP range.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param status Specifies the status of the IP range.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public IPRange withStatus(String status) {
        this.status = status;
        return this;
    }
    
    
    /**
     * Specifies the IP range.
     *
     * @return Specifies the IP range.
     */
    public String getCIDRIP() {
        return cIDRIP;
    }
    
    /**
     * Specifies the IP range.
     *
     * @param cIDRIP Specifies the IP range.
     */
    public void setCIDRIP(String cIDRIP) {
        this.cIDRIP = cIDRIP;
    }
    
    /**
     * Specifies the IP range.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param cIDRIP Specifies the IP range.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public IPRange withCIDRIP(String cIDRIP) {
        this.cIDRIP = cIDRIP;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("Status: " + status + ", ");
        sb.append("CIDRIP: " + cIDRIP + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
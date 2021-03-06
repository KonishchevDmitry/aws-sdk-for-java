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
 * Describe Dhcp Options Result
 */
public class DescribeDhcpOptionsResult {

    private java.util.List<DhcpOptions> dhcpOptions;

    /**
     * Returns the value of the DhcpOptions property for this object.
     *
     * @return The value of the DhcpOptions property for this object.
     */
    public java.util.List<DhcpOptions> getDhcpOptions() {
        if (dhcpOptions == null) {
            dhcpOptions = new java.util.ArrayList<DhcpOptions>();
        }
        return dhcpOptions;
    }
    
    /**
     * Sets the value of the DhcpOptions property for this object.
     *
     * @param dhcpOptions The new value for the DhcpOptions property for this object.
     */
    public void setDhcpOptions(java.util.Collection<DhcpOptions> dhcpOptions) {
        java.util.List<DhcpOptions> dhcpOptionsCopy = new java.util.ArrayList<DhcpOptions>();
        if (dhcpOptions != null) {
            dhcpOptionsCopy.addAll(dhcpOptions);
        }
        this.dhcpOptions = dhcpOptionsCopy;
    }
    
    /**
     * Sets the value of the DhcpOptions property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dhcpOptions The new value for the DhcpOptions property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeDhcpOptionsResult withDhcpOptions(DhcpOptions... dhcpOptions) {
        for (DhcpOptions value : dhcpOptions) {
            getDhcpOptions().add(value);
        }
        return this;
    }
    
    /**
     * Sets the value of the DhcpOptions property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dhcpOptions The new value for the DhcpOptions property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeDhcpOptionsResult withDhcpOptions(java.util.Collection<DhcpOptions> dhcpOptions) {
        java.util.List<DhcpOptions> dhcpOptionsCopy = new java.util.ArrayList<DhcpOptions>();
        if (dhcpOptions != null) {
            dhcpOptionsCopy.addAll(dhcpOptions);
        }
        this.dhcpOptions = dhcpOptionsCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("DhcpOptions: " + dhcpOptions + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
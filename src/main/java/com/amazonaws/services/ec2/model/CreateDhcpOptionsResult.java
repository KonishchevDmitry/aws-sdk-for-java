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
public class CreateDhcpOptionsResult {

    /**
     * A set of one or more DHCP options.
     */
    private DhcpOptions dhcpOptions;

    /**
     * A set of one or more DHCP options.
     *
     * @return A set of one or more DHCP options.
     */
    public DhcpOptions getDhcpOptions() {
        return dhcpOptions;
    }
    
    /**
     * A set of one or more DHCP options.
     *
     * @param dhcpOptions A set of one or more DHCP options.
     */
    public void setDhcpOptions(DhcpOptions dhcpOptions) {
        this.dhcpOptions = dhcpOptions;
    }
    
    /**
     * A set of one or more DHCP options.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dhcpOptions A set of one or more DHCP options.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateDhcpOptionsResult withDhcpOptions(DhcpOptions dhcpOptions) {
        this.dhcpOptions = dhcpOptions;
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
    
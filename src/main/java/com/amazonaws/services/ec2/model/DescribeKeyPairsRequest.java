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
 * Request to describe information about key pairs available to you.
 * </p>
 * <p>
 * If you specify key pairs, information about those key pairs is
 * returned. Otherwise, information for all your registered key pairs is
 * returned.
 * </p>
 */
public class DescribeKeyPairsRequest extends AmazonWebServiceRequest {

    /**
     * The optional list of key pair names to describe.
     */
    private java.util.List<String> keyNames;

    /**
     * The optional list of key pair names to describe.
     *
     * @return The optional list of key pair names to describe.
     */
    public java.util.List<String> getKeyNames() {
        if (keyNames == null) {
            keyNames = new java.util.ArrayList<String>();
        }
        return keyNames;
    }
    
    /**
     * The optional list of key pair names to describe.
     *
     * @param keyNames The optional list of key pair names to describe.
     */
    public void setKeyNames(java.util.Collection<String> keyNames) {
        java.util.List<String> keyNamesCopy = new java.util.ArrayList<String>();
        if (keyNames != null) {
            keyNamesCopy.addAll(keyNames);
        }
        this.keyNames = keyNamesCopy;
    }
    
    /**
     * The optional list of key pair names to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param keyNames The optional list of key pair names to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeKeyPairsRequest withKeyNames(String... keyNames) {
        for (String value : keyNames) {
            getKeyNames().add(value);
        }
        return this;
    }
    
    /**
     * The optional list of key pair names to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param keyNames The optional list of key pair names to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeKeyPairsRequest withKeyNames(java.util.Collection<String> keyNames) {
        java.util.List<String> keyNamesCopy = new java.util.ArrayList<String>();
        if (keyNames != null) {
            keyNamesCopy.addAll(keyNames);
        }
        this.keyNames = keyNamesCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("KeyNames: " + keyNames + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
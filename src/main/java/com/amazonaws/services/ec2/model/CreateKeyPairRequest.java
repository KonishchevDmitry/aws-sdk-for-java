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
 * Represents a request to create a new EC2 key pair.
 * </p>
 */
public class CreateKeyPairRequest extends AmazonWebServiceRequest {

    /**
     * The unique name for the new key pair.
     */
    private String keyName;

    /**
     * The unique name for the new key pair.
     *
     * @return The unique name for the new key pair.
     */
    public String getKeyName() {
        return keyName;
    }
    
    /**
     * The unique name for the new key pair.
     *
     * @param keyName The unique name for the new key pair.
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
    
    /**
     * The unique name for the new key pair.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param keyName The unique name for the new key pair.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateKeyPairRequest withKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("KeyName: " + keyName + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
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
package com.amazonaws.services.elasticloadbalancing.model;

/**
 * Deregister Instances From Load Balancer Result
 */
public class DeregisterInstancesFromLoadBalancerResult {

    private java.util.List<Instance> instances;

    /**
     * Returns the value of the Instances property for this object.
     *
     * @return The value of the Instances property for this object.
     */
    public java.util.List<Instance> getInstances() {
        if (instances == null) {
            instances = new java.util.ArrayList<Instance>();
        }
        return instances;
    }
    
    /**
     * Sets the value of the Instances property for this object.
     *
     * @param instances The new value for the Instances property for this object.
     */
    public void setInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;
    }
    
    /**
     * Sets the value of the Instances property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances The new value for the Instances property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeregisterInstancesFromLoadBalancerResult withInstances(Instance... instances) {
        for (Instance value : instances) {
            getInstances().add(value);
        }
        return this;
    }
    
    /**
     * Sets the value of the Instances property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances The new value for the Instances property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeregisterInstancesFromLoadBalancerResult withInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("Instances: " + instances + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Disable Availability Zones For Load Balancer Request
 */
public class DisableAvailabilityZonesForLoadBalancerRequest extends AmazonWebServiceRequest {

    private String loadBalancerName;

    private java.util.List<String> availabilityZones;

    /**
     * Returns the value of the LoadBalancerName property for this object.
     *
     * @return The value of the LoadBalancerName property for this object.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * Sets the value of the LoadBalancerName property for this object.
     *
     * @param loadBalancerName The new value for the LoadBalancerName property for this object.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * Sets the value of the LoadBalancerName property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The new value for the LoadBalancerName property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DisableAvailabilityZonesForLoadBalancerRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * Returns the value of the AvailabilityZones property for this object.
     *
     * @return The value of the AvailabilityZones property for this object.
     */
    public java.util.List<String> getAvailabilityZones() {
        if (availabilityZones == null) {
            availabilityZones = new java.util.ArrayList<String>();
        }
        return availabilityZones;
    }
    
    /**
     * Sets the value of the AvailabilityZones property for this object.
     *
     * @param availabilityZones The new value for the AvailabilityZones property for this object.
     */
    public void setAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;
    }
    
    /**
     * Sets the value of the AvailabilityZones property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones The new value for the AvailabilityZones property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DisableAvailabilityZonesForLoadBalancerRequest withAvailabilityZones(String... availabilityZones) {
        for (String value : availabilityZones) {
            getAvailabilityZones().add(value);
        }
        return this;
    }
    
    /**
     * Sets the value of the AvailabilityZones property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones The new value for the AvailabilityZones property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DisableAvailabilityZonesForLoadBalancerRequest withAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("LoadBalancerName: " + loadBalancerName + ", ");
        sb.append("AvailabilityZones: " + availabilityZones + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
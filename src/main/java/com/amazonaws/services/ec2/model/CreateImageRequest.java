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
 * Represents a request to create a new EC2 image.
 * </p>
 */
public class CreateImageRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the instance from which to create the new image.
     */
    private String instanceId;

    /**
     * The name for the new AMI being created.
     */
    private String name;

    /**
     * The description for the new AMI being created.
     */
    private String description;

    /**
     * By default this property is set to <code>false</code>, which means
     * Amazon EC2 attempts to cleanly shut down the instance before image
     * creation and reboots the instance afterwards. When set to true, Amazon
     * EC2 will not shut down the instance before creating the image. When
     * this option is used, file system integrity on the created image cannot
     * be guaranteed.
     */
    private Boolean noReboot;

    /**
     * The ID of the instance from which to create the new image.
     *
     * @return The ID of the instance from which to create the new image.
     */
    public String getInstanceId() {
        return instanceId;
    }
    
    /**
     * The ID of the instance from which to create the new image.
     *
     * @param instanceId The ID of the instance from which to create the new image.
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    
    /**
     * The ID of the instance from which to create the new image.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceId The ID of the instance from which to create the new image.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateImageRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }
    
    
    /**
     * The name for the new AMI being created.
     *
     * @return The name for the new AMI being created.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The name for the new AMI being created.
     *
     * @param name The name for the new AMI being created.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The name for the new AMI being created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param name The name for the new AMI being created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateImageRequest withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * The description for the new AMI being created.
     *
     * @return The description for the new AMI being created.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * The description for the new AMI being created.
     *
     * @param description The description for the new AMI being created.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The description for the new AMI being created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param description The description for the new AMI being created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateImageRequest withDescription(String description) {
        this.description = description;
        return this;
    }
    
    
    /**
     * By default this property is set to <code>false</code>, which means
     * Amazon EC2 attempts to cleanly shut down the instance before image
     * creation and reboots the instance afterwards. When set to true, Amazon
     * EC2 will not shut down the instance before creating the image. When
     * this option is used, file system integrity on the created image cannot
     * be guaranteed.
     *
     * @return By default this property is set to <code>false</code>, which means
     *         Amazon EC2 attempts to cleanly shut down the instance before image
     *         creation and reboots the instance afterwards. When set to true, Amazon
     *         EC2 will not shut down the instance before creating the image. When
     *         this option is used, file system integrity on the created image cannot
     *         be guaranteed.
     */
    public Boolean isNoReboot() {
        return noReboot;
    }
    
    /**
     * By default this property is set to <code>false</code>, which means
     * Amazon EC2 attempts to cleanly shut down the instance before image
     * creation and reboots the instance afterwards. When set to true, Amazon
     * EC2 will not shut down the instance before creating the image. When
     * this option is used, file system integrity on the created image cannot
     * be guaranteed.
     *
     * @param noReboot By default this property is set to <code>false</code>, which means
     *         Amazon EC2 attempts to cleanly shut down the instance before image
     *         creation and reboots the instance afterwards. When set to true, Amazon
     *         EC2 will not shut down the instance before creating the image. When
     *         this option is used, file system integrity on the created image cannot
     *         be guaranteed.
     */
    public void setNoReboot(Boolean noReboot) {
        this.noReboot = noReboot;
    }
    
    /**
     * By default this property is set to <code>false</code>, which means
     * Amazon EC2 attempts to cleanly shut down the instance before image
     * creation and reboots the instance afterwards. When set to true, Amazon
     * EC2 will not shut down the instance before creating the image. When
     * this option is used, file system integrity on the created image cannot
     * be guaranteed.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param noReboot By default this property is set to <code>false</code>, which means
     *         Amazon EC2 attempts to cleanly shut down the instance before image
     *         creation and reboots the instance afterwards. When set to true, Amazon
     *         EC2 will not shut down the instance before creating the image. When
     *         this option is used, file system integrity on the created image cannot
     *         be guaranteed.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateImageRequest withNoReboot(Boolean noReboot) {
        this.noReboot = noReboot;
        return this;
    }
    
    
    /**
     * By default this property is set to <code>false</code>, which means
     * Amazon EC2 attempts to cleanly shut down the instance before image
     * creation and reboots the instance afterwards. When set to true, Amazon
     * EC2 will not shut down the instance before creating the image. When
     * this option is used, file system integrity on the created image cannot
     * be guaranteed.
     *
     * @return By default this property is set to <code>false</code>, which means
     *         Amazon EC2 attempts to cleanly shut down the instance before image
     *         creation and reboots the instance afterwards. When set to true, Amazon
     *         EC2 will not shut down the instance before creating the image. When
     *         this option is used, file system integrity on the created image cannot
     *         be guaranteed.
     */
    public Boolean getNoReboot() {
        return noReboot;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("InstanceId: " + instanceId + ", ");
        sb.append("Name: " + name + ", ");
        sb.append("Description: " + description + ", ");
        sb.append("NoReboot: " + noReboot + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
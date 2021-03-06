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
package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

/**
 * Deletes the specified bucket. 
 * <p>
 * Note: If you delete an bucket that does not exist, Amazon S3 will return
 * a success (not an error message).
 */
public class DeleteBucketRequest extends AmazonWebServiceRequest {

    /**
     * The name of the Amazon S3 bucket to delete.
     */
    private String bucketName;

    /**
     * Constructs a new DeleteBucketRequest, ready to be executed and delete the
     * specified bucket.
     * 
     * @param bucketName
     *            The name of the Amazon S3 bucket to delete.
     */
    public DeleteBucketRequest(String bucketName) {
        setBucketName(bucketName);
    }
    
    /**
     * Sets the name of the Amazon S3 bucket to delete.
     * 
     * @param bucketName the name of the Amazon S3 bucket to delete.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Returns the name of the Amazon S3 bucket to delete.
     * 
     * @return the name of the Amazon S3 bucket to delete.
     */
    public String getBucketName() {
        return bucketName;
    }    
}

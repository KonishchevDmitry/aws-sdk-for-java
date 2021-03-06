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
package com.amazonaws.services.elasticmapreduce.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * <p>
 * The input argument to the AddJobFlow operation.
 * </p>
 */
public class AddJobFlowStepsRequest extends AmazonWebServiceRequest {

    /**
     * A string that uniquely identifies the job flow. This identifier is
     * returned by <a>RunJobFlow</a> and can also be obtained from
     * <a>DescribeJobFlows</a> .
     */
    private String jobFlowId;

    /**
     * A list of <a>StepConfig</a> to be executed by the job flow.
     */
    private java.util.List<StepConfig> steps;

    /**
     * A string that uniquely identifies the job flow. This identifier is
     * returned by <a>RunJobFlow</a> and can also be obtained from
     * <a>DescribeJobFlows</a> .
     *
     * @return A string that uniquely identifies the job flow. This identifier is
     *         returned by <a>RunJobFlow</a> and can also be obtained from
     *         <a>DescribeJobFlows</a> .
     */
    public String getJobFlowId() {
        return jobFlowId;
    }
    
    /**
     * A string that uniquely identifies the job flow. This identifier is
     * returned by <a>RunJobFlow</a> and can also be obtained from
     * <a>DescribeJobFlows</a> .
     *
     * @param jobFlowId A string that uniquely identifies the job flow. This identifier is
     *         returned by <a>RunJobFlow</a> and can also be obtained from
     *         <a>DescribeJobFlows</a> .
     */
    public void setJobFlowId(String jobFlowId) {
        this.jobFlowId = jobFlowId;
    }
    
    /**
     * A string that uniquely identifies the job flow. This identifier is
     * returned by <a>RunJobFlow</a> and can also be obtained from
     * <a>DescribeJobFlows</a> .
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param jobFlowId A string that uniquely identifies the job flow. This identifier is
     *         returned by <a>RunJobFlow</a> and can also be obtained from
     *         <a>DescribeJobFlows</a> .
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AddJobFlowStepsRequest withJobFlowId(String jobFlowId) {
        this.jobFlowId = jobFlowId;
        return this;
    }
    
    
    /**
     * A list of <a>StepConfig</a> to be executed by the job flow.
     *
     * @return A list of <a>StepConfig</a> to be executed by the job flow.
     */
    public java.util.List<StepConfig> getSteps() {
        if (steps == null) {
            steps = new java.util.ArrayList<StepConfig>();
        }
        return steps;
    }
    
    /**
     * A list of <a>StepConfig</a> to be executed by the job flow.
     *
     * @param steps A list of <a>StepConfig</a> to be executed by the job flow.
     */
    public void setSteps(java.util.Collection<StepConfig> steps) {
        java.util.List<StepConfig> stepsCopy = new java.util.ArrayList<StepConfig>();
        if (steps != null) {
            stepsCopy.addAll(steps);
        }
        this.steps = stepsCopy;
    }
    
    /**
     * A list of <a>StepConfig</a> to be executed by the job flow.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param steps A list of <a>StepConfig</a> to be executed by the job flow.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AddJobFlowStepsRequest withSteps(StepConfig... steps) {
        for (StepConfig value : steps) {
            getSteps().add(value);
        }
        return this;
    }
    
    /**
     * A list of <a>StepConfig</a> to be executed by the job flow.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param steps A list of <a>StepConfig</a> to be executed by the job flow.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AddJobFlowStepsRequest withSteps(java.util.Collection<StepConfig> steps) {
        java.util.List<StepConfig> stepsCopy = new java.util.ArrayList<StepConfig>();
        if (steps != null) {
            stepsCopy.addAll(steps);
        }
        this.steps = stepsCopy;

        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("JobFlowId: " + jobFlowId + ", ");
        sb.append("Steps: " + steps + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
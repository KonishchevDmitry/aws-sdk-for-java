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

/**
 * <p>
 * Specifies the execution state of a step.
 * </p>
 */
public class StepExecutionStatusDetail {

    /**
     * The state of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>PENDING|RUNNING|COMPLETED|CANCELLED|FAILED|INTERRUPTED<br/>
     */
    private String state;

    /**
     * The creation date and time of the step.
     */
    private java.util.Date creationDateTime;

    /**
     * The start date and time of the step.
     */
    private java.util.Date startDateTime;

    /**
     * The completion date and time of the step.
     */
    private java.util.Date endDateTime;

    /**
     * A description of the step's current state.
     */
    private String lastStateChangeReason;

    /**
     * The state of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>PENDING|RUNNING|COMPLETED|CANCELLED|FAILED|INTERRUPTED<br/>
     *
     * @return The state of the job flow step.
     */
    public String getState() {
        return state;
    }
    
    /**
     * The state of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>PENDING|RUNNING|COMPLETED|CANCELLED|FAILED|INTERRUPTED<br/>
     *
     * @param state The state of the job flow step.
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * The state of the job flow step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>PENDING|RUNNING|COMPLETED|CANCELLED|FAILED|INTERRUPTED<br/>
     *
     * @param state The state of the job flow step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepExecutionStatusDetail withState(String state) {
        this.state = state;
        return this;
    }
    
    
    /**
     * The creation date and time of the step.
     *
     * @return The creation date and time of the step.
     */
    public java.util.Date getCreationDateTime() {
        return creationDateTime;
    }
    
    /**
     * The creation date and time of the step.
     *
     * @param creationDateTime The creation date and time of the step.
     */
    public void setCreationDateTime(java.util.Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
    
    /**
     * The creation date and time of the step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param creationDateTime The creation date and time of the step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepExecutionStatusDetail withCreationDateTime(java.util.Date creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }
    
    
    /**
     * The start date and time of the step.
     *
     * @return The start date and time of the step.
     */
    public java.util.Date getStartDateTime() {
        return startDateTime;
    }
    
    /**
     * The start date and time of the step.
     *
     * @param startDateTime The start date and time of the step.
     */
    public void setStartDateTime(java.util.Date startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    /**
     * The start date and time of the step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param startDateTime The start date and time of the step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepExecutionStatusDetail withStartDateTime(java.util.Date startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }
    
    
    /**
     * The completion date and time of the step.
     *
     * @return The completion date and time of the step.
     */
    public java.util.Date getEndDateTime() {
        return endDateTime;
    }
    
    /**
     * The completion date and time of the step.
     *
     * @param endDateTime The completion date and time of the step.
     */
    public void setEndDateTime(java.util.Date endDateTime) {
        this.endDateTime = endDateTime;
    }
    
    /**
     * The completion date and time of the step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param endDateTime The completion date and time of the step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepExecutionStatusDetail withEndDateTime(java.util.Date endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }
    
    
    /**
     * A description of the step's current state.
     *
     * @return A description of the step's current state.
     */
    public String getLastStateChangeReason() {
        return lastStateChangeReason;
    }
    
    /**
     * A description of the step's current state.
     *
     * @param lastStateChangeReason A description of the step's current state.
     */
    public void setLastStateChangeReason(String lastStateChangeReason) {
        this.lastStateChangeReason = lastStateChangeReason;
    }
    
    /**
     * A description of the step's current state.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param lastStateChangeReason A description of the step's current state.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepExecutionStatusDetail withLastStateChangeReason(String lastStateChangeReason) {
        this.lastStateChangeReason = lastStateChangeReason;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("State: " + state + ", ");
        sb.append("CreationDateTime: " + creationDateTime + ", ");
        sb.append("StartDateTime: " + startDateTime + ", ");
        sb.append("EndDateTime: " + endDateTime + ", ");
        sb.append("LastStateChangeReason: " + lastStateChangeReason + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
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
 * Specification of a job flow step.
 * </p>
 */
public class StepConfig {

    /**
     * The name of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     */
    private String name;

    /**
     * Specifies the action to take if the job flow step fails.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>TERMINATE_JOB_FLOW|CANCEL_AND_WAIT|CONTINUE<br/>
     */
    private String actionOnFailure;

    /**
     * Specifies the JAR file used for the job flow step.
     */
    private HadoopJarStepConfig hadoopJarStep;

    /**
     * The name of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @return The name of the job flow step.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The name of the job flow step.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @param name The name of the job flow step.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The name of the job flow step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 256<br/>
     *
     * @param name The name of the job flow step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepConfig withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * Specifies the action to take if the job flow step fails.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>TERMINATE_JOB_FLOW|CANCEL_AND_WAIT|CONTINUE<br/>
     *
     * @return Specifies the action to take if the job flow step fails.
     */
    public String getActionOnFailure() {
        return actionOnFailure;
    }
    
    /**
     * Specifies the action to take if the job flow step fails.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>TERMINATE_JOB_FLOW|CANCEL_AND_WAIT|CONTINUE<br/>
     *
     * @param actionOnFailure Specifies the action to take if the job flow step fails.
     */
    public void setActionOnFailure(String actionOnFailure) {
        this.actionOnFailure = actionOnFailure;
    }
    
    /**
     * Specifies the action to take if the job flow step fails.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>TERMINATE_JOB_FLOW|CANCEL_AND_WAIT|CONTINUE<br/>
     *
     * @param actionOnFailure Specifies the action to take if the job flow step fails.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepConfig withActionOnFailure(String actionOnFailure) {
        this.actionOnFailure = actionOnFailure;
        return this;
    }
    
    
    /**
     * Specifies the JAR file used for the job flow step.
     *
     * @return Specifies the JAR file used for the job flow step.
     */
    public HadoopJarStepConfig getHadoopJarStep() {
        return hadoopJarStep;
    }
    
    /**
     * Specifies the JAR file used for the job flow step.
     *
     * @param hadoopJarStep Specifies the JAR file used for the job flow step.
     */
    public void setHadoopJarStep(HadoopJarStepConfig hadoopJarStep) {
        this.hadoopJarStep = hadoopJarStep;
    }
    
    /**
     * Specifies the JAR file used for the job flow step.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param hadoopJarStep Specifies the JAR file used for the job flow step.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public StepConfig withHadoopJarStep(HadoopJarStepConfig hadoopJarStep) {
        this.hadoopJarStep = hadoopJarStep;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("Name: " + name + ", ");
        sb.append("ActionOnFailure: " + actionOnFailure + ", ");
        sb.append("HadoopJarStep: " + hadoopJarStep + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
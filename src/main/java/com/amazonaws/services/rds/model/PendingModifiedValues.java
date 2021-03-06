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
package com.amazonaws.services.rds.model;

/**
 * <p>
 * 
 * </p>
 */
public class PendingModifiedValues {

    /**
     * Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     * be applied or is in progress.
     */
    private String dBInstanceClass;

    /**
     * Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     * will be applied or is in progress.
     */
    private Integer allocatedStorage;

    /**
     * Contains the pending or in-progress change of the master credentials
     * for the DB Instance.
     */
    private String masterUserPassword;

    /**
     * Specifies the pending port for the DB Instance.
     */
    private Integer port;

    /**
     * Specifies the pending number of days for which automated backups are
     * retained.
     */
    private Integer backupRetentionPeriod;

    /**
     * Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     * be applied or is in progress.
     *
     * @return Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     *         be applied or is in progress.
     */
    public String getDBInstanceClass() {
        return dBInstanceClass;
    }
    
    /**
     * Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     * be applied or is in progress.
     *
     * @param dBInstanceClass Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     *         be applied or is in progress.
     */
    public void setDBInstanceClass(String dBInstanceClass) {
        this.dBInstanceClass = dBInstanceClass;
    }
    
    /**
     * Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     * be applied or is in progress.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dBInstanceClass Contains the new <i>DBInstanceClass</i> for the DB Instance that will
     *         be applied or is in progress.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PendingModifiedValues withDBInstanceClass(String dBInstanceClass) {
        this.dBInstanceClass = dBInstanceClass;
        return this;
    }
    
    
    /**
     * Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     * will be applied or is in progress.
     *
     * @return Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     *         will be applied or is in progress.
     */
    public Integer getAllocatedStorage() {
        return allocatedStorage;
    }
    
    /**
     * Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     * will be applied or is in progress.
     *
     * @param allocatedStorage Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     *         will be applied or is in progress.
     */
    public void setAllocatedStorage(Integer allocatedStorage) {
        this.allocatedStorage = allocatedStorage;
    }
    
    /**
     * Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     * will be applied or is in progress.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param allocatedStorage Contains the new <i>AllocatedStorage</i> size for the DB Instance that
     *         will be applied or is in progress.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PendingModifiedValues withAllocatedStorage(Integer allocatedStorage) {
        this.allocatedStorage = allocatedStorage;
        return this;
    }
    
    
    /**
     * Contains the pending or in-progress change of the master credentials
     * for the DB Instance.
     *
     * @return Contains the pending or in-progress change of the master credentials
     *         for the DB Instance.
     */
    public String getMasterUserPassword() {
        return masterUserPassword;
    }
    
    /**
     * Contains the pending or in-progress change of the master credentials
     * for the DB Instance.
     *
     * @param masterUserPassword Contains the pending or in-progress change of the master credentials
     *         for the DB Instance.
     */
    public void setMasterUserPassword(String masterUserPassword) {
        this.masterUserPassword = masterUserPassword;
    }
    
    /**
     * Contains the pending or in-progress change of the master credentials
     * for the DB Instance.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param masterUserPassword Contains the pending or in-progress change of the master credentials
     *         for the DB Instance.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PendingModifiedValues withMasterUserPassword(String masterUserPassword) {
        this.masterUserPassword = masterUserPassword;
        return this;
    }
    
    
    /**
     * Specifies the pending port for the DB Instance.
     *
     * @return Specifies the pending port for the DB Instance.
     */
    public Integer getPort() {
        return port;
    }
    
    /**
     * Specifies the pending port for the DB Instance.
     *
     * @param port Specifies the pending port for the DB Instance.
     */
    public void setPort(Integer port) {
        this.port = port;
    }
    
    /**
     * Specifies the pending port for the DB Instance.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param port Specifies the pending port for the DB Instance.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PendingModifiedValues withPort(Integer port) {
        this.port = port;
        return this;
    }
    
    
    /**
     * Specifies the pending number of days for which automated backups are
     * retained.
     *
     * @return Specifies the pending number of days for which automated backups are
     *         retained.
     */
    public Integer getBackupRetentionPeriod() {
        return backupRetentionPeriod;
    }
    
    /**
     * Specifies the pending number of days for which automated backups are
     * retained.
     *
     * @param backupRetentionPeriod Specifies the pending number of days for which automated backups are
     *         retained.
     */
    public void setBackupRetentionPeriod(Integer backupRetentionPeriod) {
        this.backupRetentionPeriod = backupRetentionPeriod;
    }
    
    /**
     * Specifies the pending number of days for which automated backups are
     * retained.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param backupRetentionPeriod Specifies the pending number of days for which automated backups are
     *         retained.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PendingModifiedValues withBackupRetentionPeriod(Integer backupRetentionPeriod) {
        this.backupRetentionPeriod = backupRetentionPeriod;
        return this;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        
        sb.append("DBInstanceClass: " + dBInstanceClass + ", ");
        sb.append("AllocatedStorage: " + allocatedStorage + ", ");
        sb.append("MasterUserPassword: " + masterUserPassword + ", ");
        sb.append("Port: " + port + ", ");
        sb.append("BackupRetentionPeriod: " + backupRetentionPeriod + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    
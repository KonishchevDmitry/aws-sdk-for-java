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

package com.amazonaws.services.ec2.model.transform;

import org.w3c.dom.*;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeUnmarshallers.*;
import com.amazonaws.util.XpathUtils;

/**
 * Purchase Reserved Instances Offering Request Unmarshaller
 */
public class PurchaseReservedInstancesOfferingRequestUnmarshaller implements Unmarshaller<PurchaseReservedInstancesOfferingRequest, Node> {

    public PurchaseReservedInstancesOfferingRequest unmarshall(Node node) throws Exception {
        if (node == null) return null;

        PurchaseReservedInstancesOfferingRequest purchaseReservedInstancesOfferingRequest = new PurchaseReservedInstancesOfferingRequest();

        
        Node reservedInstancesOfferingIdNode = XpathUtils.asNode("ReservedInstancesOfferingId", node);
        purchaseReservedInstancesOfferingRequest.setReservedInstancesOfferingId(new StringUnmarshaller().unmarshall(reservedInstancesOfferingIdNode));
    
        Node instanceCountNode = XpathUtils.asNode("InstanceCount", node);
        purchaseReservedInstancesOfferingRequest.setInstanceCount(new IntegerUnmarshaller().unmarshall(instanceCountNode));
    

        return purchaseReservedInstancesOfferingRequest;
    }
}
    
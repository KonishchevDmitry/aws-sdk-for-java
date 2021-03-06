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
 * Stop Instances Request Unmarshaller
 */
public class StopInstancesRequestUnmarshaller implements Unmarshaller<StopInstancesRequest, Node> {

    public StopInstancesRequest unmarshall(Node node) throws Exception {
        if (node == null) return null;

        StopInstancesRequest stopInstancesRequest = new StopInstancesRequest();

        
        NodeList instanceIdsNodes = XpathUtils.asNodeList("InstanceId/InstanceId", node);
        for (int instanceIdsIndex = 0; instanceIdsIndex < XpathUtils.nodeLength(instanceIdsNodes); ++instanceIdsIndex) {
            Node instanceIdsNode = instanceIdsNodes.item(instanceIdsIndex);
            stopInstancesRequest.getInstanceIds().add(new StringUnmarshaller().unmarshall(instanceIdsNode));
            instanceIdsNode.getParentNode().removeChild(instanceIdsNode);
        }
    
        Node forceNode = XpathUtils.asNode("force", node);
        stopInstancesRequest.setForce(new BooleanUnmarshaller().unmarshall(forceNode));
    

        return stopInstancesRequest;
    }
}
    
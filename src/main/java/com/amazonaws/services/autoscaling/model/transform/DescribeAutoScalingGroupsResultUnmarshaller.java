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

package com.amazonaws.services.autoscaling.model.transform;

import org.w3c.dom.*;
import com.amazonaws.services.autoscaling.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeUnmarshallers.*;
import com.amazonaws.util.XpathUtils;

/**
 * Describe Auto Scaling Groups Result Unmarshaller
 */
public class DescribeAutoScalingGroupsResultUnmarshaller implements Unmarshaller<DescribeAutoScalingGroupsResult, Node> {

    public DescribeAutoScalingGroupsResult unmarshall(Node node) throws Exception {
        if (node == null) return null;

        DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult = new DescribeAutoScalingGroupsResult();

        
        NodeList autoScalingGroupsNodes = XpathUtils.asNodeList("AutoScalingGroups/member", node);
        for (int autoScalingGroupsIndex = 0; autoScalingGroupsIndex < XpathUtils.nodeLength(autoScalingGroupsNodes); ++autoScalingGroupsIndex) {
            Node autoScalingGroupsNode = autoScalingGroupsNodes.item(autoScalingGroupsIndex);
            describeAutoScalingGroupsResult.getAutoScalingGroups().add(new AutoScalingGroupUnmarshaller().unmarshall(autoScalingGroupsNode));
            autoScalingGroupsNode.getParentNode().removeChild(autoScalingGroupsNode);
        }
    

        return describeAutoScalingGroupsResult;
    }
}
    
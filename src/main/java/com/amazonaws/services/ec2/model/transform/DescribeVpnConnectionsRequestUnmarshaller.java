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
 * Describe Vpn Connections Request Unmarshaller
 */
public class DescribeVpnConnectionsRequestUnmarshaller implements Unmarshaller<DescribeVpnConnectionsRequest, Node> {

    public DescribeVpnConnectionsRequest unmarshall(Node node) throws Exception {
        if (node == null) return null;

        DescribeVpnConnectionsRequest describeVpnConnectionsRequest = new DescribeVpnConnectionsRequest();

        
        NodeList vpnConnectionIdsNodes = XpathUtils.asNodeList("VpnConnectionId/VpnConnectionId", node);
        for (int vpnConnectionIdsIndex = 0; vpnConnectionIdsIndex < XpathUtils.nodeLength(vpnConnectionIdsNodes); ++vpnConnectionIdsIndex) {
            Node vpnConnectionIdsNode = vpnConnectionIdsNodes.item(vpnConnectionIdsIndex);
            describeVpnConnectionsRequest.getVpnConnectionIds().add(new StringUnmarshaller().unmarshall(vpnConnectionIdsNode));
            vpnConnectionIdsNode.getParentNode().removeChild(vpnConnectionIdsNode);
        }
    

        return describeVpnConnectionsRequest;
    }
}
    
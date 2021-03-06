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
 * Reservation Unmarshaller
 */
public class ReservationUnmarshaller implements Unmarshaller<Reservation, Node> {

    public Reservation unmarshall(Node node) throws Exception {
        if (node == null) return null;

        Reservation reservation = new Reservation();

        
        Node reservationIdNode = XpathUtils.asNode("reservationId", node);
        reservation.setReservationId(new StringUnmarshaller().unmarshall(reservationIdNode));
    
        Node ownerIdNode = XpathUtils.asNode("ownerId", node);
        reservation.setOwnerId(new StringUnmarshaller().unmarshall(ownerIdNode));
    
        Node requesterIdNode = XpathUtils.asNode("requesterId", node);
        reservation.setRequesterId(new StringUnmarshaller().unmarshall(requesterIdNode));
    
        NodeList groupNamesNodes = XpathUtils.asNodeList("groupSet/item/groupId", node);
        for (int groupNamesIndex = 0; groupNamesIndex < XpathUtils.nodeLength(groupNamesNodes); ++groupNamesIndex) {
            Node groupNamesNode = groupNamesNodes.item(groupNamesIndex);
            reservation.getGroupNames().add(new StringUnmarshaller().unmarshall(groupNamesNode));
            groupNamesNode.getParentNode().removeChild(groupNamesNode);
        }
    
        NodeList instancesNodes = XpathUtils.asNodeList("instancesSet/item", node);
        for (int instancesIndex = 0; instancesIndex < XpathUtils.nodeLength(instancesNodes); ++instancesIndex) {
            Node instancesNode = instancesNodes.item(instancesIndex);
            reservation.getInstances().add(new InstanceUnmarshaller().unmarshall(instancesNode));
            instancesNode.getParentNode().removeChild(instancesNode);
        }
    

        return reservation;
    }
}
    
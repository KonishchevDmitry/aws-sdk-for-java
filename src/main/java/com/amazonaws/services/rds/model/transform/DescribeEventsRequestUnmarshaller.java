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

package com.amazonaws.services.rds.model.transform;

import org.w3c.dom.*;
import com.amazonaws.services.rds.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeUnmarshallers.*;
import com.amazonaws.util.XpathUtils;

/**
 * Describe Events Request Unmarshaller
 */
public class DescribeEventsRequestUnmarshaller implements Unmarshaller<DescribeEventsRequest, Node> {

    public DescribeEventsRequest unmarshall(Node node) throws Exception {
        if (node == null) return null;

        DescribeEventsRequest describeEventsRequest = new DescribeEventsRequest();

        
        Node sourceIdentifierNode = XpathUtils.asNode("SourceIdentifier", node);
        describeEventsRequest.setSourceIdentifier(new StringUnmarshaller().unmarshall(sourceIdentifierNode));
    
        Node sourceTypeNode = XpathUtils.asNode("SourceType", node);
        describeEventsRequest.setSourceType(new StringUnmarshaller().unmarshall(sourceTypeNode));
    
        Node startTimeNode = XpathUtils.asNode("StartTime", node);
        describeEventsRequest.setStartTime(new DateUnmarshaller().unmarshall(startTimeNode));
    
        Node endTimeNode = XpathUtils.asNode("EndTime", node);
        describeEventsRequest.setEndTime(new DateUnmarshaller().unmarshall(endTimeNode));
    
        Node durationNode = XpathUtils.asNode("Duration", node);
        describeEventsRequest.setDuration(new IntegerUnmarshaller().unmarshall(durationNode));
    
        Node maxRecordsNode = XpathUtils.asNode("MaxRecords", node);
        describeEventsRequest.setMaxRecords(new IntegerUnmarshaller().unmarshall(maxRecordsNode));
    
        Node markerNode = XpathUtils.asNode("Marker", node);
        describeEventsRequest.setMarker(new StringUnmarshaller().unmarshall(markerNode));
    

        return describeEventsRequest;
    }
}
    
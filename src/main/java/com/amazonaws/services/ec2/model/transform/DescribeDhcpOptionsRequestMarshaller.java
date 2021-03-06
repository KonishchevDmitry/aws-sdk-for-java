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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Describe Dhcp Options Request Marshaller
 */        
public class DescribeDhcpOptionsRequestMarshaller implements Marshaller<Request<DescribeDhcpOptionsRequest>, DescribeDhcpOptionsRequest> {

    public Request<DescribeDhcpOptionsRequest> marshall(DescribeDhcpOptionsRequest describeDhcpOptionsRequest) {
        Request<DescribeDhcpOptionsRequest> request = new DefaultRequest<DescribeDhcpOptionsRequest>(describeDhcpOptionsRequest, "AmazonEC2");
        request.addParameter("Action", "DescribeDhcpOptions");
        request.addParameter("Version", "2009-11-30");
        if (describeDhcpOptionsRequest != null) {
            java.util.List<String> dhcpOptionsIdsList = describeDhcpOptionsRequest.getDhcpOptionsIds();
            int dhcpOptionsIdsListIndex = 1;
            for (String dhcpOptionsIdsListValue : dhcpOptionsIdsList) { 
                if (dhcpOptionsIdsListValue != null) {
                    request.addParameter("DhcpOptionsId." + dhcpOptionsIdsListIndex, StringUtils.fromString(dhcpOptionsIdsListValue));
                }
                dhcpOptionsIdsListIndex++;
            }
        }


        return request;
    }
}

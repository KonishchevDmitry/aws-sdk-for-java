/*
 * Copyright 2010 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Portions copyright 2006-2009 James Murty. Please see LICENSE.txt
 * for applicable license terms and NOTICE.txt for applicable notices.
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
package com.amazonaws.services.s3.model;

/**
 * Represents a grantee (entity) who can be assigned access permissions in an
 * {@link AccessControlList}. All grantees have an ID of some kind (though the
 * format of the ID can differ depending on the kind of grantee).
 */
public interface Grantee {

    /**
     * Sets the identifier for this grantee. The meaning of the identifier is
     * specific to each implementation of the Grantee.
     *
     * @param id
     *            The identifier for this grantee.
     */
    public void setIdentifier(String id);

    /**
     * Returns the identifier for this grantee. The meaning of the grantee
     * identifier is specific to each implementation of the Grantee.
     *
     * @return The identifier for this grantee.
     */
    public String getIdentifier();

}

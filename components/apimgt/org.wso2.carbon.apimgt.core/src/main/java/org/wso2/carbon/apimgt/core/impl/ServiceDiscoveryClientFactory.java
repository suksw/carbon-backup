/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.apimgt.core.impl;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.openshift.client.DefaultOpenShiftClient;


/**
 * Factory that provides service discovery clients (any class that connect to the external system).
 *
 * To support unit tests of ServiceDiscoverer impl classes,
 * by giving the chance to mock the client.
 */
public class ServiceDiscoveryClientFactory {

    public DefaultOpenShiftClient getDefaultOpenShiftClient(Config config) {
        return new DefaultOpenShiftClient(config);
    }
}

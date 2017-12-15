/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.status.dashboard.core.api;

import org.wso2.carbon.status.dashboard.core.internal.DashboardDataHolder;
/*
import feign.Client;
import feign.Feign;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;*/
import feign.gson.GsonEncoder;
import org.wso2.carbon.status.dashboard.core.internal.DashboardDataHolder;

/**
 * Rest API service which is used to access service stub for calling another worker.
 */
public class WorkerServiceFactory {

    public static WorkerServiceStub getWorkerHttpsClient(String url, String username, String password) {
        return DashboardDataHolder.getInstance().getClientBuilderService().build(username, password,
                                                                                 CONNECTION_TIME_OUT,
                                                                          READ_TIME_OUT, WorkerServiceStub.class, url);
       /* return Feign.builder()
                .requestInterceptor(new BasicAuthRequestInterceptor(username, password))
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .options(getOptions())
                .client(new Client.Default(null, null))
                .target(WorkerServiceStub.class, url);*/
    }

    public static Request.Options getOptions() {
        int connectionTimeOut = DashboardDataHolder.getInstance().getStatusDashboardDeploymentConfigs()
                .getWorkerConnectionConfigurations().getConnectionTimeOut();
        int readTimeOut = DashboardDataHolder.getInstance().getStatusDashboardDeploymentConfigs()
                .getWorkerConnectionConfigurations().getReadTimeOut();
        return new Request.Options(connectionTimeOut,readTimeOut);
    }
    /*public static Request.Options getOptions() {
        return new Request.Options(CONNECTION_TIME_OUT,READ_TIME_OUT);
    }*/
}

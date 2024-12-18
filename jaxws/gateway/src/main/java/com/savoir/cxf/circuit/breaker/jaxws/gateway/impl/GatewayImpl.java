/*
 * Copyright (c) 2012-2024 Savoir Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.savoir.cxf.circuit.breaker.jaxws.gateway.impl;

import com.savoir.cxf.circuit.breaker.jaxws.gateway.GatewayService;
import com.savoir.cxf.circuit.breaker.jaxws.dataservice.DataService;

public class GatewayImpl implements GatewayService {

    private DataService proxy;

    public void setProxy(DataService proxy) {
        this.proxy = proxy;
    }

    @Override
    public String call() {
        String response = proxy.doDataSourceStuff();
        return "Gateway Called DS, got response: " + response;
    }
}

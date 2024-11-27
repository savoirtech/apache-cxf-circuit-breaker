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
package com.savoir.cxf.circuit.breaker.jaxws.client;

import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.clustering.SequentialStrategy;
import org.apache.cxf.clustering.circuitbreaker.CircuitBreakerFailoverFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.savoir.cxf.circuit.breaker.jaxws.gateway.GatewayService;

public class ClientCaller {

    GatewayService client;

    public ClientCaller() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        CircuitBreakerFailoverFeature feature = new CircuitBreakerFailoverFeature(1, 2000);
        List<String> alternateAddresses = new ArrayList<String>();
        SequentialStrategy strategy = new SequentialStrategy();
        strategy.setAlternateAddresses(alternateAddresses);
        feature.setStrategy(strategy);
        List<Feature> features = new ArrayList<Feature>();
        features.add(feature);
        features.add(new LoggingFeature());
        factory.setFeatures(features);
        factory.setAddress("http://localhost:8181/cxf/sample");
        //Create proxy
        client = factory.create(GatewayService.class);
    }

    public String callService() {
        return client.call();
    }
}

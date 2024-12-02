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
package com.savoir.cxf.circuit.breaker.jaxrs.client;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.cxf.clustering.SequentialStrategy;
import org.apache.cxf.clustering.circuitbreaker.CircuitBreakerFailoverFeature;
import org.apache.cxf.jaxrs.client.WebClient;

public class ClientCaller {

    private WebClient client;

    public ClientCaller() {
        URL busFile = ClientCaller.class.getResource("cxf-client.xml");

        CircuitBreakerFailoverFeature feature = new CircuitBreakerFailoverFeature();
        feature.setThreshold(2);
        SequentialStrategy strategy = new SequentialStrategy();
        strategy.setDelayBetweenRetries(3000L);
        List<String> addresses = new ArrayList<>();
        addresses.add("http://localhost:8181/cxf/sample");
        strategy.setAlternateAddresses(addresses);
        feature.setStrategy(strategy);

        String address = "http://localhost:8181/cxf/sample";
        client = WebClient.create(address, null,
                Collections.singletonList(feature), busFile.toString());
        client = client.type("application/json");
    }

    public String callService() {
        Response response = client.get();
        return response.readEntity(String.class);
    }

}

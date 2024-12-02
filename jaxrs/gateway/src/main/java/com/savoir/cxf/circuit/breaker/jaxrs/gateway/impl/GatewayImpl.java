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
package com.savoir.cxf.circuit.breaker.jaxrs.gateway.impl;

import com.savoir.cxf.circuit.breaker.jaxrs.gateway.GatewayService;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.clustering.SequentialStrategy;
import org.apache.cxf.clustering.circuitbreaker.CircuitBreakerFailoverFeature;
import org.osgi.framework.BundleContext;

@Path("/")
public class GatewayImpl implements GatewayService {

    private WebClient dataServiceClient;

    public GatewayImpl(BundleContext context) {
        URL busFile = context.getBundle().getResource("cxf-client.xml");

        CircuitBreakerFailoverFeature feature = new CircuitBreakerFailoverFeature();
        feature.setThreshold(2);
        SequentialStrategy strategy = new SequentialStrategy();
        strategy.setDelayBetweenRetries(3000L);
        List<String> addresses = new ArrayList<>();
        addresses.add("http://localhost:8181/cxf/ds");
        strategy.setAlternateAddresses(addresses);
        feature.setStrategy(strategy);

        String address = "http://localhost:8181/cxf/ds";
        dataServiceClient = WebClient.create(address, null,
                Collections.singletonList(feature), busFile.toString());
        dataServiceClient = dataServiceClient.type("application/json");
    }

    @Override
    @Path("/")
    @Produces("application/json")
    @GET
    public String call() {
        Response response = dataServiceClient.get();
        return "Gateway Called DS, got response: " + response.readEntity(String.class);
    }
}

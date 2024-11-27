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
package com.savoir.cxf.circuit.breaker.jaxws.dataservice.impl;

import com.savoir.cxf.circuit.breaker.jaxws.dataservice.DataService;

public class DataServiceImpl implements DataService {

    private long sleep;

    public void setSleep(long sleep) {
        this.sleep = sleep;
    }

    @Override
    public String doDataSourceStuff() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            // ignore
        }
        return "Talked to the DataSource.";
    }
}

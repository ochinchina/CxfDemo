/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


package demo.hw.client;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Response;

import demo.hw.ws.GreeterAsync;
import demo.hw.ws.SOAPService;
import demo.hw.ws.GreetMeSometimeResponse;

public final class Client {

    private static final QName SERVICE_NAME
            = new QName("http://apache.org/hello_world_async_soap_http", "SOAPService");


    private Client() {
    }

    private static URL getUrl(String...args) throws MalformedURLException {
        if( args.length == 0) {
            return new URL("http://127.0.0.1:9000/SoapContext/SoapPort?wsdl");
        }

        File wsdl = new File(args[0]);

        if (wsdl.exists() && wsdl.isFile()) {
            return wsdl.toURI().toURL();
        } else {
            return new URL("http://127.0.0.1:9000/SoapContext/SoapPort?wsdl");
        }
    }
    public static void main(String[] args) throws Exception {


        URL url = getUrl(args);

        System.out.println("try to connect server with URL:" + url);

        SOAPService ss = new SOAPService(url, SERVICE_NAME);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ss.setExecutor(executor);
        GreeterAsync port = ss.getSoapPort();
        String resp;

        // callback method
        TestAsyncHandler testAsyncHandler = new TestAsyncHandler();
        System.out.println("Invoking greetMeSometimeAsync using callback object...");
        Future<?> response = port.greetMeSometimeAsync(System.getProperty("user.name"), testAsyncHandler);
        while (!response.isDone()) {
            Thread.sleep(100);
        }
        resp = testAsyncHandler.getResponse();
        System.out.println();
        System.out.println("Server responded through callback with: " + resp);
        System.out.println();

        //polling method
        System.out.println("Invoking greetMeSometimeAsync using polling...");
        Response<GreetMeSometimeResponse> greetMeSomeTimeResp =
                port.greetMeSometimeAsync(System.getProperty("user.name"));
        while (!greetMeSomeTimeResp.isDone()) {
            Thread.sleep(100);
        }
        GreetMeSometimeResponse reply = greetMeSomeTimeResp.get();
        System.out.println("Server responded through polling with: " + reply.getResponseType());

        System.exit(0);

    }

}

package demo.hw.ws;

import jakarta.annotation.Generated;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.Response;
import jakarta.xml.ws.ResponseWrapper;
import java.util.concurrent.Future;

/**
 * This class was generated by Apache CXF 4.0.4
 * 2024-07-11T15:23:34.723+08:00
 * Generated source version: 4.0.4
 *
 */
@WebService(targetNamespace = "http://apache.org/hello_world_async_soap_http", name = "GreeterAsync")
@XmlSeeAlso({ObjectFactory.class})
@Generated(value = "org.apache.cxf.tools.wsdlto.WSDLToJava", date = "2024-07-11T15:23:34.723+08:00", comments = "Apache CXF 4.0.4")
public interface GreeterAsync {

    @WebMethod(operationName = "greetMeSometime")
    @RequestWrapper(localName = "greetMeSometime", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometime")
    @ResponseWrapper(localName = "greetMeSometimeResponse", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometimeResponse")
    @Generated(value = "org.apache.cxf.tools.wsdlto.WSDLToJava", date = "2024-07-11T15:23:34.723+08:00")
    public Response<demo.hw.ws.GreetMeSometimeResponse> greetMeSometimeAsync(

        @WebParam(name = "requestType", targetNamespace = "http://apache.org/hello_world_async_soap_http/types")
        java.lang.String requestType
    );

    @WebMethod(operationName = "greetMeSometime")
    @ResponseWrapper(localName = "greetMeSometimeResponse", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometimeResponse")
    @RequestWrapper(localName = "greetMeSometime", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometime")
    @Generated(value = "org.apache.cxf.tools.wsdlto.WSDLToJava", date = "2024-07-11T15:23:34.723+08:00")
    public Future<?> greetMeSometimeAsync(

        @WebParam(name = "requestType", targetNamespace = "http://apache.org/hello_world_async_soap_http/types")
        java.lang.String requestType,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<demo.hw.ws.GreetMeSometimeResponse> asyncHandler
    );

    @WebMethod(action = "http://apache.org/hello_world_async_soap_http/greetMeSometime")
    @RequestWrapper(localName = "greetMeSometime", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometime")
    @ResponseWrapper(localName = "greetMeSometimeResponse", targetNamespace = "http://apache.org/hello_world_async_soap_http/types", className = "demo.hw.ws.GreetMeSometimeResponse")
    @WebResult(name = "responseType", targetNamespace = "http://apache.org/hello_world_async_soap_http/types")
    @Generated(value = "org.apache.cxf.tools.wsdlto.WSDLToJava", date = "2024-07-11T15:23:34.723+08:00")
    public java.lang.String greetMeSometime(

        @WebParam(name = "requestType", targetNamespace = "http://apache.org/hello_world_async_soap_http/types")
        java.lang.String requestType
    );
}

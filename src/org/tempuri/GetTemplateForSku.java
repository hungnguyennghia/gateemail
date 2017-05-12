
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_02-b08-fcs
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "GetTemplateForSku", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://10.13.141.195:8002/WebService/GetTemplateForSku.asmx?WSDL")
public class GetTemplateForSku
    extends Service
{

    private final static URL GETTEMPLATEFORSKU_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://10.13.141.195:8002/WebService/GetTemplateForSku.asmx?WSDL");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        GETTEMPLATEFORSKU_WSDL_LOCATION = url;
    }

    public GetTemplateForSku(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetTemplateForSku() {
        super(GETTEMPLATEFORSKU_WSDL_LOCATION, new QName("http://tempuri.org/", "GetTemplateForSku"));
    }

    /**
     * 
     * @return
     *     returns GetTemplateForSkuSoap
     */
    @WebEndpoint(name = "GetTemplateForSkuSoap")
    public GetTemplateForSkuSoap getGetTemplateForSkuSoap() {
        return (GetTemplateForSkuSoap)super.getPort(new QName("http://tempuri.org/", "GetTemplateForSkuSoap"), GetTemplateForSkuSoap.class);
    }

}
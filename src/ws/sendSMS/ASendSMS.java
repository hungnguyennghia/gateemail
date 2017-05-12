/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.sendSMS;


import javax.xml.ws.Endpoint;


import vc.WSConfig;

public class ASendSMS {

    private Endpoint service = null;
    private RSendSMS receiver;  
    private String url=WSConfig.urlSOS;

    public ASendSMS() {       
    	
        receiver = new RSendSMS();
    }

    public void start() {
        if (receiver != null) {
            service = Endpoint.publish(url, receiver);
             System.out.println("Publish service success: " + url);
        } else {
        	System.out.println("Publish service success: " + url);
        }
    }
    public void stop() {
        service.stop();
    }
}

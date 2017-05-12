
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetTemplateEmailResult" type="{http://tempuri.org/}TemplateMailItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getTemplateEmailResult"
})
@XmlRootElement(name = "GetTemplateEmailResponse")
public class GetTemplateEmailResponse {

    @XmlElement(name = "GetTemplateEmailResult")
    protected TemplateMailItem getTemplateEmailResult;

    /**
     * Gets the value of the getTemplateEmailResult property.
     * 
     * @return
     *     possible object is
     *     {@link TemplateMailItem }
     *     
     */
    public TemplateMailItem getGetTemplateEmailResult() {
        return getTemplateEmailResult;
    }

    /**
     * Sets the value of the getTemplateEmailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplateMailItem }
     *     
     */
    public void setGetTemplateEmailResult(TemplateMailItem value) {
        this.getTemplateEmailResult = value;
    }

}

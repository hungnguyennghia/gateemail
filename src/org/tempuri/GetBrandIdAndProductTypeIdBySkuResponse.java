
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
 *         &lt;element name="GetBrandIdAndProductTypeIdBySkuResult" type="{http://tempuri.org/}BrandIdAndProductTypeId" minOccurs="0"/>
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
    "getBrandIdAndProductTypeIdBySkuResult"
})
@XmlRootElement(name = "GetBrandIdAndProductTypeIdBySkuResponse")
public class GetBrandIdAndProductTypeIdBySkuResponse {

    @XmlElement(name = "GetBrandIdAndProductTypeIdBySkuResult")
    protected BrandIdAndProductTypeId getBrandIdAndProductTypeIdBySkuResult;

    /**
     * Gets the value of the getBrandIdAndProductTypeIdBySkuResult property.
     * 
     * @return
     *     possible object is
     *     {@link BrandIdAndProductTypeId }
     *     
     */
    public BrandIdAndProductTypeId getGetBrandIdAndProductTypeIdBySkuResult() {
        return getBrandIdAndProductTypeIdBySkuResult;
    }

    /**
     * Sets the value of the getBrandIdAndProductTypeIdBySkuResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrandIdAndProductTypeId }
     *     
     */
    public void setGetBrandIdAndProductTypeIdBySkuResult(BrandIdAndProductTypeId value) {
        this.getBrandIdAndProductTypeIdBySkuResult = value;
    }

}

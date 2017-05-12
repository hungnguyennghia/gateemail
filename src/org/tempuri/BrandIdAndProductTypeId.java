
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BrandIdAndProductTypeId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrandIdAndProductTypeId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrandId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProductTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrandIdAndProductTypeId", propOrder = {
    "brandId",
    "productTypeID"
})
public class BrandIdAndProductTypeId {

    @XmlElement(name = "BrandId")
    protected int brandId;
    @XmlElement(name = "ProductTypeID")
    protected int productTypeID;

    /**
     * Gets the value of the brandId property.
     * 
     */
    public int getBrandId() {
        return brandId;
    }

    /**
     * Sets the value of the brandId property.
     * 
     */
    public void setBrandId(int value) {
        this.brandId = value;
    }

    /**
     * Gets the value of the productTypeID property.
     * 
     */
    public int getProductTypeID() {
        return productTypeID;
    }

    /**
     * Sets the value of the productTypeID property.
     * 
     */
    public void setProductTypeID(int value) {
        this.productTypeID = value;
    }

}

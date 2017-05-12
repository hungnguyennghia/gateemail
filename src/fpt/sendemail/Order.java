
package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sku" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nganhHang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loaiSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateBilling" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "order", propOrder = {
    "orderNumber",
    "sku",
    "productName",
    "customerFullName",
    "billingPhoneNumber",
    "email",
    "nganhHang",
    "loaiSP",
    "totalPrice",
    "dateBilling",
    "userName",
    "passWord"
})
public class Order {

    protected String orderNumber;
    protected String sku;
    protected String productName;
    protected String customerFullName;
    protected String billingPhoneNumber;
    protected String email;
    protected String nganhHang;
    protected String loaiSP;
    protected Long totalPrice;
    protected String dateBilling;
    protected String userName;
    protected String passWord;

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the sku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSku() {
        return sku;
    }

    /**
     * Sets the value of the sku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSku(String value) {
        this.sku = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the customerFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerFullName() {
        return customerFullName;
    }

    /**
     * Sets the value of the customerFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerFullName(String value) {
        this.customerFullName = value;
    }

    /**
     * Gets the value of the billingPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingPhoneNumber() {
        return billingPhoneNumber;
    }

    /**
     * Sets the value of the billingPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingPhoneNumber(String value) {
        this.billingPhoneNumber = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the nganhHang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNganhHang() {
        return nganhHang;
    }

    /**
     * Sets the value of the nganhHang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNganhHang(String value) {
        this.nganhHang = value;
    }

    /**
     * Gets the value of the loaiSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoaiSP() {
        return loaiSP;
    }

    /**
     * Sets the value of the loaiSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoaiSP(String value) {
        this.loaiSP = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalPrice(Long value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the dateBilling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateBilling() {
        return dateBilling;
    }

    /**
     * Sets the value of the dateBilling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateBilling(String value) {
        this.dateBilling = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the passWord property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets the value of the passWord property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassWord(String value) {
        this.passWord = value;
    }

}

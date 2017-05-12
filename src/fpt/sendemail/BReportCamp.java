
package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bReportCamp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bReportCamp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="click_link" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date_done" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date_send" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="open_email" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="request_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_email" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valid_email" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bReportCamp", propOrder = {
    "clickLink",
    "dateDone",
    "dateSend",
    "errorCode",
    "openEmail",
    "requestId",
    "totalEmail",
    "validEmail"
})
public class BReportCamp {

    @XmlElement(name = "click_link", namespace = "")
    protected int clickLink;
    @XmlElement(name = "date_done", namespace = "")
    protected String dateDone;
    @XmlElement(name = "date_send", namespace = "")
    protected String dateSend;
    @XmlElement(namespace = "")
    protected int errorCode;
    @XmlElement(name = "open_email", namespace = "")
    protected int openEmail;
    @XmlElement(name = "request_id", namespace = "")
    protected String requestId;
    @XmlElement(name = "total_email", namespace = "")
    protected int totalEmail;
    @XmlElement(name = "valid_email", namespace = "")
    protected int validEmail;

    /**
     * Gets the value of the clickLink property.
     * 
     */
    public int getClickLink() {
        return clickLink;
    }

    /**
     * Sets the value of the clickLink property.
     * 
     */
    public void setClickLink(int value) {
        this.clickLink = value;
    }

    /**
     * Gets the value of the dateDone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDone() {
        return dateDone;
    }

    /**
     * Sets the value of the dateDone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDone(String value) {
        this.dateDone = value;
    }

    /**
     * Gets the value of the dateSend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateSend() {
        return dateSend;
    }

    /**
     * Sets the value of the dateSend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateSend(String value) {
        this.dateSend = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     */
    public void setErrorCode(int value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the openEmail property.
     * 
     */
    public int getOpenEmail() {
        return openEmail;
    }

    /**
     * Sets the value of the openEmail property.
     * 
     */
    public void setOpenEmail(int value) {
        this.openEmail = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the totalEmail property.
     * 
     */
    public int getTotalEmail() {
        return totalEmail;
    }

    /**
     * Sets the value of the totalEmail property.
     * 
     */
    public void setTotalEmail(int value) {
        this.totalEmail = value;
    }

    /**
     * Gets the value of the validEmail property.
     * 
     */
    public int getValidEmail() {
        return validEmail;
    }

    /**
     * Sets the value of the validEmail property.
     * 
     */
    public void setValidEmail(int value) {
        this.validEmail = value;
    }

}


package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendEmail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendEmail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlpush" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "sendEmail", propOrder = {
    "from",
    "to",
    "cc",
    "bcc",
    "emailId",
    "subject",
    "content",
    "orderid",
    "teamplate",
    "urlpush",
    "userName",
    "passWord"
})
public class SendEmail_Type {

    protected String from;
    protected String to;
    protected String cc;
    protected String bcc;
    @XmlElement(name = "email_id")
    protected String emailId;
    protected String subject;
    protected String content;
    protected String orderid;
    protected String teamplate;
    protected String urlpush;
    protected String userName;
    protected String passWord;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCc(String value) {
        this.cc = value;
    }

    /**
     * Gets the value of the bcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets the value of the bcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBcc(String value) {
        this.bcc = value;
    }

    /**
     * Gets the value of the emailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the value of the emailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailId(String value) {
        this.emailId = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the orderid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * Sets the value of the orderid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderid(String value) {
        this.orderid = value;
    }

    /**
     * Gets the value of the teamplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamplate() {
        return teamplate;
    }

    /**
     * Sets the value of the teamplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamplate(String value) {
        this.teamplate = value;
    }

    /**
     * Gets the value of the urlpush property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlpush() {
        return urlpush;
    }

    /**
     * Sets the value of the urlpush property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlpush(String value) {
        this.urlpush = value;
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

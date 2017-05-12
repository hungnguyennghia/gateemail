
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemplateMailItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemplateMailItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TeamplateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BodyContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateMailItem", propOrder = {
    "teamplateName",
    "subject",
    "bodyContent",
    "status"
})
public class TemplateMailItem {

    @XmlElement(name = "TeamplateName")
    protected String teamplateName;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "BodyContent")
    protected String bodyContent;
    @XmlElement(name = "Status")
    protected int status;

    /**
     * Gets the value of the teamplateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamplateName() {
        return teamplateName;
    }

    /**
     * Sets the value of the teamplateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamplateName(String value) {
        this.teamplateName = value;
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
     * Gets the value of the bodyContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyContent() {
        return bodyContent;
    }

    /**
     * Sets the value of the bodyContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyContent(String value) {
        this.bodyContent = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}

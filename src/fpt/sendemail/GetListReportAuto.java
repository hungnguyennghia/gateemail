
package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getListReportAuto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getListReportAuto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeTemp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dateSend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getListReportAuto", propOrder = {
    "typeTemp",
    "dateSend"
})
public class GetListReportAuto {

    protected int typeTemp;
    protected String dateSend;

    /**
     * Gets the value of the typeTemp property.
     * 
     */
    public int getTypeTemp() {
        return typeTemp;
    }

    /**
     * Sets the value of the typeTemp property.
     * 
     */
    public void setTypeTemp(int value) {
        this.typeTemp = value;
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

}

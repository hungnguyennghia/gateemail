
package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileupload complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileupload">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileupload", propOrder = {
    "fileName",
    "imageBytes"
})
public class Fileupload {

    @XmlElement(namespace = "")
    protected String fileName;
    @XmlElement(namespace = "")
    protected byte[] imageBytes;

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the imageBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImageBytes() {
        return imageBytes;
    }

    /**
     * Sets the value of the imageBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImageBytes(byte[] value) {
        this.imageBytes = ((byte[]) value);
    }

}

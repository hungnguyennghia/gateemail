
package fpt.sendemail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for campaign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="campaign">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nameCamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nganhHang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loaiSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="giaTu" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="giaDen" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tgMuaTu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tgMuaDen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tgGui" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teamplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileoperator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "campaign", propOrder = {
    "nameCamp",
    "nganhHang",
    "loaiSP",
    "giaTu",
    "giaDen",
    "tgMuaTu",
    "tgMuaDen",
    "tgGui",
    "teamplate",
    "subject",
    "content",
    "mobileoperator",
    "userName",
    "passWord"
})
public class Campaign {

    protected String nameCamp;
    protected String nganhHang;
    protected String loaiSP;
    protected Long giaTu;
    protected Long giaDen;
    protected String tgMuaTu;
    protected String tgMuaDen;
    protected String tgGui;
    protected String teamplate;
    protected String subject;
    protected String content;
    protected String mobileoperator;
    protected String userName;
    protected String passWord;

    /**
     * Gets the value of the nameCamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameCamp() {
        return nameCamp;
    }

    /**
     * Sets the value of the nameCamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameCamp(String value) {
        this.nameCamp = value;
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
     * Gets the value of the giaTu property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGiaTu() {
        return giaTu;
    }

    /**
     * Sets the value of the giaTu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGiaTu(Long value) {
        this.giaTu = value;
    }

    /**
     * Gets the value of the giaDen property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGiaDen() {
        return giaDen;
    }

    /**
     * Sets the value of the giaDen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGiaDen(Long value) {
        this.giaDen = value;
    }

    /**
     * Gets the value of the tgMuaTu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTgMuaTu() {
        return tgMuaTu;
    }

    /**
     * Sets the value of the tgMuaTu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTgMuaTu(String value) {
        this.tgMuaTu = value;
    }

    /**
     * Gets the value of the tgMuaDen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTgMuaDen() {
        return tgMuaDen;
    }

    /**
     * Sets the value of the tgMuaDen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTgMuaDen(String value) {
        this.tgMuaDen = value;
    }

    /**
     * Gets the value of the tgGui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTgGui() {
        return tgGui;
    }

    /**
     * Sets the value of the tgGui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTgGui(String value) {
        this.tgGui = value;
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
     * Gets the value of the mobileoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileoperator() {
        return mobileoperator;
    }

    /**
     * Sets the value of the mobileoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileoperator(String value) {
        this.mobileoperator = value;
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

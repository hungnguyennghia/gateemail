
package org.tempuri;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BrandIdAndProductTypeId }
     * 
     */
    public BrandIdAndProductTypeId createBrandIdAndProductTypeId() {
        return new BrandIdAndProductTypeId();
    }

    /**
     * Create an instance of {@link GetTemplateEmail }
     * 
     */
    public GetTemplateEmail createGetTemplateEmail() {
        return new GetTemplateEmail();
    }

    /**
     * Create an instance of {@link GetBrandIdAndProductTypeIdBySku }
     * 
     */
    public GetBrandIdAndProductTypeIdBySku createGetBrandIdAndProductTypeIdBySku() {
        return new GetBrandIdAndProductTypeIdBySku();
    }

    /**
     * Create an instance of {@link TemplateMailItem }
     * 
     */
    public TemplateMailItem createTemplateMailItem() {
        return new TemplateMailItem();
    }

    /**
     * Create an instance of {@link GetTemplateEmailResponse }
     * 
     */
    public GetTemplateEmailResponse createGetTemplateEmailResponse() {
        return new GetTemplateEmailResponse();
    }

    /**
     * Create an instance of {@link GetBrandIdAndProductTypeIdBySkuResponse }
     * 
     */
    public GetBrandIdAndProductTypeIdBySkuResponse createGetBrandIdAndProductTypeIdBySkuResponse() {
        return new GetBrandIdAndProductTypeIdBySkuResponse();
    }

}

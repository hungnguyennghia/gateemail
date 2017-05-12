
package fpt.sendemail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fpt.sendemail package. 
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

    private final static QName _SendEmailAttachResponse_QNAME = new QName("http://sendEmail.fpt/", "sendEmailAttachResponse");
    private final static QName _GetReportResponse_QNAME = new QName("http://sendEmail.fpt/", "getReportResponse");
    private final static QName _GetListReportAutoResponse_QNAME = new QName("http://sendEmail.fpt/", "getListReportAutoResponse");
    private final static QName _SendEmailFull_QNAME = new QName("http://sendEmail.fpt/", "sendEmailFull");
    private final static QName _SendEmailAttach_QNAME = new QName("http://sendEmail.fpt/", "sendEmailAttach");
    private final static QName _SendEmailFullResponse_QNAME = new QName("http://sendEmail.fpt/", "sendEmailFullResponse");
    private final static QName _CampaignUpdateResponse_QNAME = new QName("http://sendEmail.fpt/", "campaign_UpdateResponse");
    private final static QName _Order_QNAME = new QName("http://sendEmail.fpt/", "order");
    private final static QName _OrderResponse_QNAME = new QName("http://sendEmail.fpt/", "orderResponse");
    private final static QName _SendEmailResponse_QNAME = new QName("http://sendEmail.fpt/", "sendEmailResponse");
    private final static QName _CampaignResponse_QNAME = new QName("http://sendEmail.fpt/", "campaignResponse");
    private final static QName _CheckStatus_QNAME = new QName("http://sendEmail.fpt/", "checkStatus");
    private final static QName _CheckStatusResponse_QNAME = new QName("http://sendEmail.fpt/", "checkStatusResponse");
    private final static QName _CampaignUpdate_QNAME = new QName("http://sendEmail.fpt/", "campaign_Update");
    private final static QName _Campaign_QNAME = new QName("http://sendEmail.fpt/", "campaign");
    private final static QName _GetReport_QNAME = new QName("http://sendEmail.fpt/", "getReport");
    private final static QName _GetListReportAuto_QNAME = new QName("http://sendEmail.fpt/", "getListReportAuto");
    private final static QName _SendEmail_QNAME = new QName("http://sendEmail.fpt/", "sendEmail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fpt.sendemail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendEmailAttachResponse }
     * 
     */
    public SendEmailAttachResponse createSendEmailAttachResponse() {
        return new SendEmailAttachResponse();
    }

    /**
     * Create an instance of {@link GetReportResponse }
     * 
     */
    public GetReportResponse createGetReportResponse() {
        return new GetReportResponse();
    }

    /**
     * Create an instance of {@link CheckStatusResponse }
     * 
     */
    public CheckStatusResponse createCheckStatusResponse() {
        return new CheckStatusResponse();
    }

    /**
     * Create an instance of {@link GetListReportAutoResponse }
     * 
     */
    public GetListReportAutoResponse createGetListReportAutoResponse() {
        return new GetListReportAutoResponse();
    }

    /**
     * Create an instance of {@link GetReport }
     * 
     */
    public GetReport createGetReport() {
        return new GetReport();
    }

    /**
     * Create an instance of {@link GetListReportAuto }
     * 
     */
    public GetListReportAuto createGetListReportAuto() {
        return new GetListReportAuto();
    }

    /**
     * Create an instance of {@link CampaignResponse }
     * 
     */
    public CampaignResponse createCampaignResponse() {
        return new CampaignResponse();
    }

    /**
     * Create an instance of {@link CheckStatus }
     * 
     */
    public CheckStatus createCheckStatus() {
        return new CheckStatus();
    }

    /**
     * Create an instance of {@link BReportCamp }
     * 
     */
    public BReportCamp createBReportCamp() {
        return new BReportCamp();
    }

    /**
     * Create an instance of {@link Campaign }
     * 
     */
    public Campaign createCampaign() {
        return new Campaign();
    }

    /**
     * Create an instance of {@link SendEmailAttach }
     * 
     */
    public SendEmailAttach createSendEmailAttach() {
        return new SendEmailAttach();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link SendEmailFullResponse }
     * 
     */
    public SendEmailFullResponse createSendEmailFullResponse() {
        return new SendEmailFullResponse();
    }

    /**
     * Create an instance of {@link SendEmailFull }
     * 
     */
    public SendEmailFull createSendEmailFull() {
        return new SendEmailFull();
    }

    /**
     * Create an instance of {@link Fileupload }
     * 
     */
    public Fileupload createFileupload() {
        return new Fileupload();
    }

    /**
     * Create an instance of {@link SendEmailResponse }
     * 
     */
    public SendEmailResponse createSendEmailResponse() {
        return new SendEmailResponse();
    }

    /**
     * Create an instance of {@link CampaignUpdate }
     * 
     */
    public CampaignUpdate createCampaignUpdate() {
        return new CampaignUpdate();
    }

    /**
     * Create an instance of {@link CampaignUpdateResponse }
     * 
     */
    public CampaignUpdateResponse createCampaignUpdateResponse() {
        return new CampaignUpdateResponse();
    }

    /**
     * Create an instance of {@link SendEmail_Type }
     * 
     */
    public SendEmail_Type createSendEmail_Type() {
        return new SendEmail_Type();
    }

    /**
     * Create an instance of {@link OrderResponse }
     * 
     */
    public OrderResponse createOrderResponse() {
        return new OrderResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailAttachResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmailAttachResponse")
    public JAXBElement<SendEmailAttachResponse> createSendEmailAttachResponse(SendEmailAttachResponse value) {
        return new JAXBElement<SendEmailAttachResponse>(_SendEmailAttachResponse_QNAME, SendEmailAttachResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "getReportResponse")
    public JAXBElement<GetReportResponse> createGetReportResponse(GetReportResponse value) {
        return new JAXBElement<GetReportResponse>(_GetReportResponse_QNAME, GetReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListReportAutoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "getListReportAutoResponse")
    public JAXBElement<GetListReportAutoResponse> createGetListReportAutoResponse(GetListReportAutoResponse value) {
        return new JAXBElement<GetListReportAutoResponse>(_GetListReportAutoResponse_QNAME, GetListReportAutoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailFull }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmailFull")
    public JAXBElement<SendEmailFull> createSendEmailFull(SendEmailFull value) {
        return new JAXBElement<SendEmailFull>(_SendEmailFull_QNAME, SendEmailFull.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailAttach }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmailAttach")
    public JAXBElement<SendEmailAttach> createSendEmailAttach(SendEmailAttach value) {
        return new JAXBElement<SendEmailAttach>(_SendEmailAttach_QNAME, SendEmailAttach.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailFullResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmailFullResponse")
    public JAXBElement<SendEmailFullResponse> createSendEmailFullResponse(SendEmailFullResponse value) {
        return new JAXBElement<SendEmailFullResponse>(_SendEmailFullResponse_QNAME, SendEmailFullResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CampaignUpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "campaign_UpdateResponse")
    public JAXBElement<CampaignUpdateResponse> createCampaignUpdateResponse(CampaignUpdateResponse value) {
        return new JAXBElement<CampaignUpdateResponse>(_CampaignUpdateResponse_QNAME, CampaignUpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "order")
    public JAXBElement<Order> createOrder(Order value) {
        return new JAXBElement<Order>(_Order_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "orderResponse")
    public JAXBElement<OrderResponse> createOrderResponse(OrderResponse value) {
        return new JAXBElement<OrderResponse>(_OrderResponse_QNAME, OrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmailResponse")
    public JAXBElement<SendEmailResponse> createSendEmailResponse(SendEmailResponse value) {
        return new JAXBElement<SendEmailResponse>(_SendEmailResponse_QNAME, SendEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CampaignResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "campaignResponse")
    public JAXBElement<CampaignResponse> createCampaignResponse(CampaignResponse value) {
        return new JAXBElement<CampaignResponse>(_CampaignResponse_QNAME, CampaignResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "checkStatus")
    public JAXBElement<CheckStatus> createCheckStatus(CheckStatus value) {
        return new JAXBElement<CheckStatus>(_CheckStatus_QNAME, CheckStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "checkStatusResponse")
    public JAXBElement<CheckStatusResponse> createCheckStatusResponse(CheckStatusResponse value) {
        return new JAXBElement<CheckStatusResponse>(_CheckStatusResponse_QNAME, CheckStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CampaignUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "campaign_Update")
    public JAXBElement<CampaignUpdate> createCampaignUpdate(CampaignUpdate value) {
        return new JAXBElement<CampaignUpdate>(_CampaignUpdate_QNAME, CampaignUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Campaign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "campaign")
    public JAXBElement<Campaign> createCampaign(Campaign value) {
        return new JAXBElement<Campaign>(_Campaign_QNAME, Campaign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "getReport")
    public JAXBElement<GetReport> createGetReport(GetReport value) {
        return new JAXBElement<GetReport>(_GetReport_QNAME, GetReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListReportAuto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "getListReportAuto")
    public JAXBElement<GetListReportAuto> createGetListReportAuto(GetListReportAuto value) {
        return new JAXBElement<GetListReportAuto>(_GetListReportAuto_QNAME, GetListReportAuto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendEmail_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sendEmail.fpt/", name = "sendEmail")
    public JAXBElement<SendEmail_Type> createSendEmail(SendEmail_Type value) {
        return new JAXBElement<SendEmail_Type>(_SendEmail_QNAME, SendEmail_Type.class, null, value);
    }

}

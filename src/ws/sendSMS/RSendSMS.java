/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.sendSMS;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import redis.clients.jedis.Jedis;

import vc.VCSMS;
import vc.WSConfig;
import vc.common.LoggerConfig;
import vc.database.DBException;
import vc.database.DBTool;
import vc.database.JedisConnectionPool;
import vc.util.BCamp;
import vc.util.BOrder;
import vc.util.BReportCamp;

import vc.util.DateProc;
import vc.util.Queue;
import vc.util.beanBR;
import vc.util.fileupload;
import vc.util.logger.Logger;




/**
 *
 * @author hungnn
 */
@WebService(name = "sendEmail", targetNamespace = "http://sendEmail.fpt/")
public class RSendSMS {

 
    public RSendSMS() {      
            requestBRQueue = VCSMS.getRequestBRQueue();
            logFileQueue = VCSMS.getLogFileQueue();
            logDBQueue = VCSMS.getLogDBQueue();
            allowUsers = WSConfig.allowUsers;
            dirLog = WSConfig.dirLog;
    }  

    

    @WebMethod(operationName = "sendEmail")
    public String sendEmail(
            @WebParam(name = "from", targetNamespace = "http://sendEmail.fpt/") String from,
            @WebParam(name = "to", targetNamespace = "http://sendEmail.fpt/") String to,
            @WebParam(name = "cc", targetNamespace = "http://sendEmail.fpt/") String cc,
            @WebParam(name = "bcc", targetNamespace = "http://sendEmail.fpt/") String bcc,
            @WebParam(name = "email_id", targetNamespace = "http://sendEmail.fpt/") String email_id,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "orderid", targetNamespace = "http://sendEmail.fpt/") String orderid,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "urlpush", targetNamespace = "http://sendEmail.fpt/") String urlpush,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting sendEmail .... ");
        Logger.getInstance().logInfo("Starting sendEmail ....", null, "sendEmail");
        String result = "-1";
        beanBR bean = new beanBR();
         try {
            bean.setMessage_ids(UUID.randomUUID().toString());
            bean.setFrom(from);
            bean.setTo(to);
            bean.setCc(cc);
            bean.setBcc(bcc);
            bean.setEmail_id(email_id);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setOrderid(orderid);
            bean.setTeamplate(teamplate);
            bean.setAccount(userName+"/"+passWord);
            bean.setUrlpush(urlpush);
            boolean checkUser = authenUser(userName, passWord);
            if(!checkUser)
            {
                System.out.println(" -- Deny User -- ");
                result="0";
                bean.setResult(-4);//xac thuc sai
            } else
            {
            	result=bean.getMessage_ids();
                bean.setResult(0);//request thanh cong
                VCSMS.getRequestBRQueue().enqueue(bean);
                
            }
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getInstance().logInfo(e.getMessage(), e, "sendEmail");
		}
     	VCSMS.getLogFileQueue().enqueue(bean);
     	VCSMS.getLogDBQueue().enqueue(bean);

        return result;
            
    }
    
    @WebMethod(operationName = "sendEmailAll")
    public String sendEmailAll(
            @WebParam(name = "from", targetNamespace = "http://sendEmail.fpt/") String from,
            @WebParam(name = "to", targetNamespace = "http://sendEmail.fpt/") String to,
            @WebParam(name = "cc", targetNamespace = "http://sendEmail.fpt/") String cc,
            @WebParam(name = "bcc", targetNamespace = "http://sendEmail.fpt/") String bcc,
            @WebParam(name = "email_id", targetNamespace = "http://sendEmail.fpt/") String email_id,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "orderid", targetNamespace = "http://sendEmail.fpt/") String orderid,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "urlpush", targetNamespace = "http://sendEmail.fpt/") String urlpush,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting sendEmail .... ");
        Logger.getInstance().logInfo("Starting sendEmailAll ....", null, "sendEmailAll");
        String result = "-1";
        beanBR bean = new beanBR();
         try {
            bean.setMessage_ids(UUID.randomUUID().toString());
            bean.setFrom(from);
            bean.setTo(to);
            bean.setCc(cc);
            bean.setBcc(bcc);
            bean.setEmail_id(email_id);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setOrderid(orderid);
            bean.setTeamplate(teamplate);
            bean.setAccount(userName+"/"+passWord);
            bean.setUrlpush(urlpush);
            boolean checkUser = authenUser(userName, passWord);
            if(!checkUser)
            {
                System.out.println(" -- Deny User -- ");
                result="0";
                bean.setResult(-4);//xac thuc sai
            } else
            {
            	result=bean.getMessage_ids();
                bean.setResult(0);//request thanh cong
                VCSMS.getRequestBRQueue().enqueue(bean);
                
            }
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getInstance().logInfo(e.getMessage(), e, "sendEmailAll");
		}
     	VCSMS.getLogFileQueue().enqueue(bean);
     	VCSMS.getLogDBQueue().enqueue(bean);

        return result;
            
    }
    @WebMethod(operationName = "sendEmailFull")
    public String sendEmailFull(
            @WebParam(name = "from", targetNamespace = "http://sendEmail.fpt/") String from,
            @WebParam(name = "to", targetNamespace = "http://sendEmail.fpt/") String to,
            @WebParam(name = "cc", targetNamespace = "http://sendEmail.fpt/") String cc,
            @WebParam(name = "bcc", targetNamespace = "http://sendEmail.fpt/") String bcc,
            @WebParam(name = "email_id", targetNamespace = "http://sendEmail.fpt/") String email_id,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "orderid", targetNamespace = "http://sendEmail.fpt/") String orderid,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "urlpush", targetNamespace = "http://sendEmail.fpt/") String urlpush,
            @WebParam(name = "camp_id", targetNamespace = "http://sendEmail.fpt/") String camp_id,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting sendEmail .... ");
        String result = "-1";
        beanBR bean = new beanBR();
         try {
            bean.setMessage_ids(UUID.randomUUID().toString());
            bean.setFrom(from);
            bean.setTo(to);
            bean.setCc(cc);
            bean.setBcc(bcc);
            bean.setEmail_id(email_id);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setOrderid(orderid);
            bean.setTeamplate(teamplate);
            bean.setAccount(userName+"/"+passWord);
            bean.setUrlpush(urlpush);
            bean.setCamp_id(camp_id);
            bean.setEmailValid(1);
            boolean checkUser = authenUser(userName, passWord);
            if(!checkUser)
            {
                System.out.println(" -- Deny User -- ");
                result="0";
                bean.setResult(-4);//xac thuc sai
            } else
            {
            	result=bean.getMessage_ids();
                bean.setResult(0);//request thanh cong
                VCSMS.getRequestBRQueue().enqueue(bean);
                
            }
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getInstance().logInfo(e.getMessage(), e, "sendEmailFull");
		}
     	VCSMS.getLogFileQueue().enqueue(bean);
     	VCSMS.getLogDBQueue().enqueue(bean);

        return result;
            
    }
    @WebMethod(operationName = "sendEmailAttach")
    public String sendEmailAttach(
            @WebParam(name = "from", targetNamespace = "http://sendEmail.fpt/") String from,
            @WebParam(name = "to", targetNamespace = "http://sendEmail.fpt/") String to,
            @WebParam(name = "cc", targetNamespace = "http://sendEmail.fpt/") String cc,
            @WebParam(name = "bcc", targetNamespace = "http://sendEmail.fpt/") String bcc,
            @WebParam(name = "email_id", targetNamespace = "http://sendEmail.fpt/") String email_id,            
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "attach", targetNamespace = "http://sendEmail.fpt/")  ArrayList<fileupload> attachFiles,
            @WebParam(name = "orderid", targetNamespace = "http://sendEmail.fpt/") String orderid,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "urlpush", targetNamespace = "http://sendEmail.fpt/") String urlpush,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting sendEmail attach file .... ");
        Logger.getInstance().logInfo("Starting sendEmail attach file ....", null, "sendEmailAttach");
        String result = "-1";
        beanBR bean = new beanBR();
         try {
             
            bean.setMessage_ids(UUID.randomUUID().toString());
            bean.setFrom(from);
            bean.setTo(to.toLowerCase());
            bean.setCc(cc);
            bean.setBcc(bcc);
            bean.setEmail_id(email_id);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setFiles(attachFiles);
            bean.setOrderid(orderid);
            bean.setTeamplate(teamplate);
            bean.setAccount(userName+"/"+passWord);
            bean.setUrlpush(urlpush);
            boolean checkUser = authenUser(userName, passWord);
            if(!checkUser)
            {
                System.out.println(" -- Deny User -- ");
                result="0";
                bean.setResult(-4);//xac thuc sai
            } else
            {
            	
            	String attach[]=new String[ attachFiles.size() ];
            	for (int i = 0; i < attachFiles.size(); i++) {
            		fileupload beanFiles=new fileupload();
            		beanFiles=(fileupload)attachFiles.get(i);
                    String filePath = LoggerConfig.LOG_UPLOAD_FOLDER+LoggerConfig.LOG_PATH+DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp())+LoggerConfig.LOG_PATH+bean.getMessage_ids();
                    File dir = new File(filePath);
                    if(!dir.exists())
                        dir.mkdirs();
                    filePath+=LoggerConfig.LOG_PATH + beanFiles.getFileName();
                    

                 // if the directory does not exist, create it
              
                    try {
                        FileOutputStream fos = new FileOutputStream(filePath);
                        BufferedOutputStream outputStream = new BufferedOutputStream(fos);
                        outputStream.write(beanFiles.getImageBytes());
                        outputStream.close();
                         
                        System.out.println("Received file: " + filePath);
                         
                         attach[i]=filePath;
                         bean.setFilePaths(bean.getFilePaths().equals("")?filePath:bean.getFilePaths()+"|"+filePath);
                    } catch (IOException ex) {
                        System.err.println(ex);
                        throw new WebServiceException(ex);
                    }
				}
            	bean.setAttach(attach);
               // --------------------------------------
            	result=bean.getMessage_ids();
                bean.setResult(0);//request thanh cong
               VCSMS.getRequestBRQueue().enqueue(bean);
                
            }
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getInstance().logError(e.getMessage(), e, "sendEmailAttach");
		}
      	VCSMS.getLogFileQueue().enqueue(bean);
      	VCSMS.getLogDBQueue().enqueue(bean);

        return result;
            
    } 
    
    @WebMethod(operationName = "checkStatus")
    public String checkStatus(
            @WebParam(name = "request_id", targetNamespace = "http://sendEmail.fpt/") String request_id,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {

        System.out.println("Starting checkStatus .... ");
        String result = "-1";

        boolean checkUser = authenUser(userName, passWord);
        if(!checkUser)
        {
            System.out.println(" -- Deny User -- ");
            result = "-3";
            return result;
        }
        DBTool dbTool = new DBTool();
        try
        {
            result = dbTool.getStatusBR_ByID(request_id);
        }
        catch(DBException e)
        {
            e.printStackTrace();
            result = "-1";
        }
        return result;
    }
 
 
    @WebMethod(operationName = "order")
    public int setOrder(
            @WebParam(name = "orderNumber", targetNamespace = "http://sendEmail.fpt/") String orderNumber,
            @WebParam(name = "sku", targetNamespace = "http://sendEmail.fpt/") String sku,
            @WebParam(name = "productName", targetNamespace = "http://sendEmail.fpt/") String productName,
            @WebParam(name = "customerFullName", targetNamespace = "http://sendEmail.fpt/") String customerFullName,
            @WebParam(name = "billingPhoneNumber", targetNamespace = "http://sendEmail.fpt/") String billingPhoneNumber,
            @WebParam(name = "email", targetNamespace = "http://sendEmail.fpt/") String email,
            @WebParam(name = "nganhHang", targetNamespace = "http://sendEmail.fpt/") String nganhHang,
            @WebParam(name = "loaiSP", targetNamespace = "http://sendEmail.fpt/") String loaiSP,
            @WebParam(name = "totalPrice", targetNamespace = "http://sendEmail.fpt/") Long totalPrice,
            @WebParam(name = "dateBilling", targetNamespace = "http://sendEmail.fpt/") String dateBilling,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting setOrder .... ");
        int result = -1;
        BOrder bean = new BOrder();
         try {
            bean.setOrderNumber(orderNumber);
            bean.setSku(sku);
            bean.setProductName(productName);
            bean.setCustomerFullName(customerFullName);
            bean.setBillingPhoneNumber(billingPhoneNumber.trim());
            bean.setEmail(email);
            bean.setNganhHang(nganhHang);
            bean.setLoaiSP(loaiSP);
            bean.setGiaSP(totalPrice);
            bean.setTgMuaHang(dateBilling);
            bean.setAccount(userName+"/"+passWord);
            bean.buildMobileOperator();
            if(bean.getEmail().equals("")||bean.getEmail()==null||bean.getOrderNumber().equals("")
            		||bean.getProductName().equals("")||bean.getCustomerFullName().equals("")||bean.getBillingPhoneNumber().equals("")||bean.getLoaiSP().equals("")||bean.getTgMuaHang().equals("")){
            	 result=-1;
                 bean.setResult(-1);//xac thuc sai
                 System.out.println(" -- field null -- ");
            	
            }else{
                boolean checkUser = authenUser(userName, passWord);
                if(!checkUser)
                {
                    System.out.println(" -- Deny User -- ");
                    result=-2;
                    bean.setResult(-2);//xac thuc sai
                } else
                {
                	result=0;
                    bean.setResult(0);//request thanh cong
                 	VCSMS.getRequestQueueOrder().enqueue(bean);
                }
            }

		} catch (Exception e) {
			System.out.println(" -- loi khong xac dinh -- "+ e.getMessage());
		}
     	

        return result;
            
    }
 
    @WebMethod(operationName = "campaign")
    public String setCampaign(
            @WebParam(name = "nameCamp", targetNamespace = "http://sendEmail.fpt/") String nameCamp,
            @WebParam(name = "nganhHang", targetNamespace = "http://sendEmail.fpt/") String nganhHang,
            @WebParam(name = "loaiSP", targetNamespace = "http://sendEmail.fpt/") String loaiSP,
            @WebParam(name = "giaTu", targetNamespace = "http://sendEmail.fpt/") Long giaTu,
            @WebParam(name = "giaDen", targetNamespace = "http://sendEmail.fpt/") Long giaDen,
            @WebParam(name = "tgMuaTu", targetNamespace = "http://sendEmail.fpt/") String tgMuaTu,
            @WebParam(name = "tgMuaDen", targetNamespace = "http://sendEmail.fpt/") String tgMuaDen,
            @WebParam(name = "tgGui", targetNamespace = "http://sendEmail.fpt/") String tgGui,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "mobileoperator", targetNamespace = "http://sendEmail.fpt/") String mobileoperator,
            @WebParam(name = "unique", targetNamespace = "http://sendEmail.fpt/") int unique,
            @WebParam(name = "sendTest", targetNamespace = "http://sendEmail.fpt/") int sendTest,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting campaign .... ");
        BCamp bean = new BCamp();
         try {
            bean.setNameCamp(nameCamp);
            bean.setNganhHang(nganhHang);
            bean.setLoaiSP(loaiSP);
            bean.setGiaTu(giaTu);
            bean.setGiaDen(giaDen);
            bean.setTgMuaTu(tgMuaTu);
            bean.setTgMuaDen(tgMuaDen);
            bean.setTgGui(tgGui);
            bean.setTeamplate(teamplate);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setMobileoperator(mobileoperator);
            bean.setUnique(unique);
            bean.setSendTest(sendTest);
            bean.setAccount(userName+"/"+passWord);
            bean.setRequestID(UUID.randomUUID().toString());
            if(bean.getNameCamp().equals("")||bean.getNameCamp()==null||bean.getTgGui().equals("")){
                 bean.setRequestID("-1");//xac thuc sai
                 System.out.println(" -- field null -- ");
            	
            }else{
                boolean checkUser = authenUser(userName, passWord);
                if(!checkUser)
                {
                    System.out.println(" -- Deny User -- ");
                    bean.setRequestID("-2");//xac thuc sai
                } else
                {
                     	VCSMS.getLogDBQueueCamp().enqueue(bean);
                     	VCSMS.getRequestQueueCamp().enqueue(bean);//add queue de gui
                     	
                     	
                }
            }

		} catch (Exception e) {
			System.out.println(" -- loi khong xac dinh -- "+ e.getMessage());
		}
     	VCSMS.getLogFileQueueCamp().enqueue(bean);

        return bean.getRequestID();
            
    }

    @WebMethod(operationName = "campaign_Update")
    public int campaignUpdate(
    		@WebParam(name = "requestID", targetNamespace = "http://sendEmail.fpt/") String requestID,
    		@WebParam(name = "statusUpdate", targetNamespace = "http://sendEmail.fpt/") String statusUpdate,
            @WebParam(name = "nameCamp", targetNamespace = "http://sendEmail.fpt/") String nameCamp,
            @WebParam(name = "nganhHang", targetNamespace = "http://sendEmail.fpt/") String nganhHang,
            @WebParam(name = "loaiSP", targetNamespace = "http://sendEmail.fpt/") String loaiSP,
            @WebParam(name = "giaTu", targetNamespace = "http://sendEmail.fpt/") Long giaTu,
            @WebParam(name = "giaDen", targetNamespace = "http://sendEmail.fpt/") Long giaDen,
            @WebParam(name = "tgMuaTu", targetNamespace = "http://sendEmail.fpt/") String tgMuaTu,
            @WebParam(name = "tgMuaDen", targetNamespace = "http://sendEmail.fpt/") String tgMuaDen,
            @WebParam(name = "tgGui", targetNamespace = "http://sendEmail.fpt/") String tgGui,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "mobileoperator", targetNamespace = "http://sendEmail.fpt/") String mobileoperator,
            @WebParam(name = "unique", targetNamespace = "http://sendEmail.fpt/") int unique,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting campaignUpdate .... ");
        BCamp bean = new BCamp();
        int result=-1;
         try {
            bean.setNameCamp(nameCamp);
            bean.setNganhHang(nganhHang);
            bean.setLoaiSP(loaiSP);
            bean.setGiaTu(giaTu);
            bean.setGiaDen(giaDen);
            bean.setTgMuaTu(tgMuaTu);
            bean.setTgMuaDen(tgMuaDen);
            bean.setTgGui(tgGui);
            bean.setTeamplate(teamplate);
            bean.setSubject(subject);
            bean.setContent(content);
            bean.setAccount(userName+"/"+passWord);
            bean.setRequestID(requestID);
            bean.setMobileoperator(mobileoperator);
            bean.setUnique(unique);
            if(statusUpdate.equals("UPDATE")){
            	bean.setSended(2);
            }else  if(statusUpdate.equals("DELETE")){
            	bean.setSended(-1);
            }
            if(!statusUpdate.equals("UPDATE") && !statusUpdate.equals("DELETE")){
            	result=-1;//xac thuc sai
                System.out.println(" -- statusUpdate field -- ");
            }
            if(bean.getNameCamp().equals("")||bean.getNameCamp()==null
            		||bean.getLoaiSP()==null||bean.getTgGui().equals("")||bean.getTgGui()==null){
            	result=-1;//xac thuc sai
                 System.out.println(" -- field null -- ");
            	
            }else{
                boolean checkUser = authenUser(userName, passWord);
                if(!checkUser)
                {
                    System.out.println(" -- Deny User -- ");
                    result=-2;//xac thuc sai
                } else
                {
                     	
                     	//set redis de check khi send email
        	        	Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
        	        	if(!WSConfig.redisAuth.equals(""))jedis.auth(WSConfig.redisAuth);
        	        	
        	        	jedis.set(requestID, statusUpdate);//UPDATE or DELETE

        	        	VCSMS.getLogDBQueueCamp().enqueue(bean);
                      	result=0;
                      	jedis.close();
                }
            }

		} catch (Exception e) {
			System.out.println(" -- loi khong xac dinh -- "+ e.getMessage());
		}
     	VCSMS.getLogFileQueueCamp().enqueue(bean);

        return result;
            
    }

    @WebMethod(operationName = "getCountEmail")
    public Long getCountEmail(
            @WebParam(name = "nameCamp", targetNamespace = "http://sendEmail.fpt/") String nameCamp,
            @WebParam(name = "nganhHang", targetNamespace = "http://sendEmail.fpt/") String nganhHang,
            @WebParam(name = "loaiSP", targetNamespace = "http://sendEmail.fpt/") String loaiSP,
            @WebParam(name = "giaTu", targetNamespace = "http://sendEmail.fpt/") Long giaTu,
            @WebParam(name = "giaDen", targetNamespace = "http://sendEmail.fpt/") Long giaDen,
            @WebParam(name = "tgMuaTu", targetNamespace = "http://sendEmail.fpt/") String tgMuaTu,
            @WebParam(name = "tgMuaDen", targetNamespace = "http://sendEmail.fpt/") String tgMuaDen,
            @WebParam(name = "tgGui", targetNamespace = "http://sendEmail.fpt/") String tgGui,
            @WebParam(name = "teamplate", targetNamespace = "http://sendEmail.fpt/") String teamplate,
            @WebParam(name = "subject", targetNamespace = "http://sendEmail.fpt/") String subject,
            @WebParam(name = "content", targetNamespace = "http://sendEmail.fpt/") String content,
            @WebParam(name = "mobileoperator", targetNamespace = "http://sendEmail.fpt/") String mobileoperator,
            @WebParam(name = "unique", targetNamespace = "http://sendEmail.fpt/") int unique,
            @WebParam(name = "sendTest", targetNamespace = "http://sendEmail.fpt/") int sendTest,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting getCountEmail .... ");
        BCamp bean = new BCamp();
        long result=0;
        Jedis jedis = new Jedis(WSConfig.redisHost, WSConfig.redisPort);
        try {
           bean.setNameCamp(nameCamp);
           bean.setNganhHang(nganhHang);
           bean.setLoaiSP(loaiSP);
           bean.setGiaTu(giaTu);
           bean.setGiaDen(giaDen);
           bean.setTgMuaTu(tgMuaTu);
           bean.setTgMuaDen(tgMuaDen);
           bean.setTgGui(tgGui);
           bean.setTeamplate(teamplate);
           bean.setSubject(subject);
           bean.setContent(content);
           bean.setMobileoperator(mobileoperator);
           bean.setUnique(unique); 
           bean.setSendTest(sendTest);
          
	  	String key=nganhHang+loaiSP+giaTu+giaDen+tgMuaTu+tgMuaDen+mobileoperator+unique+sendTest;
   	  	if(!jedis.exists(key)){
        	DBTool db=new DBTool();
        	bean=db.getCountAllEmailByCamp(bean);
        	result=bean.getCountRows();
        	jedis.set(key, result+"");
   		  	jedis.expire(key, 86400);
   		  	System.out.println("set key...");
   	  	}else{
   	  	result=Long.parseLong(jedis.get(key));
   	  		System.out.println(result);
   	  		System.out.println("exists key...");
   	  	}
   	  	jedis.close();

		} catch (Exception e) {
			System.out.println(" -- loi khong xac dinh -- "+ e.getMessage());
			jedis.close();
		}

        return result;
            
    }
    @WebMethod(operationName = "getReport")
    public BReportCamp getReport(
    		@WebParam(name = "requestID", targetNamespace = "http://sendEmail.fpt/") String requestID,
    		@WebParam(name = "typeCamp", targetNamespace = "http://sendEmail.fpt/") int typeCamp,
            @WebParam(name = "userName", targetNamespace = "http://sendEmail.fpt/") String userName,
            @WebParam(name = "passWord", targetNamespace = "http://sendEmail.fpt/") String passWord
            ) {
        System.out.println("Starting getReport .... ");
        BReportCamp bean = new BReportCamp();
        bean.setErrorCode(-1);
         try {

            if(requestID.equals("")||requestID==null){
            	bean.setErrorCode(-1);
                 System.out.println(" -- field null -- ");
            	
            }else{
                boolean checkUser = authenUser(userName, passWord);
                if(!checkUser)
                {
                    System.out.println(" -- Deny User -- ");
                    bean.setErrorCode(-2);
                } else
                {                	
                	DBTool db=new DBTool();
                		bean=db.getReportCamp_ByID(requestID,typeCamp);
                }
            }

		} catch (Exception e) {
			System.out.println(" -- loi khong xac dinh -- "+ e.getMessage());
		}

        return bean;
            
    }
    
    @WebMethod(operationName = "getListReportAuto")
    public List getListReportAuto(
    		@WebParam(name = "typeTemp", targetNamespace = "http://sendEmail.fpt/") int typeTemp,
    		@WebParam(name = "dateSend", targetNamespace = "http://sendEmail.fpt/") String dateSend

            ) {
        System.out.println("Starting getListReportAuto .... ");
        List listR = new ArrayList();              	

        if(typeTemp==3){
        	BReportCamp bean=new BReportCamp();
        	bean.setRequest_id("Email.EBilling_"+dateSend);
        	listR.add(bean);
        }else{
        	DBTool db=new DBTool();
        	listR=db.getAllReportAuto(typeTemp,dateSend);
        }

        return listR;
            
    }
    
    private static boolean authenUser(String user, String pass)
    {
        boolean result = false;
        if(allowUsers == null || allowUsers.size() == 0)
            return true;
        if(user == null || pass == null)
            return false;
        if(!allowUsers.containsKey(user))
        {
            System.out.println((new StringBuilder("There is no user name : ")).append(user).toString());
            result = false;
        } else
        if(!((String)allowUsers.get(user)).equals(pass))
        {
            System.out.println((new StringBuilder("Password doesn't match for user: ")).append(user).toString());
            result = false;
        } else
        {
            System.out.println((new StringBuilder(" Accepted user: ")).append(user).toString());
            result = true;
        }
        return result;
    }
    
   

    static String state = null;
    static Hashtable allowUsers = new Hashtable();
    static String receiver = null;
    static File dir = null;
    static final String _PREFIX = "\n";
    static String allowIPsDir = "./conf/allowIPs.properties";
    static String allowUsersDir = "./conf/allowUsers.properties";
    static String dirLog = (new StringBuilder(String.valueOf(System.getProperty("user.dir")))).append("/LOG").toString();
    static Properties properties = new Properties();
    static boolean wscpStatus = false;
    private Queue requestBRQueue;
    private Queue logFileQueue;
    private Queue logDBQueue;
}

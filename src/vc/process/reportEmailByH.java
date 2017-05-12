
package vc.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import vc.VCSMS;
import vc.WSConfig;
import vc.database.DBTool;
import vc.util.BeanReport;
import vc.util.DateProc;
import vc.util.beanBR;

public class reportEmailByH extends Thread
{

    public reportEmailByH()
    {

    }

    private void processReportEmailSend() throws Exception
    {
    	Calendar now = Calendar.getInstance();
    	int hour=now.get(Calendar.HOUR_OF_DAY);
    	String subject="Sản lượng email GATEWAY ghi nhận trong ngày "+DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
    	

    	String content="<b>Sản lượng email GATEWAY ghi nhận đến "+hour+"h trong ngày "+DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp())+"</b><br>" +
		"<html><body><table>";
		content+="<tr style='padding:8px;border-top:1px solid #fff;color:#039;background:#b9c9fe'><td><b>Teamplate</b></td><td><b>Ghi nhận</b></td><td><b>Valid</b></td><td><b>%Valid</b></td><td><b>Thiếu param</b></td></tr>";

    	String content1="<b>Sản lượng email GATEWAY gửi sang AMAZON tính đến "+hour+"h trong ngày "+DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp())+"</b><br>" +
		"<html><body><table>";
		content1+="<tr style='padding:8px;border-top:1px solid #fff;color:#039;background:#b9c9fe'><td><b>Teamplate</b></td><td><b>Email gửi</b></td><td><b>%Valid</b></td><td><b>Mở</b></td><td><b>%Mở</b></td></tr>";

		DBTool db=new DBTool();
    	ArrayList beans=new ArrayList();

    	String fromDate=DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp());
    	String toDate=fromDate;
    	if(hour>9){
    		toDate+=hour;
    	}else{
    		toDate+="0"+hour;
    	}
    	fromDate+="00";

    	String strSQL="select a.teamplate,a.slsend ,b.valids, b.valids*100/a.slsend tylevalid,c.slteamopen,c.slteamopen*100/b.valids tyleopen,d.thieu from  " +
    			"(select teamplate,count(1) slsend from log_sendemail where  teamplate <> '' and to_char(time_send,'YYYYMMDDHH24')>= '"+fromDate+"' " +
    			"and to_char(time_send,'YYYYMMDDHH24')< '"+toDate+"' group by teamplate)a " +
    			"left join (select teamplate,count(1) valids from log_sendemail where result=1 and teamplate <> '' " +
    			"and to_char(time_send,'YYYYMMDDHH24')>= '"+fromDate+"' and to_char(time_send,'YYYYMMDDHH24')< '"+toDate+"' group by teamplate)b " +
    			"on a.teamplate=b.teamplate	" +
    			"left join (select teamplate,count(1) slteamopen  from log_sendemail a where teamplate <> '' and result=1 " +
    			"and to_char(time_send,'YYYYMMDDHH24')>= '"+fromDate+"' and to_char(time_send,'YYYYMMDDHH24')< '"+toDate+"' and " +
    			"EXISTS(select email_id from log_email b where b.email_id=a.email_id) " +
    			"group by teamplate)c on a.teamplate=c.teamplate " +
    			"left join (select teamplate,count(1) thieu  from log_sendemail a where teamplate <> '' and result=-5 " +
    			"and to_char(time_send,'YYYYMMDDHH24')>= '"+fromDate+"' and to_char(time_send,'YYYYMMDDHH24')< '"+toDate+"' " +
    			"group by teamplate)d on a.teamplate=d.teamplate order by a.slsend desc";    	
    	beans= db.getReportEmailSend(strSQL);
    	BeanReport beanTotal=new BeanReport();
    	String checkTyle="";
    	for (int i = 0; i < beans.size(); i++) {
    		BeanReport bean= (BeanReport)beans.get(i);
    		beanTotal.setSanLuong(beanTotal.getSanLuong()+bean.getSanLuong());
    		beanTotal.setSlValid(beanTotal.getSlValid()+bean.getSlValid());
    		beanTotal.setOpenRate(beanTotal.getOpenRate()+bean.getOpenRate());
    		beanTotal.setThieu(beanTotal.getThieu()+bean.getThieu());
    		checkTyle=bean.getTyleValid()<90?"style='color:red'":"";
        	content+="<tr style='padding:8px;background:#e8edff;border-top:1px solid #fff;color:#669'><td>"+bean.getTeamPlate()+":</td><td align='right'>"+bean.getSanLuong()+"</td>" +
        			"<td align='right'>"+bean.getSlValid()+"</td><td align='right' "+checkTyle+">~"+bean.getTyleValid()+"%</td><td align='right'>"+bean.getThieu()+"</td></tr>";
        	content1+="<tr style='padding:8px;background:#e8edff;border-top:1px solid #fff;color:#669'><td>"+bean.getTeamPlate()+":</td>" +
        			"<td align='right'>"+bean.getSlValid()+"</td><td align='right'>~100%</td><td align='right'>"+bean.getOpenRate()+"</td><td align='right'>~"+bean.getTyleOpen()+"%</td></tr>";
        	
    	}
    	int tyle=0;
    	try {
    		tyle=Math.round(beanTotal.getSlValid()*100/beanTotal.getSanLuong());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			tyle=0;
		}
    	int tyleMo=0;
    	try {
    		tyleMo=Math.round(beanTotal.getOpenRate()*100/beanTotal.getSlValid());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			tyleMo=0;
		}
    	checkTyle=beanTotal.getTyleValid()<90?"style='color:red'":"";
    	content+="<tr style='padding:8px;border-top:1px solid #fff;color:#039;background:#b9c9fe'><td><b>Tổng:</b></td><td align='right'><b>"+beanTotal.getSanLuong()+"</b></td>" +
    			"<td align='right'><b>"+beanTotal.getSlValid()+"</b></td><td align='right'"+checkTyle+"><b>~"+tyle+"%</b></td><td align='right'><b>"+beanTotal.getThieu()+"</b></td></tr>";
    	content+="</table></body></html>";

    	content1+="<tr style='padding:8px;border-top:1px solid #fff;color:#039;background:#b9c9fe'><td><b>Tổng:</b></td>" +
    			"<td align='right'><b>"+beanTotal.getSlValid()+"</b></td><td align='right'><b>~100%</b></td><td align='right'><b>"+beanTotal.getOpenRate()+"</b></td><td align='right'><b>~"+tyleMo+"%</b></td></tr>";
    	content1+="</table></body></html>";

    	beanBR bean=new beanBR();
    	bean.setMessage_ids(UUID.randomUUID().toString());
    	bean.setFrom(WSConfig.emailReport);
    	bean.setCc("");
    	bean.setBcc("");
    	bean.setTo(WSConfig.emailRecReportByH);
    	bean.setSubject(subject);
    	bean.setContent(content+ "<br>" +content1);
    	RequestCallSend.sendEmail(bean);
    	VCSMS.getLogFileQueue().enqueue(bean);
    	VCSMS.getLogDBQueue().enqueue(bean);

    }
    
    public void run()
    {
    	while(VCSMS.isRunning()){
    		if(Integer.parseInt(WSConfig.enableReport)==1){
	//    		System.out.println("processGetUrlPushErrFromDB...");
				Calendar now = Calendar.getInstance();
	            int hour=now.get(Calendar.HOUR_OF_DAY);
	            if(hour>=8 && hour<=23){
		        		try {
		        			processReportEmailSend();
		        			
		    			} catch (Exception e) {
		    				// TODO: handle exception
		    				try {
		    					sleep(100);
		    				} catch (InterruptedException e1) {
		    					// TODO Auto-generated catch block
		    					e1.printStackTrace();
		    				}
		    			}
	            }
	
	    		//sleep recall
				try {
					sleep(60*60*1000);//di ngu 1h
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}else{
	    		try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
    }



}

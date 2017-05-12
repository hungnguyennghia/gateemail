// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DBTool.java

package vc.database;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vc.WSConfig;
import vc.util.BCamp;
import vc.util.BOrder;
import vc.util.BReject;
import vc.util.BReportCamp;
import vc.util.BSku;
import vc.util.BTheadAuto;
import vc.util.BUrlPush;
import vc.util.BeanReport;
import vc.util.beanBR;

public class DBTool extends BaseDAO
{

    public DBTool()
    {
    }

 

    public boolean insert2BR(beanBR bean)
        throws DBException
    {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String strSQL = null;
        boolean result = false;
        try
        {
            conn = DBPool.getConnection();
            strSQL = "INSERT INTO log_sendemail (account, from_mail,to_mail,cc,bcc, subject, content_text,result,resend,message_ids,urlpush,orderid,teamplate,filepaths,email_id,camp_id) VALUES (?, ?, ?,?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
            preStmt = conn.prepareStatement(strSQL);
            preStmt.setString(1, bean.getAccount());
            preStmt.setString(2, bean.getFrom());
            preStmt.setString(3, bean.getTo());
            preStmt.setString(4, bean.getCc());
            preStmt.setString(5, bean.getBcc());
            preStmt.setString(6, bean.getSubject());
            preStmt.setString(7, bean.getContent().replace("\n", "##"));
            preStmt.setInt(8, bean.getResult());
            preStmt.setInt(9, bean.getResend());
            preStmt.setString(10, bean.getMessage_ids());
            preStmt.setString(11, bean.getUrlpush());
            preStmt.setString(12, bean.getOrderid());
            preStmt.setString(13, bean.getTeamplate());
            preStmt.setString(14, bean.getFilePaths());
            preStmt.setString(15, bean.getEmail_id());
            preStmt.setString(16, bean.getCamp_id());
            if(preStmt.executeUpdate() == 1)
                result = true;
        }
        catch(SQLException e)
        {
            System.out.println("insert2BR: log_sendemail "+e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("insert2BR:log_sendemail "+e.getMessage());
        }
        finally
        {
            try
            {
                conn.setAutoCommit(true);
            }
            catch(SQLException sqlexception) { }
            releaseConnection(conn, preStmt);
            
        }
        return result;
    }

    
    public boolean insert2Order(BOrder bean)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "INSERT INTO shoporder (account, email,nganh_hang,orderNumber,loai_sp, gia_sp, tg_mua,productName,customerFullName,billingPhoneNumber,sku,mobileoperator,actives) VALUES (?, ?, ?,?, ?, ?,?, ?,?, ?,?,?,?)";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setString(1, bean.getAccount());
                preStmt.setString(2, bean.getEmail());
                preStmt.setString(3, bean.getNganhHang());
                preStmt.setString(4, bean.getOrderNumber());
                preStmt.setString(5, bean.getLoaiSP());
                preStmt.setLong(6, bean.getGiaSP());
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                cal.setTime(sdf.parse(bean.getTgMuaHang()));// all done                
                preStmt.setTimestamp(7, new java.sql.Timestamp(cal.getTimeInMillis()));
                preStmt.setString(8, bean.getProductName());
                preStmt.setString(9, bean.getCustomerFullName());
                preStmt.setString(10, bean.getBillingPhoneNumber());
                preStmt.setString(11, bean.getSku());
                preStmt.setString(12, bean.getMobileOperator());
                preStmt.setInt(13, bean.getResult());
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("insert2Order: "+e.getMessage());
                e.printStackTrace();
            }
            catch(Exception e)
            {
                System.out.println("insert2Order: "+e.getMessage());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    
    public boolean insert2Camp(BCamp bean)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "INSERT INTO campaignemail (account, namecamp,nganh_hang,loai_sp, gia_tu,gia_den,tg_mua_tu,tg_mua_den, tg_gui,teamplate,subject,content_email,id,mobileoperator,uniqueemail) VALUES (?,?, ?, ?,?, ?, ?,?,?,?,?,?,?,?,?)";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setString(1, bean.getAccount());
                preStmt.setString(2, bean.getNameCamp());
                preStmt.setString(3, bean.getNganhHang());
                preStmt.setString(4, bean.getLoaiSP());
                preStmt.setLong(5, bean.getGiaTu());
                preStmt.setLong(6, bean.getGiaDen());
                
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if(bean.getTgMuaTu().equals("")){
                    preStmt.setTimestamp(7,null);
                }else{
                    cal.setTime(sdf.parse(bean.getTgMuaTu()));                
                    preStmt.setTimestamp(7, new java.sql.Timestamp(cal.getTimeInMillis()));
                }
                if(bean.getTgMuaDen().equals("")){
                    preStmt.setTimestamp(8,null);
                }else{
                    cal.setTime(sdf.parse(bean.getTgMuaDen()));// all done                
                    preStmt.setTimestamp(8, new java.sql.Timestamp(cal.getTimeInMillis()));
                }
                cal.setTime(sdf.parse(bean.getTgGui()));// all done                
                preStmt.setTimestamp(9, new java.sql.Timestamp(cal.getTimeInMillis()));
                preStmt.setString(10, bean.getTeamplate());
                preStmt.setString(11, bean.getSubject());
                preStmt.setString(12, bean.getContent());
                preStmt.setString(13, bean.getRequestID());
                preStmt.setString(14, bean.getMobileoperator());
                preStmt.setInt(15, bean.getUnique());
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("insert2Camp: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("insert2Camp: "+e.getMessage().toString());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    public boolean update2Camp(BCamp bean)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "UPDATE campaignemail set account=?, namecamp=?,nganh_hang=?,loai_sp=?, gia_tu=?,gia_den=?,tg_mua_tu=?,tg_mua_den=?, tg_gui=?,teamplate=?,subject=?,content_email=?,mobileoperator=?,uniqueemail=? where id=?";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setString(1, bean.getAccount());
                preStmt.setString(2, bean.getNameCamp());
                preStmt.setString(3, bean.getNganhHang());
                preStmt.setString(4, bean.getLoaiSP());
                preStmt.setLong(5, bean.getGiaTu());
                preStmt.setLong(6, bean.getGiaDen());
                
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                if(bean.getTgMuaTu().equals("")){
                    preStmt.setTimestamp(7,null);
                }else{
                    cal.setTime(sdf.parse(bean.getTgMuaTu()));                
                    preStmt.setTimestamp(7, new java.sql.Timestamp(cal.getTimeInMillis()));
                }
                if(bean.getTgMuaDen().equals("")){
                    preStmt.setTimestamp(8,null);
                }else{
                    cal.setTime(sdf.parse(bean.getTgMuaDen()));// all done                
                    preStmt.setTimestamp(8, new java.sql.Timestamp(cal.getTimeInMillis()));
                }
                cal.setTime(sdf.parse(bean.getTgGui()));// all done                
                preStmt.setTimestamp(9, new java.sql.Timestamp(cal.getTimeInMillis()));
                preStmt.setString(10, bean.getTeamplate());
                preStmt.setString(11, bean.getSubject());
                preStmt.setString(12, bean.getContent());
                preStmt.setString(13, bean.getMobileoperator());
                preStmt.setInt(14, bean.getUnique());
                preStmt.setString(15, bean.getRequestID());
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("update2Camp: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("update2Camp: "+e.getMessage().toString());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }    
    public boolean updateCampStatus(String id)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "update campaignemail set sended=1,time_done=? where id=?";
                preStmt = conn.prepareStatement(strSQL);
                Calendar cal  = Calendar.getInstance();                    
                preStmt.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
 
                preStmt.setString(2, id);
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("updateCampStatus: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("updateCampStatus: "+e.getMessage());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    
    
    public boolean updateShopOrderNganhHang(String nganh_hang, String loai_sp,String sku,int time)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
      //      	int timeT=time+1;
                conn = DBPool.getConnection();
                strSQL = "update shoporder set nganh_hang=?,loai_sp=? where sku=? and timecreate >=now() - interval '"+time+" day'";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setString(1, nganh_hang);
                preStmt.setString(2, loai_sp);
                preStmt.setString(3, sku);
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("updateShopOrderNganhHang: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("updateShopOrderNganhHang: "+e.getMessage());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    
    public boolean updateShopOrderScan(BOrder bean)
            throws DBException
        {

            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "update shoporder set actives=? where UPPER(email)=? ";
                preStmt = conn.prepareStatement(strSQL);
                preStmt.setInt(1, bean.getResult());
                preStmt.setString(2, bean.getEmail().toUpperCase());
                if(preStmt.executeUpdate() >= 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("updateShopOrderScan: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("updateShopOrderScan: "+e.getMessage());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    	public ArrayList getAllRecord() {
    		
    		ArrayList beans=new ArrayList();
    		beanBR mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "SELECT from_mail,to_mail,cc,bcc, subject, content_text,message_ids,filepaths,urlpush,resend+1 resend FROM log_sendemail a where result=5 and resend<3 and time_send >now() - interval '1 day' and time_send <now() - interval '2 hour' limit 100";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new beanBR();
    				mo.setFrom(rs.getString(1));
    				mo.setTo(rs.getString(2));
    				mo.setCc(rs.getString(3));
    				mo.setBcc(rs.getString(4));
    				mo.setSubject(rs.getString(5));
    				try {
        				mo.setContent(rs.getString(6).replace("##", "\n"));

					} catch (Exception e) {
						// TODO: handle exception
					}
    				mo.setMessage_ids(rs.getString(7));
    				try {
						String file=rs.getString(8);
						String files[]=file.split("[|]");
	    				mo.setAttach(!file.equals("")?files:null);
					} catch (Exception e) {
						// TODO: handle exception
					}


    				mo.setUrlpush(rs.getString(9));
    				mo.setResend(rs.getInt(10));
     				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	//lay ra cac ban ghi chua request duoc sang EcomApp(cap nhat trang thai email)
    	//lay ban ghi loi trong 1 ngay
    	public ArrayList getAllRecordUrlPush() {
    		
    		ArrayList beans=new ArrayList();
    		BUrlPush mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();
    			strSQL = "SELECT message_ids,requestid,errorcode,errormessage,result,urlpush,resend FROM log_sendemail a where statuspush=0 and result <> 0 and requestid !='' and urlpush is not null and time_send >now() - interval '1 day' and time_send <now() - interval '1 hour' order by id asc";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new BUrlPush();
    				mo.setMessage_ids(rs.getString(1));
    				mo.setRequestId(rs.getString(2));
    				mo.setErrorCode(rs.getString(3));
    				mo.setErrorMessage(rs.getString(4));
    				mo.setResult(rs.getInt(5));
    				mo.setUrlpush(rs.getString(6));
    				mo.setResend(rs.getInt(7));
    				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	
 
        
        public boolean updateUrlpushStatus(BUrlPush bean)
                throws DBException
            {
                Connection conn = null;
                PreparedStatement preStmt = null;
                String strSQL = null;
                boolean result = false;
                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "update log_sendemail set statuspush=?,time_updateurl=?, requestid=?,errorcode=?,errormessage=?,result=?, resend=? where message_ids='"+bean.getMessage_ids()+"'";
                    preStmt = conn.prepareStatement(strSQL);
                    preStmt.setLong(1, bean.getStatusPush());
//                    Calendar cal  = Calendar.getInstance();                    
                    preStmt.setTimestamp(2, new java.sql.Timestamp(bean.getTimeSend()));
                     
                     preStmt.setString(3,bean.getRequestId());
                     preStmt.setString(4,bean.getErrorCode());
                     preStmt.setString(5,bean.getErrorMessage());

                     preStmt.setInt(6, bean.getResult());
                     preStmt.setInt(7, bean.getResend());
                   //  preStmt.setTimestamp(8, new java.sql.Timestamp(bean.getTimeSend()));
                  //  preStmt.setString(8, bean.getMessage_ids());
                    if(preStmt.executeUpdate() >= 1)
                        result = true;
                }
                catch(SQLException e)
                {
                    System.out.println("updateUrlpushStatus: "+e.getMessage());
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    System.out.println("updateUrlpushStatus: "+e.getMessage());
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException sqlexception) { }
                    releaseConnection(conn, preStmt);
                    
                }
                return result;
            }
        public boolean updateUrlpushStatusMulti(ArrayList beans)
                throws DBException
            {
                Connection conn = null;
                PreparedStatement preStmt = null;
                String strSQL = null;
                boolean result = false;
                
                
                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "update log_sendemail set statuspush=?,time_updateurl=?, requestid=?,errorcode=?,errormessage=?,result=?, resend=? where message_ids=?";
                    preStmt = conn.prepareStatement(strSQL);
                    
	                for (int i = 0; i < beans.size(); i++) {
	                	BUrlPush bean=(BUrlPush)beans.get(i);
	                    preStmt.setLong(1, bean.getStatusPush());
	                    preStmt.setTimestamp(2, new java.sql.Timestamp(bean.getTimeSend()));
	                     preStmt.setString(3,bean.getRequestId());
	                     preStmt.setString(4,bean.getErrorCode());
	                     preStmt.setString(5,bean.getErrorMessage());

	                     preStmt.setInt(6, bean.getResult());
	                     preStmt.setInt(7, bean.getResend());
	                    preStmt.setString(8, bean.getMessage_ids());
		                preStmt.addBatch();
					}

	                preStmt.executeBatch();
                }
                catch(SQLException e)
                {
                    System.out.println("updateUrlpushStatus: "+e.getMessage());
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    System.out.println("updateUrlpushStatus: "+e.getMessage());
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException sqlexception) { }
                    releaseConnection(conn, preStmt);
                    
                }
                return result;
            }       
    	public long getId() {
    		
    		long id=0;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "SELECT max(id) FROM log_sendemail";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				id=rs.getLong(1);
    				
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return id;
    	}
    	public boolean checkEmailReject(beanBR bean) {
    		
    		boolean result=false;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "SELECT count(1) FROM log_email_reject where ? like '%'||email||'%'";
    			preStmt = conn.prepareStatement(strSQL);
    			preStmt.setString(1, bean.getTo());
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				result=rs.getLong(1)>0;
    				
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return result;
    	}
    	

    	
    	public ArrayList getReportEmailSend(String strSQL) {
    		
    		ArrayList beans=new ArrayList();
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		BeanReport bean =new BeanReport();
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				bean =new BeanReport();
    				bean.setTeamPlate(rs.getString(1));
    				bean.setSanLuong(rs.getLong(2));
    				bean.setSlValid(rs.getLong(3));
    				bean.setTyleValid(rs.getInt(4));
    				bean.setOpenRate(rs.getLong(5));
    				bean.setTyleOpen(rs.getInt(6));
    				bean.setThieu(rs.getLong(7));
    				beans.add(bean);
    				
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	
    	public ArrayList getAllEmailOpenRate() {
    		
    		ArrayList beans=new ArrayList();
    		beanBR mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "SELECT distinct email from log_email where email like '%@%'";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new beanBR();
    				mo.setTo(rs.getString(1));
    				mo.setResult(1);
    				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	public ArrayList getAllEmailReject() {
    		
    		ArrayList beans=new ArrayList();
    		beanBR mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "select distinct email from log_email_reject where email like '%@%'  and getdate >=now() - interval '1 day'";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new beanBR();
    				mo.setTo(rs.getString(1));
    				mo.setResult(-1);
    				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	
    	public ArrayList getAllEmailValid() {
    		
    		ArrayList beans=new ArrayList();
    		beanBR mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "select distinct trim(lower(email)) from email_valid where isvalid=1";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new beanBR();
    				mo.setTo(rs.getString(1));
    				mo.setResult(1);
    				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	
    	public ArrayList getAllEmailShopOrder(String fdate, String tdate) {
    		
    		ArrayList beans=new ArrayList();
    		BOrder mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();

    			strSQL = "select distinct on(email) ordernumber, productname, customerfullname, billingphonenumber, email, nganh_hang, loai_sp, gia_sp,sku,account,mobileoperator,tg_mua" +
    					" from shoporder where actives=0 and timecreate >= to_timestamp('"+fdate+"','yyyymmdd') and timecreate < to_timestamp('"+tdate+"','yyyymmdd')";
    			preStmt = conn.prepareStatement(strSQL);
    			
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				mo = new BOrder();
     				mo.setOrderNumber(rs.getString(1));
    				mo.setProductName(rs.getString(2));
    				mo.setCustomerFullName(rs.getString(3));
    				mo.setBillingPhoneNumber(rs.getString(4));
    				mo.setEmail(rs.getString(5));
    				mo.setNganhHang(rs.getString(6));
    				mo.setLoaiSP(rs.getString(7));
    				mo.setGiaSP(rs.getLong(8));
    				mo.setSku(rs.getString(9));
    				mo.setScan(1);
    				mo.setAccount(rs.getString(10));
    				mo.setMobileOperator(rs.getString(11));
    				mo.setTgMuaHang(rs.getString(12));
    				beans.add(mo);
    			}
    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
    	
        public boolean updateEmailValid(beanBR bean)
                throws DBException
            {
                Connection conn = null;
                PreparedStatement preStmt = null;
                String strSQL = null;
                boolean result = false;
                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "update email_valid set isvalid=? where lower(email)=?";
                    preStmt = conn.prepareStatement(strSQL);
                    preStmt.setLong(1, bean.getResult());
                    preStmt.setString(2, bean.getTo());
                    if(preStmt.executeUpdate() == 1)
                        result = true;
                }
                catch(SQLException e)
                {
                    System.out.println("updateEmailValid: "+e.getMessage());
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    System.out.println("updateEmailValid: "+e.getMessage());
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException sqlexception) { }
                    releaseConnection(conn, preStmt);
                    
                }
                return result;
            }
        
        public String getStatusBR_ByID(String id)
                throws DBException
            {
                Connection conn;
                PreparedStatement preStmt;
                Statement stmt;
                ResultSet rs;
                String result;
                conn = null;
                preStmt = null;
                stmt = null;
                rs = null;
                String strSQL = null;
                result = "-1";
                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "SELECT result,to_char(time_send,'yyyymmddhh24miss') time_send FROM log_sendemail a where id="+id;
                    preStmt = conn.prepareStatement(strSQL);
        			
        			rs = preStmt.executeQuery();
        			
        			while((rs != null) && (rs.next())){
        				result = rs.getString("result")+"_"+rs.getString("time_send");
        			}                

                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                releaseConnection(conn, preStmt, stmt, rs);
                return result;
            }
        
        public BReportCamp getReportCamp_ByID(String id,int type)
                throws DBException
            {
                Connection conn;
                PreparedStatement preStmt;
                Statement stmt;
                ResultSet rs;
                BReportCamp bean =new BReportCamp();
                conn = null;
                preStmt = null;
                stmt = null;
                rs = null;
                String strSQL = null;
try {
	String temps[]=id.split("_");
	if(temps[0].equals("Email.EBilling")){
		type=3;
	}
} catch (Exception e) {
	// TODO: handle exception
}
                try
                {
                    conn = DBPool.getConnection();
                    String sqlType="select id camp_id,to_char(tg_gui,'YYYYMMDDHH24MISS') time_send, to_char(time_done,'YYYYMMDDHH24MISS') time_done  from campaignemail";
                    if(type==1){
                    	sqlType="select id camp_id,to_char(time_send,'YYYYMMDDHH24MISS') time_send, to_char(time_done,'YYYYMMDDHH24MISS') time_done  from auto_report";
                    }
                    strSQL = "select a.camp_id,a.time_send,a.time_done,a1.slsend ,b.valids, b.valids*100/a1.slsend tylevalid,c.slteamopen,c.slteamopen*100/b.valids tyleopen,d.thieu,e.slclicklink from  "+
    			" ( "+sqlType+" where id='"+id+"')a  "+
    			" left join "+
    			" (select camp_id,count(distinct email_id) slsend from log_sendemail where camp_id='"+id+"' group by camp_id)a1  "+
    			" on a.camp_id=a1.camp_id "+
    			" left join (select camp_id,count(distinct email_id) valids from log_sendemail where result=1 and camp_id='"+id+"' group by camp_id)b  "+
    			" on a.camp_id=b.camp_id	 "+
    			" left join ( select '"+id+"'::character(200) camp_id,count(distinct email_id) slteamopen from log_email b where camp_id='"+id+"')c on a.camp_id=c.camp_id  "+
    			" left join (select camp_id,count(distinct email_id) thieu  from log_sendemail a where result=-5  "+
    			" and camp_id='"+id+"'"+ 
    			" group by camp_id)d on a.camp_id=d.camp_id " +
    			"left join (select camp_id,count(distinct email_id) slclicklink  from log_sendemail a where result=1 and camp_id='"+id+"' and EXISTS(select email_id from log_click_link b where b.email_id=a.email_id) group by camp_id)e on a.camp_id=e.camp_id ";
                    if(type==3){
                    	String temps[]=id.split("_");
                    	strSQL = "select a.teamplate,a.time_send,a.time_done,a.slsend,b.valids,c.slteamopen,d.slclicklink from(" +
								" select '"+temps[0]+"'::text teamplate,to_char(min(time_send),'YYYYMMDDHH24MISS') time_send,to_char(max(time_send),'YYYYMMDDHH24MISS') time_done, count(1) slsend from log_sendemail where  teamplate <> '' " +
								" and time_send >= to_timestamp('"+temps[1]+"','YYYYMMDDHH24')" +
								" and time_send < to_timestamp('"+temps[1]+"','YYYYMMDDHH24')+interval '1 day'" +
								" and teamplate='"+temps[0]+"'" +
								" )a" +
								" left join (" +
								" select '"+temps[0]+"'::text teamplate,count(distinct a.email_id) valids  from log_sendemail a " +
								" where teamplate <> '' and result=1 " +
								" and time_send >= to_timestamp('"+temps[1]+"','YYYYMMDDHH24')" +
								" and time_send < to_timestamp('"+temps[1]+"','YYYYMMDDHH24')+interval '1 day'" +
								" and teamplate='"+temps[0]+"'" +
								" )b on a.teamplate=b.teamplate " +
								" left join (" +
								" select '"+temps[0]+"'::text teamplate,count(distinct a.email_id) slteamopen  from log_sendemail a " +
								" inner join log_email b on b.email_id=a.email_id" +
								" where teamplate <> '' and result=1 " +
								" and time_send >= to_timestamp('"+temps[1]+"','YYYYMMDDHH24')" +
								" and time_send < to_timestamp('"+temps[1]+"','YYYYMMDDHH24')+interval '1 day'" +
								" and teamplate='"+temps[0]+"'" +
								" )c on a.teamplate=c.teamplate " +
								" left join (" +
								" select '"+temps[0]+"'::text teamplate,count(distinct a.email_id) slclicklink  from log_sendemail a " +
								" inner join log_click_link b on b.email_id=a.email_id" +
								" where teamplate <> '' and result=1 " +
								" and time_send >= to_timestamp('"+temps[1]+"','YYYYMMDDHH24')" +
								" and time_send < to_timestamp('"+temps[1]+"','YYYYMMDDHH24')+interval '1 day'" +
								" and teamplate='"+temps[0]+"'" +
								" )d on a.teamplate=d.teamplate ";
 	
                    }
                    preStmt = conn.prepareStatement(strSQL);
        			System.out.println(strSQL);
        			rs = preStmt.executeQuery();
        			bean.setErrorCode(0);
        			while((rs != null) && (rs.next())){
            			bean.setErrorCode(1);
        				bean.setTotal_email(rs.getInt("slsend"));
        				bean.setValid_email(rs.getInt("valids"));
        				bean.setOpen_email(rs.getInt("slteamopen"));
        				bean.setDate_send(rs.getString("time_send"));
        				bean.setDate_done(rs.getString("time_done"));
        				bean.setClick_link(rs.getInt("slclicklink"));
        				
        			}                

                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                releaseConnection(conn, preStmt, stmt, rs);
                return bean;
            }   
        
        
        public BCamp getCamp_ByID(String id)
                throws DBException
            {
                Connection conn;
                PreparedStatement preStmt;
                Statement stmt;
                ResultSet rs;
                BCamp bean =new BCamp();
                conn = null;
                preStmt = null;
                stmt = null;
                rs = null;
                String strSQL = null;

                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "select id, account, namecamp, nganh_hang, loai_sp, gia_tu, gia_den,to_char(tg_mua_tu,'YYYYMMDDHH24MISS') tg_mua_tu, " +
                    		"to_char(tg_mua_den,'YYYYMMDDHH24MISS') tg_mua_den, to_char(tg_gui,'YYYYMMDDHH24MISS') tg_gui, " +
                    		"teamplate, subject, content_email from campaignemail where id='"+id+"'";
                    preStmt = conn.prepareStatement(strSQL);
//        			System.out.println(strSQL);
        			rs = preStmt.executeQuery();
        			while((rs != null) && (rs.next())){
        				bean.setRequestID(rs.getString("id"));
            			bean.setAccount(rs.getString("account"));
        				bean.setNameCamp(rs.getString("namecamp"));
        				bean.setNganhHang(rs.getString("nganh_hang"));
        				bean.setLoaiSP(rs.getString("loai_sp"));
        				bean.setGiaTu(rs.getLong("gia_tu"));
        				bean.setGiaDen(rs.getLong("gia_den"));
        				bean.setTgMuaTu(rs.getString("tg_mua_tu"));
        				bean.setTgMuaDen(rs.getString("tg_mua_den"));
        				bean.setTgGui(rs.getString("tg_gui"));
        				bean.setTeamplate(rs.getString("teamplate"));
        				bean.setSubject(rs.getString("subject"));
        				bean.setContent(rs.getString("content_email"));
        			}                

                }
                catch(SQLException e)
                {
                    System.out.println(e.toString());
                }
                releaseConnection(conn, preStmt, stmt, rs);
                return bean;
            }  
        
        public boolean deleteCamp_byID(String id)
                throws DBException
            {
                Connection conn = null;
                PreparedStatement preStmt = null;
                String strSQL = null;
                boolean result = false;
                try
                {
                    conn = DBPool.getConnection();
                    strSQL = "UPDATE campaignemail set sended=-1 WHERE id=?";
                    preStmt = conn.prepareStatement(strSQL);
                    preStmt.setString(1, id);
                    if(preStmt.executeUpdate() == 1)
                        result = true;
                }
                catch(SQLException e)
                {
                    System.out.println("deleteCamp_byID: "+e.getMessage());
                    e.printStackTrace();
                }
                catch(Exception e)
                {
                    System.out.println("deleteCamp_byID: "+e.getMessage());
                }
                finally
                {
                    try
                    {
                        conn.setAutoCommit(true);
                    }
                    catch(SQLException sqlexception) { }
                    releaseConnection(conn, preStmt);
                    
                }
                return result;
            }
        
        
	public ArrayList getAllEmailByCamp(BCamp bean) {
    		
    		ArrayList beans=new ArrayList();
    		BOrder mo = null;
    		java.sql.Connection conn = null;
    		PreparedStatement preStmt = null;
    		String strSQL = null;
    		ResultSet rs = null;
    		try {
    			conn = DBPool.getConnection();
    			String sqlW="";
    			String sqlJoin="";
    			if(!bean.getMobileoperator().equals("")){
    				sqlW+=" and mobileoperator='"+bean.getMobileoperator()+"'";
    			}
    			if(!bean.getNganhHang().equals("")){
    				insert2SkuTemp(conn,bean.getNganhHang());
    				//		sqlW+=" and nganh_hang in ("+bean.getNganhHang()+")";
    				//		sqlW+=" and exists(select sku FROM sku b where b.SKU=a.sku)";
    						sqlJoin=" inner join sku b on b.sku=a.sku ";
    			}
    			if(!bean.getLoaiSP().equals("0")){
    				sqlW+=" and loai_sp='"+bean.getLoaiSP()+"'";
    			}
    			if(bean.getGiaTu()>0){
    				sqlW+=" and gia_sp>="+bean.getGiaTu();
    			}
    			if(bean.getGiaDen()>0){
    				sqlW+=" and gia_sp<="+bean.getGiaDen();
    			}
    			if(!bean.getTgMuaTu().equals("")){
    				sqlW+=" and tg_mua>= to_timestamp('"+bean.getTgMuaTu()+"','yyyymmddhh24miss')";
    			}
    			if(!bean.getTgMuaDen().equals("")){
    				sqlW+=" and tg_mua<= to_timestamp('"+bean.getTgMuaDen()+"','yyyymmddhh24miss')";
    			}
    			String tables="shoporder a";
    			if(bean.getSendTest()==0){
    				tables="shoporder_test a";
    			}
				String sqlUnique="DISTINCT on (email,nganh_hang)";
				String sqlOrder="order by email,nganh_hang,tg_mua asc";
				if(bean.getUnique()==0){
					sqlUnique="DISTINCT on (email)";
					sqlOrder="order by email,tg_mua asc";
    			}
    			strSQL = "select "+sqlUnique+" ordernumber, productname, customerfullname, billingphonenumber, email, nganh_hang, loai_sp, gia_sp from "+tables + sqlJoin +" where a.actives=1" + sqlW;
    			String limit=" limit "+WSConfig.campLimit;
				strSQL+=sqlOrder;
				
				if(bean.getOffset()>0){
					limit+=" OFFSET "+bean.getOffset();
				}

    				strSQL+=limit;
        			preStmt = conn.prepareStatement(strSQL);
        			System.err.println("[getAllEmailByCamp]"+strSQL);
        			rs = preStmt.executeQuery();
        			
        			while((rs != null) && (rs.next())){
        				mo = new BOrder();
        				mo.setOrderNumber(rs.getString(1));
        				mo.setProductName(rs.getString(2));
        				mo.setCustomerFullName(rs.getString(3));
        				mo.setBillingPhoneNumber(rs.getString(4));
        				mo.setEmail(rs.getString(5));
        				mo.setNganhHang(rs.getString(6));
        				mo.setLoaiSP(rs.getString(7));
        				mo.setGiaSP(rs.getLong(8));
        				beans.add(mo);
        			}    				
    			

    		} catch (SQLException e) {
    			
    		} catch (Exception e) {
    			
    		} finally {
    			releaseConnection(conn, preStmt, rs);
    			
    		}
    		return beans;
    	}
	
	public BCamp getCountAllEmailByCamp(BCamp bean) {

		java.sql.Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		try {
			conn = DBPool.getConnection();
			String sqlW="";
			String sqlJoin="";
			if(!bean.getMobileoperator().equals("")){
				sqlW+=" and mobileoperator='"+bean.getMobileoperator()+"'";
			}
			if(!bean.getNganhHang().equals("")){
				insert2SkuTemp(conn,bean.getNganhHang());
		//		sqlW+=" and nganh_hang in ("+bean.getNganhHang()+")";
			//	sqlW+=" and exists(select sku FROM sku b where b.SKU=a.sku)";
				sqlJoin=" inner join sku b on b.sku=a.sku ";
			}
			if(!bean.getLoaiSP().equals("0")){
				sqlW+=" and loai_sp='"+bean.getLoaiSP()+"'";
			}
			if(bean.getGiaTu()>0){
				sqlW+=" and gia_sp>="+bean.getGiaTu();
			}
			if(bean.getGiaDen()>0){
				sqlW+=" and gia_sp<="+bean.getGiaDen();
			}
			if(!bean.getTgMuaTu().equals("")){
				sqlW+=" and tg_mua>= to_timestamp('"+bean.getTgMuaTu()+"','yyyymmddhh24miss')";
			}
			if(!bean.getTgMuaDen().equals("")){
				sqlW+=" and tg_mua<= to_timestamp('"+bean.getTgMuaDen()+"','yyyymmddhh24miss')";
			}
			String tables="shoporder a";
			if(bean.getSendTest()==0){
				tables="shoporder_test a";
			}
			strSQL = "select count(1) from (select DISTINCT email, nganh_hang from "+tables+sqlJoin +" where a.actives=1 "+ sqlW+")a " ;
			if(bean.getUnique()==0){
    			strSQL = "select count(DISTINCT email) from "+tables+sqlJoin +" where a.actives=1" + sqlW;
			}
    			preStmt = conn.prepareStatement(strSQL);
//    			System.err.println("[getCountAllEmailByCamp]"+strSQL);
    			rs = preStmt.executeQuery();
    			
    			while((rs != null) && (rs.next())){
    				bean.setCountRows(rs.getLong(1));
    			}


		} catch (SQLException e) {
			
		} catch (Exception e) {
			
		} finally {
			releaseConnection(conn, preStmt, rs);
			
		}
		return bean;
	}	
	//auto
	
	public ArrayList getAllSku(int datetime) {
		
		ArrayList beans=new ArrayList();
		java.sql.Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		BSku bean=new BSku();
		try {
			conn = DBPool.getConnection();
int timeT=datetime-1;
			strSQL = "SELECT distinct sku FROM shoporder a where actives=1 and tg_mua >=date_trunc('day', now() - interval '"+datetime+" day') and tg_mua <date_trunc('day', now() - interval '"+timeT+" day')";
			preStmt = conn.prepareStatement(strSQL);
			
			rs = preStmt.executeQuery();
			
			while((rs != null) && (rs.next())){
				bean=new BSku();
				bean.setSku(rs.getString(1));
				bean.setDateD(datetime);
 				beans.add(bean);
			}
		} catch (SQLException e) {
			
		} catch (Exception e) {
			
		} finally {
			releaseConnection(conn, preStmt, rs);
			
		}
		return beans;
	}
	
	public ArrayList getAllOrderBySku(int datetime,String sku) {
		
		ArrayList beans=new ArrayList();
		java.sql.Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		BOrder bean=new BOrder();
		try {
			conn = DBPool.getConnection();
int timeT=datetime-1;
			strSQL = "SELECT distinct ordernumber, productname, customerfullname, billingphonenumber,email, gia_sp, to_char(tg_mua,'dd/mm/yyyy'), sku FROM shoporder where actives=1 and sku='"+sku+"' and tg_mua >=date_trunc('day', now() - interval '"+datetime+" day') and tg_mua <date_trunc('day', now() - interval '"+timeT+" day')";
			preStmt = conn.prepareStatement(strSQL);
			
			rs = preStmt.executeQuery();
			
			while((rs != null) && (rs.next())){
				bean=new BOrder();
				bean.setOrderNumber(rs.getString(1));
				bean.setProductName(rs.getString(2));
				bean.setCustomerFullName(rs.getString(3));
				bean.setBillingPhoneNumber(rs.getString(4));
				bean.setEmail(rs.getString(5));
				bean.setGiaSP(rs.getLong(6));
				bean.setTgMuaHang(rs.getString(7));
				bean.setSku(rs.getString(8));
 				beans.add(bean);
			}
		} catch (SQLException e) {
			
		} catch (Exception e) {
			
		} finally {
			releaseConnection(conn, preStmt, rs);
			
		}
		return beans;
	}
//	lay tat ca thead auto
	public ArrayList getAllTheadAuto(String type_teamp) {
		
		ArrayList beans=new ArrayList();
		java.sql.Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		BTheadAuto bean=new BTheadAuto();
		try {
			conn = DBPool.getConnection();
			strSQL = "SELECT number_date,type_teamp FROM thead_auto where actives=1 and type_teamp in ("+type_teamp+")";
			preStmt = conn.prepareStatement(strSQL);
			
			rs = preStmt.executeQuery();
			
			while((rs != null) && (rs.next())){
				bean=new BTheadAuto();
				bean.setNumber_date(rs.getInt(1));
				bean.setType_Teamp(rs.getInt(2));
 				beans.add(bean);
			}
		} catch (SQLException e) {
			
		} catch (Exception e) {
			
		} finally {
			releaseConnection(conn, preStmt, rs);
			
		}
		return beans;
	}
	
    public boolean insert2ReportAuto(String id,int type_temp)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "INSERT INTO auto_report (time_send,id,id_temp) VALUES (?,?,?)";
                preStmt = conn.prepareStatement(strSQL);
                Calendar cal  = Calendar.getInstance();                    
                preStmt.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
                preStmt.setString(2, id);
                preStmt.setInt(3, type_temp);
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("insert2ReportAuto: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("insert2ReportAuto: "+e.getMessage().toString());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
 
    public boolean updateReportAutoStatus(String id)
            throws DBException
        {
            Connection conn = null;
            PreparedStatement preStmt = null;
            String strSQL = null;
            boolean result = false;
            try
            {
                conn = DBPool.getConnection();
                strSQL = "update auto_report set sended=1,time_done=? where id=?";
                preStmt = conn.prepareStatement(strSQL);
                Calendar cal  = Calendar.getInstance();                    
                preStmt.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
 
                preStmt.setString(2, id);
                if(preStmt.executeUpdate() == 1)
                    result = true;
            }
            catch(SQLException e)
            {
                System.out.println("updateReportAutoStatus: Error executing "+e.getMessage());
            }
            catch(Exception e)
            {
                System.out.println("updateReportAutoStatus: "+e.getMessage());
            }
            finally
            {
                try
                {
                    conn.setAutoCommit(true);
                }
                catch(SQLException sqlexception) { }
                releaseConnection(conn, preStmt);
                
            }
            return result;
        }
    
    
	public List getAllReportAuto(int typyTemp, String date) {
		
		List beans=new ArrayList();
		java.sql.Connection conn = null;
		PreparedStatement preStmt = null;
		String strSQL = null;
		ResultSet rs = null;
		BReportCamp bean=new BReportCamp();
		try {
			conn = DBPool.getConnection();
			strSQL = "SELECT id,to_char(time_send,'yyyymmddhh24miss'),to_char(time_done,'yyyymmddhh24miss') FROM auto_report a where time_send >=to_timestamp('"+date+"000000','yyyymmddhh24miss') and time_send <=to_timestamp('"+date+"235959','yyyymmddhh24miss') and id_temp="+typyTemp;
			preStmt = conn.prepareStatement(strSQL);
			
			rs = preStmt.executeQuery();
			
			while((rs != null) && (rs.next())){
				bean=new BReportCamp();
				bean.setRequest_id(rs.getString(1));
				bean.setDate_send(rs.getString(2));
				bean.setDate_done(rs.getString(3));
 				beans.add(bean);
			}
		} catch (SQLException e) {
			
		} catch (Exception e) {
			
		} finally {
			releaseConnection(conn, preStmt, rs);
			
		}
		return beans;
	}
	
	   public boolean insert2SkuTemp(Connection conn,String skus)
	            throws DBException
	        {
	      
	            PreparedStatement preStmt = null;
	            PreparedStatement preStmtDelete = null;
	            String strSQL = null;
	            boolean result = false;
	            String[] skuList=skus.split(",");
	            
	            try
	            {
	                preStmtDelete = conn.prepareStatement("TRUNCATE sku");
	                preStmtDelete.execute();
	                
	                strSQL = "INSERT INTO sku (sku) VALUES (?)";
	                preStmt = conn.prepareStatement(strSQL);
	                for (int i = 0; i < skuList.length; i++) {
		                preStmt.setString(1, skuList[i]);
		                preStmt.addBatch();
					}

	                preStmt.executeBatch();
	    			
	           //     preStmt.addBatch();
//	                if(preStmt.executeUpdate() == 1)
//	                    result = true;
	            }
	            catch(SQLException e)
	            {
	                System.out.println("insert2SkuTemp: Error executing "+e.getMessage());
	            }
	            catch(Exception e)
	            {
	                System.out.println("insert2SkuTemp: "+e.getMessage().toString());
	            }
	            finally
	            {
	                releaseConnection(conn, preStmt);
	                
	            }
	            
	            return result;
	        }
	   
	   
	   public boolean insert2Reject(BReject bean)
	            throws DBException
	        {
	            Connection conn = null;
	            PreparedStatement preStmt = null;
	            String strSQL = null;
	            boolean result = false;
	            try
	            {
	                conn = DBPool.getConnection();
	                strSQL = "INSERT INTO log_email_reject (email, getdate,status) VALUES (?,?,?)";
	                preStmt = conn.prepareStatement(strSQL);
	                preStmt.setString(1, bean.getEmail());
	                
	                Calendar cal = Calendar.getInstance();
	              //  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	                  //  cal.setTime(sdf.parse(bean.getGendate()));                
	                    preStmt.setTimestamp(2, new java.sql.Timestamp(cal.getTimeInMillis()));
	                preStmt.setInt(3, 1);
	                if(preStmt.executeUpdate() == 1)
	                    result = true;
	            }
	            catch(SQLException e)
	            {
	                System.out.println("insert2Reject: Error executing "+e.getMessage());
	            }
	            catch(Exception e)
	            {
	                System.out.println("insert2Reject: "+e.getMessage().toString());
	            }
	            finally
	            {
	                try
	                {
	                    conn.setAutoCommit(true);
	                }
	                catch(SQLException sqlexception) { }
	                releaseConnection(conn, preStmt);
	                
	            }
	            return result;
	        }
	   
}

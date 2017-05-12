
package vc.util.logger;

import java.io.*;
import java.util.Base64;

import vc.common.LoggerConfig;
import vc.util.BCamp;
import vc.util.BOrder;
import vc.util.DateProc;
import vc.util.beanBR;

public class FileLogger
{

    public FileLogger()
    {
        File dir = new File(LoggerConfig.MO_LOG_FOLDER);
        if(!dir.exists())
            dir.mkdir();
        dir = new File(LoggerConfig.MO_ERR_LOG_FOLDER);
        if(!dir.exists())
            dir.mkdir();
    }

    public void logMO_ERR(beanBR moData)
    {
        String mo_err_log_folder = LoggerConfig.MO_ERR_LOG_FOLDER;
        File dir = new File(mo_err_log_folder);
        if(!dir.exists())
            dir.mkdirs();
        mo_err_log_filename = "logDB.err";
        try
        {
        	String file=new StringBuilder(String.valueOf(mo_err_log_folder)).append("/").append(mo_log_filename).toString();
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF8"));
            fout.print((new StringBuilder(String.valueOf(moData.getMessage_ids()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getAccount()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getFrom()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTo()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getCc()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getBcc()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getSubject()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getResult()))).append("|").toString());
            fout.print(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()));
            fout.print((new StringBuilder(String.valueOf(moData.getContent().replace("\n", "##")))).toString());
            fout.println();
            fout.flush();
            fout.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("FileLogger.logMO_ERR: path file --> ")).append(mo_err_log_folder).append("/").append(mo_err_log_filename).append(" with ex: ").append(ex.getMessage()).toString());
        }
    }

    public void logMO(beanBR moData)
    {
        String mo_log_folder = LoggerConfig.MO_LOG_FOLDER;
        File dir = new File(mo_log_folder);
        if(!dir.exists())
            dir.mkdir();
        mo_log_filename = (new StringBuilder(String.valueOf(DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp()))).append(".mo").toString());
        try
        {
        	String file=new StringBuilder(String.valueOf(mo_log_folder)).append("/").append(mo_log_filename).toString();
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF8"));
            fout.print((new StringBuilder(String.valueOf(moData.getMessage_ids()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getAccount()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getFrom()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTo()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getSubject()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getResult()))).append("|").toString());
            fout.print(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()));
            fout.print((new StringBuilder(String.valueOf(moData.getContent().replace("\n", "##")))).toString());
            fout.println();
            fout.flush();
            fout.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("FileLogger.logMO: ")).append(ex.getMessage()).toString());
        }
    }

    public void logOrder(BOrder moData)
    {
        String mo_log_folder = LoggerConfig.LOG_ORDER_FOLDER;
        File dir = new File(mo_log_folder);
        if(!dir.exists())
            dir.mkdir();
        mo_log_filename = (new StringBuilder(String.valueOf(DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp()))).append(".mo").toString());
        try
        {
        	String file=new StringBuilder(String.valueOf(mo_log_folder)).append("/").append(mo_log_filename).toString();
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF8"));
            fout.print((new StringBuilder(String.valueOf(moData.getAccount()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getOrderNumber()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getProductName()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getCustomerFullName()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getBillingPhoneNumber()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getEmail()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getNganhHang()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getLoaiSP()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getGiaSP()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgMuaHang()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getResult()))).append("|").toString());
            fout.print(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()));
            fout.println();
            fout.flush();
            fout.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("FileLogger.logMO: ")).append(ex.getMessage()).toString());
        }
    }
    
    public void logCamp(BCamp moData)
    {
        String mo_log_folder = LoggerConfig.LOG_CAMP_FOLDER;
        File dir = new File(mo_log_folder);
        if(!dir.exists())
            dir.mkdir();
        Base64.Encoder enc= Base64.getEncoder();
        mo_log_filename = (new StringBuilder(String.valueOf(DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp()))).append(".log").toString());
        try
        {
        	String file=new StringBuilder(String.valueOf(mo_log_folder)).append("/").append(mo_log_filename).toString();
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF8"));
            byte[] subjectenc =enc.encode(moData.getSubject().getBytes("UTF-8"));
            byte[] contentenc =enc.encode(moData.getContent().getBytes("UTF-8"));
            fout.print((new StringBuilder(String.valueOf(moData.getAccount()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getNameCamp()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getNganhHang()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getLoaiSP()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getGiaTu()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getGiaDen()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgMuaTu()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgMuaDen()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgGui()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTeamplate()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(new String(subjectenc,"UTF-8")))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(new String(contentenc,"UTF-8")))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getRequestID()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getMobileoperator()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getCountRows()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getOffset()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getUnique()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getSendTest()))).append("|").toString());
            fout.print(DateProc.getYYYYMMDDHHMMSSString(DateProc.createTimestamp()));
            fout.println();
            fout.flush();
            fout.close();
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("FileLogger.logMO: ")).append(ex.getMessage()).toString());
        }
    }
    
    public void logRuningCamp(BCamp moData)
    {
        String mo_log_folder = LoggerConfig.LOG_RUN_CAMP_FOLDER;
        File dir = new File(mo_log_folder);
        if(!dir.exists())
            dir.mkdir();
        Base64.Encoder enc= Base64.getEncoder();
        mo_log_filename = (new StringBuilder(String.valueOf(DateProc.Timestamp2YYYYMMDD(DateProc.createTimestamp()))).append(".log").toString());
        try
        {
        	String file=new StringBuilder(String.valueOf(mo_log_folder)).append("/").append(mo_log_filename).toString();
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file,true), "UTF8"));
            byte[] subjectenc =enc.encode(moData.getSubject().getBytes("UTF-8"));
            byte[] contentenc =enc.encode(moData.getContent().getBytes("UTF-8"));
            fout.print((new StringBuilder(String.valueOf(moData.getAccount()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getNameCamp()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getNganhHang().equals("")?"null":moData.getNganhHang()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getLoaiSP().equals("")?"null":moData.getLoaiSP()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getGiaTu()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getGiaDen()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgMuaTu().equals("")?"null":moData.getTgMuaTu()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgMuaDen().equals("")?"null":moData.getTgMuaDen()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTgGui()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getTeamplate()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(new String(subjectenc,"UTF-8")))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(new String(contentenc,"UTF-8")))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getRequestID()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getMobileoperator().equals("")?"null":moData.getMobileoperator()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getCountRows()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getOffset()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getUnique()))).append("|").toString());
            fout.print((new StringBuilder(String.valueOf(moData.getSendTest()))).toString());
            fout.println();
            fout.flush();
            fout.close();
            
        }
        catch(IOException ex)
        {
            System.out.println((new StringBuilder("FileLogger.logMO: ")).append(ex.getMessage()).toString());
        }
    }
    private static String mo_err_log_filename = "";
    private static String mo_log_filename = "";
    private static int mt_log_counter = 0;
    private static String mt_err_log_filename = "";
    private static String mt_log_filename = "";

}

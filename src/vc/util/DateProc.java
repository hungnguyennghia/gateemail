// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DateProc.java

package vc.util;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateProc
{

    public DateProc()
    {
    }

    public static Timestamp createTimestamp()
    {
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp createDateTimestamp(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp String2Timestamp(String strInputDate)
    {
        String strDate = strInputDate;
        String strSub = null;
        int i = strDate.indexOf("/");
        if(i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        int nDay = (new Integer(strSub.trim())).intValue();
        strDate = strDate.substring(i + 1);
        i = strDate.indexOf("/");
        if(i < 0)
            return createTimestamp();
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub.trim())).intValue() - 1;
        strDate = strDate.substring(i + 1);
        if(strDate.length() < 4)
            if(strDate.substring(0, 1).equals("9"))
                strDate = (new StringBuilder("19")).append(strDate.trim()).toString();
            else
                strDate = (new StringBuilder("20")).append(strDate.trim()).toString();
        int nYear = (new Integer(strDate)).intValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(nYear, nMonth, nDay);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getDateTimeString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(Timestamp2DDMMYYYY(ts)))).append(" ").append(Timestamp2HHMMSS(ts, 1)).toString();
    }

    public static String getDateString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return Timestamp2DDMMYYYY(ts);
    }

    public static String getTimeString(Timestamp ts)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        return (new StringBuilder(String.valueOf(calendar.get(11)))).append(":").append(calendar.get(12)).append(":").append(calendar.get(13)).toString();
    }

    public static String Timestamp2YYYYMMDD(Timestamp ts)
    {
        if(ts == null)
            return "";
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        sDay = (new StringBuilder()).append(Integer.toString(calendar.get(5))).toString();
        if(calendar.get(5) < 10)
            sDay = (new StringBuilder("0")).append(sDay).toString();
        if(calendar.get(2) + 1 < 10)
            sMonth = (new StringBuilder("0")).append(calendar.get(2) + 1).toString();
        else
            sMonth = (new StringBuilder()).append(calendar.get(2) + 1).toString();
        sYear = (new StringBuilder()).append(calendar.get(1)).toString();
        return (new StringBuilder(String.valueOf(sYear))).append(sMonth).append(sDay).toString();
    }

    public static String Timestamp2YYYYMMDD(Timestamp ts, String seperator)
    {
        if(ts == null)
            return "";
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        sDay = (new StringBuilder()).append(Integer.toString(calendar.get(5))).toString();
        if(calendar.get(5) < 10)
            sDay = (new StringBuilder("0")).append(sDay).toString();
        if(calendar.get(2) + 1 < 10)
            sMonth = (new StringBuilder("0")).append(calendar.get(2) + 1).toString();
        else
            sMonth = (new StringBuilder()).append(calendar.get(2) + 1).toString();
        sYear = (new StringBuilder()).append(calendar.get(1)).toString();
        return (new StringBuilder(String.valueOf(sYear))).append(seperator).append(sMonth).append(seperator).append(sDay).toString();
    }

    public static String Timestamp2DDMMYYYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        if(calendar.get(5) < 10)
            strTemp = (new StringBuilder("0")).append(strTemp).toString();
        if(calendar.get(2) + 1 < 10)
            return (new StringBuilder(String.valueOf(strTemp))).append("/0").append(calendar.get(2) + 1).append("/").append(calendar.get(1)).toString();
        else
            return (new StringBuilder(String.valueOf(strTemp))).append("/").append(calendar.get(2) + 1).append("/").append(calendar.get(1)).toString();
    }

    public static String Timestamp2DDMMYY(Timestamp ts)
    {
        if(ts == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        String strTemp = Integer.toString(calendar.get(5));
        int endYear = calendar.get(1) % 100;
        if(calendar.get(5) < 10)
            strTemp = (new StringBuilder("0")).append(strTemp).toString();
        if(calendar.get(2) + 1 < 10)
            if(endYear < 10)
                return (new StringBuilder(String.valueOf(strTemp))).append("/0").append(calendar.get(2) + 1).append("/0").append(endYear).toString();
            else
                return (new StringBuilder(String.valueOf(strTemp))).append("/0").append(calendar.get(2) + 1).append("/").append(endYear).toString();
        if(endYear < 10)
            return (new StringBuilder(String.valueOf(strTemp))).append("/").append(calendar.get(2) + 1).append("/0").append(endYear).toString();
        else
            return (new StringBuilder(String.valueOf(strTemp))).append("/").append(calendar.get(2) + 1).append("/").append(endYear).toString();
    }

    public static String Timestamp2HHMM(Timestamp ts)
    {
        if(ts == null)
            return "";
        String sHour = "";
        String sMinunute = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        if(calendar.get(11) < 10)
            sHour = (new StringBuilder("0")).append(calendar.get(11)).toString();
        else
            sHour = (new StringBuilder()).append(calendar.get(11)).toString();
        if(calendar.get(12) < 10)
            sMinunute = (new StringBuilder("0")).append(calendar.get(12)).toString();
        else
            sMinunute = (new StringBuilder()).append(calendar.get(12)).toString();
        return (new StringBuilder(String.valueOf(sHour))).append(sMinunute).toString();
    }

    public static String Timestamp2HHMMSS(Timestamp ts)
    {
        if(ts == null)
            return "";
        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        if(calendar.get(11) < 10)
            sHour = (new StringBuilder("0")).append(Integer.toString(calendar.get(11))).toString();
        else
            sHour = (new StringBuilder()).append(Integer.toString(calendar.get(11))).toString();
        if(calendar.get(12) < 10)
            sMinunute = (new StringBuilder("0")).append(calendar.get(12)).toString();
        else
            sMinunute = (new StringBuilder()).append(calendar.get(12)).toString();
        if(calendar.get(13) < 10)
            sSecond = (new StringBuilder("0")).append(calendar.get(13)).toString();
        else
            sSecond = (new StringBuilder()).append(calendar.get(13)).toString();
        return (new StringBuilder(String.valueOf(sHour))).append(sMinunute).append(sSecond).toString();
    }

    public static String Timestamp2HHMMSS(Timestamp ts, int iStyle)
    {
        if(ts == null)
            return "";
        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        if(iStyle == 0)
            sHour = (new StringBuilder()).append(Integer.toString(calendar.get(11))).toString();
        else
            sHour = (new StringBuilder()).append(Integer.toString(calendar.get(10))).toString();
        if(sHour.length() < 2)
            sHour = (new StringBuilder("0")).append(sHour).toString();
        if(calendar.get(12) < 10)
            sMinunute = (new StringBuilder("0")).append(calendar.get(12)).toString();
        else
            sMinunute = (new StringBuilder()).append(calendar.get(12)).toString();
        if(calendar.get(13) < 10)
            sSecond = (new StringBuilder("0")).append(calendar.get(13)).toString();
        else
            sSecond = (new StringBuilder()).append(calendar.get(13)).toString();
        strTemp = (new StringBuilder(String.valueOf(sHour))).append(":").append(sMinunute).append(":").append(sSecond).toString();
        if(iStyle != 0)
            if(calendar.get(9) == 0)
                strTemp = (new StringBuilder(String.valueOf(strTemp))).append(" AM").toString();
            else
                strTemp = (new StringBuilder(String.valueOf(strTemp))).append(" PM").toString();
        return strTemp;
    }

    public static String Timestamp2HHMMSS(Timestamp ts, int iStyle, String separator)
    {
        if(ts == null)
            return "";
        String sHour = "";
        String sMinunute = "";
        String sSecond = "";
        String strTemp = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(ts.getTime()));
        if(iStyle == 0)
            sHour = (new StringBuilder()).append(Integer.toString(calendar.get(11))).toString();
        else
            sHour = (new StringBuilder()).append(Integer.toString(calendar.get(10))).toString();
        if(sHour.length() < 2)
            sHour = (new StringBuilder("0")).append(sHour).toString();
        if(calendar.get(12) < 10)
            sMinunute = (new StringBuilder("0")).append(calendar.get(12)).toString();
        else
            sMinunute = (new StringBuilder()).append(calendar.get(12)).toString();
        if(calendar.get(13) < 10)
            sSecond = (new StringBuilder("0")).append(calendar.get(13)).toString();
        else
            sSecond = (new StringBuilder()).append(calendar.get(13)).toString();
        strTemp = (new StringBuilder(String.valueOf(sHour))).append(separator).append(sMinunute).append(separator).append(sSecond).toString();
        if(iStyle != 0)
            if(calendar.get(9) == 0)
                strTemp = (new StringBuilder(String.valueOf(strTemp))).append(" AM").toString();
            else
                strTemp = (new StringBuilder(String.valueOf(strTemp))).append(" PM").toString();
        return strTemp;
    }

    public static String getYYYYMMDDHHMMString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(Timestamp2YYYYMMDD(ts)))).append(Timestamp2HHMM(ts)).toString();
    }

    public static String getYYYYMMDDHHMMSSString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(Timestamp2YYYYMMDD(ts)))).append(Timestamp2HHMMSS(ts)).toString();
    }

    public static String getDateTime24hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(Timestamp2DDMMYYYY(ts)))).append(" ").append(Timestamp2HHMMSS(ts, 0)).toString();
    }

    public static String getDateTime12hString(Timestamp ts)
    {
        if(ts == null)
            return "";
        else
            return (new StringBuilder(String.valueOf(Timestamp2DDMMYYYY(ts)))).append(" ").append(Timestamp2HHMMSS(ts, 1)).toString();
    }

    public static String TimestampPlusDay2DDMMYYYY(Timestamp ts, int iDayPlus)
    {
        if(ts == null)
        {
            return "";
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(ts.getTime()));
            int iDay = calendar.get(5);
            calendar.set(5, iDay + iDayPlus);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return Timestamp2DDMMYYYY(tsNew);
        }
    }

    public static Timestamp getPreviousDate(Timestamp ts)
    {
        if(ts == null)
        {
            return null;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(ts.getTime()));
            int iDay = calendar.get(5);
            calendar.set(5, iDay - 1);
            Timestamp tsNew = new Timestamp(calendar.getTime().getTime());
            return tsNew;
        }
    }

    public static String getLastestDateOfMonth(String strMonthYear)
    {
        String strDate = strMonthYear;
        String strSub = null;
        int i = strDate.indexOf("/");
        if(i < 0)
            return "";
        strSub = strDate.substring(0, i);
        int nMonth = (new Integer(strSub)).intValue();
        strDate = strDate.substring(i + 1);
        int nYear = (new Integer(strDate)).intValue();
        boolean leapyear = false;
        if(nYear % 100 == 0)
        {
            if(nYear % 400 == 0)
                leapyear = true;
        } else
        if(nYear % 4 == 0)
            leapyear = true;
        if(nMonth == 2)
            if(leapyear)
                return (new StringBuilder("29/")).append(strDate).toString();
            else
                return (new StringBuilder("28/")).append(strDate).toString();
        if(nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7 || nMonth == 8 || nMonth == 10 || nMonth == 12)
            return (new StringBuilder("31/")).append(strDate).toString();
        if(nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11)
            return (new StringBuilder("30/")).append(strDate).toString();
        else
            return "";
    }

    public static void main(String args[])
    {
        System.out.println(getYYYYMMDDHHMMString(createTimestamp()));
        System.out.println(Timestamp2YYYYMMDD(createTimestamp(), "-"));
        System.out.println(Timestamp2YYYYMMDD(getPreviousDate(createTimestamp())));
    }
}

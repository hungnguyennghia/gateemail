// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDGenerator.java

package vc.util;

import java.io.IOException;
import java.math.BigDecimal;
import vc.WSConfig;

public class IDGenerator
{

    public IDGenerator()
    {
    }

    public static BigDecimal genMT_ID()
    {
        if(mt_id == null)
            try
            {
                byte b[] = FileTool.readFile(FILE_MT_ID);
                System.out.println((new StringBuilder("byte[] length: [")).append(b.length).append("]").toString());
                if(b.length == 0)
                    mt_id = new BigDecimal(0);
                else
                    mt_id = new BigDecimal(new String(b));
                System.out.println((new StringBuilder("mt_id[]: [")).append(mt_id.toString().getBytes()).append("]").toString());
            }
            catch(IOException ex)
            {
                System.out.println("Catch here");
                mt_id = new BigDecimal(0);
            }
        mt_id = mt_id.add(new BigDecimal(1));
        try
        {
            FileTool.saveToFile(mt_id.toString().getBytes(), FILE_MT_ID, false);
        }
        catch(Exception exception) { }
        return mt_id;
    }

    private static final String FILE_MT_ID;
    private static BigDecimal mt_id = null;

    static 
    {
        FILE_MT_ID = (new StringBuilder(String.valueOf(WSConfig.dirFileLog))).append(WSConfig.fileNameMT).toString();
    }
}

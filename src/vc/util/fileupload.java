// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   beanBR.java

package vc.util;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class fileupload
{

    public fileupload()
    {

    }


    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public byte[] getImageBytes() {
		return imageBytes;
	}


	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}


	private String fileName;
    private byte[] imageBytes;
   
    
}

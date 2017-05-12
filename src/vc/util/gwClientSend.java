// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   beanBR.java

package vc.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import vc.WSConfig;
import vc.process.requestCamp;


public class gwClientSend 
{
	private static final int MYTHREADS = 30;
	 
	 public static String removeAccent(String s) {
		  
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
		 }
	 
	public static void main(String args[]) throws Exception {
		
    	int ii = 0;
  	  while(ii < 1901){
  		  ii=ii+100;
  		  System.err.println(ii);
  	  }

	}
		
//        try
//        {
//        	WSConfig.loadPropertiesConfig();
//        }
//        catch(Exception e)
//        {
//            System.out.println("khong doc duoc file ./conf/loadPropertiesConfig.properties");
//        }
//        
//		
//		
//		System.out.print(WSConfig.tempName[0]);
//		System.out.print(WSConfig.tempName[1]);
//		System.out.print(WSConfig.tempName[2]);
//		System.out.print(WSConfig.tempName[3]);
	//	 System.out.print(removeAccent("Tai nghe không dây  thoại và nhạc Roman X2S  Đen").replace(" ", ""));
		
		
	    
//		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
//		String[] hostList = { "http://crunchify.com", "http://yahoo.com",
//				"http://www.ebay.com", "http://google.com",
//				"http://www.example.co", "https://paypal.com",
//				"http://bing.com/", "http://techcrunch.com/",
//				"http://mashable.com/", "http://thenextweb.com/",
//				"http://wordpress.com/", "http://wordpress.org/",
//				"http://example.com/", "http://sjsu.edu/",
//				"http://ebay.co.uk/", "http://google.co.uk/",
//				"http://www.wikipedia.org/",
//				"http://en.wikipedia.org/wiki/Main_Page" };
// 
//		for (int i = 0; i < hostList.length; i++) {
// 
//			String url = hostList[i];
//			Runnable worker = new gwClientSendMulti(url);
//			executor.execute(worker);
//		}
//		executor.shutdown();
//		// Wait until all threads are finish
//		while (!executor.isTerminated()) {
// 
//		}
//		System.out.println("\nFinished all threads");
//	}
 

}

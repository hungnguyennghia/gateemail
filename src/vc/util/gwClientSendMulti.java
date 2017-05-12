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


public class gwClientSendMulti implements Runnable
{
		private final String url;
 
		gwClientSendMulti(String url) {
			this.url = url;
		}
 
		@Override
		public void run() {
 
			String result = "";
			int code = 200;
			try {
				URL siteURL = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) siteURL
						.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
 
				code = connection.getResponseCode();
				if (code == 200) {
					result = "Green\t";
				}
			} catch (Exception e) {
				result = "->Red<-\t";
			}
			System.out.println(url + "\t\tStatus:" + result);
		}
	
}


package vc.util;

import java.io.*;
import java.util.*;
import vc.util.FileTool;
import vc.common.LoggerConfig;

public class CampQueue extends Queue
{

    public boolean isStoredInFile()
    {
        return storedInFile;
    }

    public void setStoredInFile(boolean value)
    {
        storedInFile = value;
    }

    public CampQueue(String queueName)
    {
        fileName = null;
        counter = 0;
        storedInFile = false;
        isLoading = false;
        setName(queueName);
        File dir = new File(LoggerConfig.QUEUE_FOLDER);
        if(!dir.exists())
            dir.mkdir();
        dir = new File((new StringBuilder(String.valueOf(LoggerConfig.QUEUE_FOLDER))).append("/").append(queueName).toString());
        if(!dir.exists())
            dir.mkdir();
        else
            loadData();
    }

    public Object dequeue() {
        synchronized (monitor) {
            if(isStoredInFile() && queue.isEmpty())
                loadData();
            while (queue.isEmpty()) { //Threads are blocked
                try { //if the queue is empty.
                    monitor.wait(); //wait until other thread call notify().
                } catch (InterruptedException ex) {}
            }
            Object item = queue.removeFirst();
            return item;
        }
    }


    public void enqueue(Object item)
    {
        synchronized(monitor)
        {
            queue.addLast(item);
            monitor.notifyAll();
//            System.out.println(queue.size());
        }
        if(queue.size() >= MAX_QUEUE_SIZE)
        {
            storeData();
            setStoredInFile(true);
        }
    }

    public int storeData()
    {
    	 try
         {
	        Collection c;
	        c = dequeueAll();
	        if(c != null && c.size() > 0)
	        {
	            PrintWriter fout = openNewFile();
	            BCamp mo = null;
	            for(Iterator it = c.iterator(); it.hasNext();)
	            {
	                mo = (BCamp)it.next();
	                if(mo != null)
	                {
	                    mo.toFile(fout);
	                    if(counter >= MAX_FILE_SIZE)
	                    {
	                        fout.close();
	                        fout = openNewFile();
	                        counter = 0;
	                    }
	                }
	                counter++;
	            }
	
	            fout.close();
	        }
	        return c.size();
	    } catch(Exception ex){ }
        return 0;
    }

    private PrintWriter openNewFile()
        throws IOException
    {
        fileName = (new StringBuilder(String.valueOf(LoggerConfig.QUEUE_FOLDER))).append("/").append(getName()).append("/").append(System.currentTimeMillis()).append(".queue").toString();
//        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName,true), "UTF8"));

        return fout;
    }

    private void loadData()
    {
        Vector vFiles = null;
        isLoading = true;
        try
        {
            vFiles = FileTool.getAllFiles((new StringBuilder(String.valueOf(LoggerConfig.QUEUE_FOLDER))).append("/").append(getName()).toString(), ".queue");
        }
        catch(Exception ex)
        {
            isLoading = false;
            return;
        }
        if(vFiles == null || vFiles.size() == 0)
        {
            setStoredInFile(false);
            return;
        }
        try
        {
            File file = (File)vFiles.get(0);
        	BufferedReader fIn = new BufferedReader(
     			   new InputStreamReader(new FileInputStream(file), "UTF8"));
            String line = null;
            BCamp mo = null;
            Vector vMOs = new Vector();
            int i = 0;
            while((line = fIn.readLine()) != null) 
            {
                mo = BCamp.parseString(line);
                if(mo != null)
                {
                    vMOs.add(mo);
                    i++;
                }
            }
            fIn.close();
            file.delete();
            enqueueAll(vMOs);
            if(vFiles.size() == 1)
                setStoredInFile(false);
            else
            if(!isStoredInFile())
                setStoredInFile(true);
        }
        catch(Exception exception) { }
    }

    public static void main(String args[])
    {
        try
        {
            LoggerConfig.loadProperties();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        Queue q = new CampQueue("requestUrlPushQueue");
        System.out.println((new StringBuilder("queue.size()=")).append(q.size()).toString());
        q.storeData();
    }

    private static final int MAX_QUEUE_SIZE = 100;
    private static final int MAX_FILE_SIZE = 100;
    private String fileName;
    private int counter;
    private boolean storedInFile;
    private boolean isLoading;
}

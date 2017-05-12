package vc.util;


import java.io.*;
import java.util.*;

public class FileTool {
    public FileTool() {
    }
    // returns a vector of <code>File</code> objects
    public static Vector getAllFiles(String folder, String fileExt) throws FolderNotFoundException {
        Vector v = new Vector();

        File[] list = new File(folder).listFiles();
        if (list == null) {
            throw new FolderNotFoundException("Folder " + folder + " could not be found.");
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i].toString().endsWith(fileExt)) {
                v.addElement(list[i]);
            }
            /* also list file in subfolders */
            /*
            if ((list[i]).isDirectory()) {
                 getAllFiles(list[i]);
            }
            */
        }
        return v;
    }

    public static void copy(String source_name, String dest_name) throws IOException {
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);
        copy(source_file, dest_file);
    }

    public static void copy(File source_file, File dest_file) throws IOException {
        FileInputStream source = null;
        FileOutputStream destination = null;

        byte[] buffer;
        int bytes_read;

        // First make sure the specified source file
        // exists, is a file, and is readable.
        if (!source_file.exists() || !source_file.isFile())
            throw new FileCopyException("FileCopy: no such source file: " +
                                        source_file.getPath());
        if (!source_file.canRead())
            throw new FileCopyException("FileCopy: source file is unreadable: " +
                                        source_file.getPath());
        // If we've gotten this far, then everything is okay;
        // we can copy the file.
        source = new FileInputStream(source_file);
        destination = new FileOutputStream(dest_file);
        buffer = new byte[1024];
        while ((bytes_read=source.read(buffer)) != -1) {
            destination.write(buffer, 0, bytes_read);
        }
        // No matter what happens, always close any streams we've opened.
        try {
            if (source != null)
                source.close();
            if (destination != null)
                destination.close();
        } catch (IOException e) {}
    }
    public static void move(String source_name, String dest_name) throws IOException {
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);

        copy(source_file, dest_file);
        source_file.delete();
    }

    public static void move(File source_file, File dest_file) throws IOException {
        copy(source_file, dest_file);
        source_file.delete();
    }

    public static byte[] readFile(String filename) throws IOException {
        byte[] buffer = null;
        FileInputStream fin = new FileInputStream(filename);
        buffer = new byte[fin.available()];
        fin.read(buffer);
        fin.close();
        return buffer;
    }
    
    public static String readLineFromFile(String filename) throws IOException {
    	BufferedReader fIn=null;
		String line="";
		try{
			//fIn = new BufferedReader(new FileReader("./conf/users.txt"));
			fIn = new BufferedReader(new FileReader(filename));
			line = fIn.readLine();
			fIn.close();
			return line;
		}catch(FileNotFoundException ex){
			System.out.println("Khong tim thay file black_list.dat");
			System.exit(1);
			return line;
		}
    }

    public static void saveToFile(byte[] output, String filename) {
        try {
            File f = new File(filename);
            FileOutputStream out = new FileOutputStream(f);

            out.write(output);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveToFile(byte[] output, String filename, boolean append) {
        try {
            FileOutputStream out = new FileOutputStream(filename, append);
            out.write(output);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveToFile(String output, String filename) {
        try {
            java.io.DataOutputStream fout = new java.io.DataOutputStream(new
                    FileOutputStream(filename, true)); // append = true
            fout.writeBytes(output);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String args[]) {
        FileTool fTool = new FileTool();

        File dir = new File("./OUT");
        System.out.println(dir);

        dir.mkdir();

    }
}

class FileCopyException extends IOException {
    public FileCopyException(String msg) {
        super(msg);
    }
}

class FolderNotFoundException extends IOException {
    public FolderNotFoundException(String msg) {
        super(msg);
    }
}



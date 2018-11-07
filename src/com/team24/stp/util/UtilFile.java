package com.team24.stp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class UtilFile {
	// getServletContext().getRealPath("/data") + "\\test.txt";
	public static void main(String[] args) {
	}

	static void testFile() {
		UtilFile.writeLine("YourFile.txt", "Hello, ", false);
		UtilFile.writeLine("YourFile.txt", "How are you?", true);
		System.out.println(UtilFile.readFile("YourFile.txt"));
	}

	public static void writeLine(String fname, String txt, boolean append) {
		Writer writer = null;
		try {
			System.out.println(fname + "" + txt);
			// writer = new FileWriter(fname, append);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname), "UTF-8"));
			writer.write(txt);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<String> readLine(String fname) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(fname);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				ret.add(line + "");
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static String readFile(String fname) {
		StringBuffer ret = new StringBuffer();
		try {
			// FileReader reader = new FileReader(fname);
			/// BufferedReader bufferedReader = new BufferedReader(reader);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(fname), "UTF8"));
			;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				ret.append(line + "");

			}
			bufferedReader.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return ret.toString();
	}

	public static String getfiles(String p) {
		StringBuffer sb = new StringBuffer("");
		File folder = new File(p);
		try {
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					if (i > 0)
						sb.append("|||");
					String content = readFile(listOfFiles[i].getPath());
					sb.append(listOfFiles[i].getName() + "||" + content + "|| ");
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		} catch (Exception e) {
			System.out.println("Error reading directory! ");
		}
		return sb.toString();
	}

	public static void makedir(String p) {
		File theDir = new File(p);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + p);
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

	public static void rename(String p) {
		File oldName = new File("C:/program.txt");
		File newName = new File("C:/java.txt");
		if (oldName.renameTo(newName)) {
			System.out.println("renamed");
		} else {
			System.out.println("Error");
		}
	}

	public static void del(String p) {
		File oldName = new File(p);
		if (oldName.delete()) {
			System.out.println("Deleted");
		} else {
			System.out.println("Error deleting");
		}
	}
}

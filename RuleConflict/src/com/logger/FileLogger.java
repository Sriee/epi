package com.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Logger implementation for printing the values to a log file
 * Singleton implementation so that there is only one logging object
 * 
 * @author sriee
 *
 */
public class FileLogger implements Logger {

	private String fileName = "rule.log"; 		// Log file name
	private File logFile = null;				// Log file handle
	public static FileLogger fileLoggerObj = null;

	/**
	 * Initialize the file handle and log file object
	 */
	private FileLogger() {
		super();
		Path currentDir = Paths.get(".");
		this.fileName = currentDir.toString() + "/" + this.fileName;

		this.logFile = new File(this.fileName);
		try {
			if( !this.logFile.exists() ){
				if( ! logFile.createNewFile() )
					throw new NullPointerException(fileName + " file not created.");
			} else {
				BufferedWriter bw = null;

				bw = new BufferedWriter( new FileWriter(this.logFile.getCanonicalPath(), true));
				bw.write("\n[=============================================New Run=============================================]\n");
				bw.close();	
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Construct File Logger object
     * 
     * @return File logger instance
     */
	public static FileLogger instance() {
		if(fileLoggerObj == null){
			fileLoggerObj = new FileLogger();
		}
		return fileLoggerObj;
	}

	/**
	 * Write a single entry to log file.
	 * 
	 * @param log statement
	 */
	@Override
	public void writeLog(String entry){
		if( logFile == null )
			throw new NullPointerException("Log file not initialized.");

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter( new FileWriter(this.logFile.getCanonicalPath(), true));
			bw.write(this.getTimeStamp() + entry + "\n");
			bw.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write a collection of string entries to log file.
	 * 
	 * @param entry Collection of log statements
	 */
	@Override
	public void writeLog(Collection<String> entry){
		if( logFile == null )
			throw new NullPointerException("Log file not initialized.");
		Iterator<String> iter = entry.iterator();

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter( new FileWriter(this.logFile.getCanonicalPath(), true));
			while( iter.hasNext() )
				bw.write(this.getTimeStamp() + iter.next() + "\n");

			bw.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Timestamp for logging. Fomat - [12-07-2017 16:53]
	 * 
	 * @return time stamp
	 */
	private String getTimeStamp(){
		String timeStamp = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("[M-dd-yyyy H:mm] ");
		Date date = new Date();
		timeStamp = dateFormat.format(date.getTime()) + ": ";
		return timeStamp;
	}
}

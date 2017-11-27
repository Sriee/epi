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

public class FileLogger implements Logger {

	private String fileName = "rule.log"; 
	private File logFile = null;
    public static FileLogger fileLoggerObj = null;

	private FileLogger() throws IOException {
		super();
		Path currentDir = Paths.get(".");
		this.fileName = currentDir.toString() + "/" + this.fileName;
		
		this.logFile = new File(this.fileName);
		if( !this.logFile.exists() ){
			if( ! logFile.createNewFile() )
				throw new NullPointerException(fileName + " file not created.");
		} else {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter( new FileWriter(this.logFile.getCanonicalPath(), true));
				bw.write("\n[=============================================New Run=============================================]\n");
				bw.close();
			} catch(FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	public static FileLogger instance() throws IOException{
        if(fileLoggerObj == null){
            fileLoggerObj = new FileLogger();
        }
        return fileLoggerObj;
    }

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
	
	private String getTimeStamp(){
		String timeStamp = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("[M-dd-yyyy H:mm] ");
		Date date = new Date();
		timeStamp = dateFormat.format(date.getTime()) + ": ";
		return timeStamp;
	}
}

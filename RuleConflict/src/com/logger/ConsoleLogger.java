package com.logger;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Logger implementation for printing the values to the console
 * Singleton implementation so that there is only one logging object
 * 
 * @author sriee
 *
 */
public class ConsoleLogger implements Logger {

    public static ConsoleLogger consoleLoggerObj = null;
    private ConsoleLogger(){}
    
    /**
     * Construct Console Logger object
     * 
     * @return Console logger instance
     */
    public static ConsoleLogger instance(){
        if(consoleLoggerObj == null){
            consoleLoggerObj = new ConsoleLogger();
        }

        return consoleLoggerObj;
    }

    /**
     * Write a collection of string entries to Standard Output(stdout) Stream.
     * 
     * @param entry Collection of log statements 
     */
	@Override
	public void writeLog(Collection<String> entry) {
		Iterator<String> iter = entry.iterator();
		while(iter.hasNext()){
			System.out.println(this.getTimeStamp() + iter.next());
		}
	}

	/**
	 * Write a single entry to Standard Output(stdout) Stream.
	 * 
	 * @param log statement
	 */
	@Override
	public void writeLog(String entry) { System.out.println(this.getTimeStamp() + entry); }
	
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

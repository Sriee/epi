package com.logger;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class ConsoleLogger implements Logger {

	@Override
	public void writeLog(Collection<String> entry) {
		Iterator<String> iter = entry.iterator();
		while(iter.hasNext()){
			System.out.println(this.getTimeStamp() + iter.next());
		}
	}

	@Override
	public void writeLog(String entry) { System.out.println(this.getTimeStamp() + entry); }
	
	private String getTimeStamp(){
		String timeStamp = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("[M-dd-yyyy H:mm] ");
		Date date = new Date();
		timeStamp = dateFormat.format(date.getTime()) + ": ";
		return timeStamp;
	}
}

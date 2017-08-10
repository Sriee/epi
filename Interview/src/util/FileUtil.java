package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileUtil{

    private String fileName;
    private long headLength;
    private long tailLength;
    private long sleep;

    public FileUtil(){
        this(null, 10, 10, 1);
    }

    public FileUtil(String fileName){
        this(fileName, 10, 10, 1);
    }

    public FileUtil(String fileName, long headLength, long tailLength, long sleep){
        this.fileName = fileName;
        this.headLength = headLength;
        this.tailLength = tailLength;
        this.sleep = sleep;
    }
    
    /**
	 * @return the fileName
	 */
	private String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the headLength
	 */
	private long getHeadLength() {
		return headLength;
	}

	/**
	 * @param headLength the headLength to set
	 */
	private void setHeadLength(long headLength) {
		this.headLength = headLength;
	}

	/**
	 * @return the tailLength
	 */
	private long getTailLength() {
		return tailLength;
	}

	/**
	 * @param tailLength the tailLength to set
	 */
	private void setTailLength(long tailLength) {
		this.tailLength = tailLength;
	}

	/**
	 * @return the sleep
	 */
	private long getSleep() {
		return sleep;
	}

	/**
     * Displays the first [count] lines of the specified file.  If count is omitted it defaults to 10.
     */
    public void head(){ headFunctionality(); }

    /**
     * Displays the first [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param fileName Name of the file
     */
    public void head(String fileName){
        this.setFileName(fileName);
        headFunctionality();
    }

    /**
     * Displays the first [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param headLength number of lines to display
     */
    public void head(long headLength){
        this.setHeadLength(headLength);
        headFunctionality();
    }

    /**
     * Displays the first [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param fileName Name of the file
     * @param headLength number of lines to display
     */
    public void head(String fileName, long headLength){
        this.setFileName(fileName);
        this.setHeadLength(headLength);
        headFunctionality();
    }

    /**
     * 'head' command feature implementation
     */
    private void headFunctionality(){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.getFileName()));
            String currentLine;
            long linesRead = 1;
            while ((currentLine = bufferedReader.readLine()) != null && linesRead <= this.getHeadLength()) {
                System.out.println(currentLine);
                linesRead += 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Displays the last [count] lines of the specified file.  If count is omitted it defaults to 10.
     */
    public void tail(){
        tailFunctionality();
    }

    /**
     * Displays the last [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param fileName Name of the file
     */
    public void tail(String fileName){
        this.setFileName(fileName);
        tailFunctionality();
    }

    /**
     * Displays the last [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param tailLength number of lines to display
     */
    public void tail(long tailLength){
        this.setTailLength(tailLength);
        tailFunctionality();
    }

    /**
     * Displays the last [count] lines of the specified file.  If count is omitted it defaults to 10.
     *
     * @param fileName Name of the file
     * @param tailLength number of lines to display
     */
    public void tail(String fileName, long tailLength){
        this.setFileName(fileName);
        this.setTailLength(tailLength);
        tailFunctionality();
    }

    /**
     * 'tail' command feature implementation
     */
    private void tailFunctionality(){
        RandomAccessFile raf = null;
        List<String> lines = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        try {
            raf = new RandomAccessFile(this.getFileName(), "r");
            long length = raf.length(); // Length of the file in bytes

            for(long seek = length-1; seek >= 0; --seek){
                raf.seek(seek);
                char ch = (char)raf.read();

                if(ch == '\n'){ // Encountered new line
                    lines.add(0, builder.toString() + "\n");
                    builder = new StringBuilder();

                    if(lines.size() == this.getTailLength()) break;
                } else {
                    builder.insert(0, ch);
                }
            }
            for(String line : lines)
                System.out.print(line);

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays the last [count] lines of the specified file. If count is omitted it defaults to 10. After printing
     * monitors the file for further additions
     *
     * Warning: This is a daemon thread. User should send SIGTERM to quit the program.
     */
    public void tailFeed(){
        new Thread(new TailFeed(this.getFileName(), this.getSleep())).start();
    }

    /**
     * Displays the last [count] lines of the specified file. If count is omitted it defaults to 10. After printing
     * monitors the file for further additions
     *
     * Warning: This is a daemon thread. User should send SIGTERM to quit the program.
     *
     * @param fileName Name of the file
     */
    public void tailFeed(String fileName){
        new Thread(new TailFeed(this.getFileName(), this.getSleep())).start();
    }

    /**
     * Displays the last [count] lines of the specified file. If count is omitted it defaults to 10. After printing
     * monitors the file for further additions
     *
     * Warning: This is a daemon thread. User should send SIGTERM to quit the program.
     *
     * @param sleep update interval for feed
     */
    public void tailFeed(long sleep){
        new Thread(new TailFeed(this.getFileName(), this.getSleep())).start();
    }

    /**
     * Displays the last [count] lines of the specified file. If count is omitted it defaults to 10. After printing
     * monitors the file for further additions
     *
     * Warning: This is a daemon thread. User should send SIGTERM to quit the program.
     *
     * @param fileName Name of the file
     * @param sleep update interval for feed
     */
    public void tailFeed(String fileName, long sleep){
        new Thread(new TailFeed(this.getFileName(), this.getSleep())).start();
    }
    
    @Override
    public String toString() {
        return "FileUtil [File Name=\'" + this.getFileName() + "\', Head Count=" + this.getHeadLength() +
                ", Tail Count=" + this.getTailLength() + ", Sleep=" + this.getSleep() + "]";
    }

    /**
     * Calculates the number of lines in a file. Empty lines will also be considered. If there is an empty line at the
     * End of File, it won't be considered
     *
     * @return Number of lines in a file, -1 if any error occurred
     */
    public long length(){
        BufferedReader bufferedReader = null;
        long numLines = 0;
        try{
            bufferedReader = new BufferedReader(new FileReader(this.getFileName()));
            while(bufferedReader.readLine() != null) numLines++;
            bufferedReader.close();
            return numLines;
        } catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

	/**
     * Driver program for testing tail feed.
     * @param args
     */
    public static void main(String[] args) {
    	final String fileName = "/home/sriee/data/test.txt";
        FileUtil fu = new FileUtil(fileName);
        fu.tailFeed();
        
    	String dummy[] = {
    			"------------------------------ Start ----------------------------------\n",
    			"07-05-2017 12:25 PM:DEBUG: Paths: ../template ../config ../reports\n",
    			"07-05-2017 12:25 PM:INFO: Opening file at ../config/settings.json \n",
    			"------------------------------- End ------------------------------------\n"
    			};
    	BufferedWriter bw = null;
		try{
			Thread.sleep(1000);
			for(int i = 0; i < 10; i++){
				Thread.sleep(1500);
				bw = new BufferedWriter(
						new FileWriter(fileName, true));
				for(String line : dummy) {
					bw.write(line);	
				}
				bw.close();
			}
		} catch (IOException | InterruptedException e){
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
    }

}
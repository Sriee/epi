package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
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

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private long getHeadLength() {
        return headLength;
    }

    private void setHeadLength(long headLength) {
        this.headLength = headLength;
    }

    private long getTailLength() {
        return tailLength;
    }

    private void setTailLength(long tailLength) {
        this.tailLength = tailLength;
    }

    public long getSleep() {
        return sleep;
    }

    public void setSleep(long sleep) {
        this.sleep = sleep;
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
        FileReader fileReader = null;

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
                if (fileReader != null) fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void tail(){
        tailFunctionality();
    }

    public void tail(String fileName){
        this.setFileName(fileName);
        tailFunctionality();
    }

    public void tail(long tailLength){
        this.setTailLength(tailLength);
        tailFunctionality();
    }

    public void tail(String fileName, long tailLength){
        this.setFileName(fileName);
        this.setTailLength(tailLength);
        tailFunctionality();
    }

    private void tailFunctionality(){
        RandomAccessFile raf = null;
        List<String> lines = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();

        try {
            raf = new RandomAccessFile(this.getFileName(), "r");
            long length = raf.length(); // Length of the file in bytes

            for(long seek = length-1; seek >= 0; --seek){
                raf.seek(seek);
                char ch = (char)raf.read();
                builder.insert(0, ch);
                if(ch == '\n'){ // Encountered new line
                    lines.add(0, builder.toString());
                    builder = new StringBuilder();

                    if(lines.size() == this.getTailLength()) break;
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

    public static void main(String[] args) {
    	FileUtil fu = new FileUtil("/Users/sriee/epi/Interview/src/util/data/harley.txt");
        System.out.println("Starting...");
    	fu.head();
        System.out.println("(END)...");
    }
}
package util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Properties;
import java.util.ArrayList;

public class FileUtil {

    private String file_name = null;
    private long length = -1;

    public FileUtil(){
        this.length = 5;
    }

    public FileUtil(String fileName, long length){
        this.length = length;
        this.file_name = fileName;
    }

    public void tail(String fileName){
    	RandomAccessFile raf = null;
    	List<String> lines = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();

        try {
            raf = new RandomAccessFile(fileName, "r");
            length = raf.length(); // Length of the file in bytes
            length--;
            
            for(long seek = length-1; seek >= 0; --seek){
                raf.seek(seek);
                char ch = (char)raf.read();
                builder.insert(0, ch);
                if(ch == '\n'){ // Encountered new line
                    lines.add(0, builder.toString());
                    builder = new StringBuilder();

                    if(lines.size() == 5) break;
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

    public void tail(String fileName, long length){
    	RandomAccessFile raf = null;
    	List<String> lines = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();

        try {
            raf = new RandomAccessFile(this.file_name, "r");
            length = raf.length(); // Length of the file in bytes

            for(long seek = length-1; seek >= 0; --seek){
                raf.seek(seek);
                char ch = (char)raf.read();
                builder.insert(0, ch);
                if(ch == '\n'){ // Encountered new line
                    lines.add(0, builder.toString());
                    builder = new StringBuilder();

                    if(lines.size() == this.length) break;
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
    	FileUtil fu = new FileUtil();
    	System.out.println("Starting...");
    	fu.tail("/home/sriee/Git/epi/Interview/src/util/data/harley.txt");
    }
}

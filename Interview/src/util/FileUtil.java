package util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.ArrayList;

public class FileUtil {

    private static final String file_name = "data/dummt.txt";
    private long length = -1;

    public FileUtil(){
        this.length = 5;
    }

    public FileUtil(){
        this.length = 5;
    }

    public void tail(String fileName){

    }

    public void tail(String fileName, int length){

    }

    public static void main(String[] args) {

        RandomAccessFile raf = null;
        long length = -1;
        List<String> lines = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();

        try {
            raf = new RandomAccessFile(file_name, "r");
            length = raf.length(); // Length of the file in bytes

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
}

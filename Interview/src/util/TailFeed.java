package util;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TailFeed implements Runnable{

    private String fileName;
    private long updateInterval;

    public TailFeed(String fileName, long sleep) {
        this.fileName = fileName;
        this.updateInterval = (sleep < 1000) ? 1000 : sleep;
    }

    private String getFileName() {
        return fileName;
    }

    private long getUpdateInterval() {
        return updateInterval;
    }

    @Override
    public void run(){
        RandomAccessFile raf = null;
        long filePointer = -1;
        try {
            raf = new RandomAccessFile(this.getFileName(), "r");
            while (true) {
                Thread.sleep(this.getUpdateInterval());
                long len = raf.length() - 1;

                if (len < filePointer) {
                    System.out.println("File reset. Stopping tail feed.");
                    break;
                } else if (len > filePointer) {
                    raf.seek(filePointer);
                    String line = null;
                    while ((line = raf.readLine()) != null) {
                        System.out.println(line);
                    }

                    filePointer = raf.getFilePointer();
                }
            }
        } catch (Exception e) {
            System.out.println("Tail feed has stopped.");
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

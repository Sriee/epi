package util;

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
        long filePointer = 0;
        try {
            while (true) {
                Thread.sleep(this.getUpdateInterval());
                raf = new RandomAccessFile(this.getFileName(), "r");
                long length = raf.length() - 1;
                if (length > filePointer) {
                    raf.seek(filePointer);
                    String line = null;
                    while ((line = raf.readLine()) != null) {
                        System.out.println(line);
                    }

                    filePointer = raf.getFilePointer();
                }
                raf.close();
            }
        } catch (Exception e) {
            System.out.println("Tail feed has stopped.");
		}
    }
}

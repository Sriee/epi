package com.logger;

import java.util.Collection;

/**
 * Interface for logger modules.
 * 
 * @author sriee
 *
 */
public interface Logger {

    public void writeLog(Collection<String> entry);
    public void writeLog(String entry);
}

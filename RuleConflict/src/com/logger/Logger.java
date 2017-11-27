package com.logger;

import java.util.Collection;

public interface Logger {

    public void writeLog(Collection<String> entry);
    public void writeLog(String entry);
}

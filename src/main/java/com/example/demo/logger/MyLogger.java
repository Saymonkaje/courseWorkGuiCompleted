package com.example.demo.logger;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    private static Logger logger = null;


    private MyLogger() throws IOException {
        logger = Logger.getLogger(MyLogger.class.getName());
        logger.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler("log.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

    }

    public static Logger getLogger()
    {
        if(logger == null) {
            try {
                new MyLogger();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return logger;
    }


}

package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod {
    public static ExampleMod INSTANCE = new ExampleMod();

    private final Logger logger = LogManager.getLogger();

    public void main() {
        logger.info("ExampleMod was loaded! :)");
    }

    public Logger getLogger() {
        return logger;
    }
}

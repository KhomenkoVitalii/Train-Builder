package main.app;

import main.Settings;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransportApp {
    public static Logger logger;

    public TransportApp() {
        initLogger();
    }

    public static void initLogger() {
        logger = Logger.getLogger("TransportAppLogger");
        try {
            FileHandler fileHandler = new FileHandler(Settings.logsFilePath);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}

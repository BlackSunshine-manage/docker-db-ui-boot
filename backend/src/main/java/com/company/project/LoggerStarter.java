package com.company.project;

import org.apache.logging.log4j.Logger;

public class LoggerStarter {
    public LoggerStarter(Logger logger) {
        logger.info("Starting logging level INFO");
        logger.debug("Starting logging level DEBUG");
        logger.error("Starting logging level ERROR");
        logger.warn("Starting logging level WARN");
        logger.trace("Starting logging level TRACE");
    }
}
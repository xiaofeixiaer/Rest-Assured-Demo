package com.init;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class RestAssuredExtension implements BeforeAllCallback {

    private static String baseURI;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void initAssured() throws IOException {
        if (baseURI != null) {
            return;
        }

        String romEnv = System.getProperty("romEnv");
        baseURI = getBaseURI(romEnv);
        RestAssured.baseURI = baseURI;
    }

    private String getBaseURI(String name) throws IOException {
        if (name == null || name.isEmpty()) {
            logger.error(() -> "Error you need command like mvn test -DromEnv=dev");
            throw new IOException("env not found.");
        }

        String sourceFileName = "application_" + name.toLowerCase() + ".property";

        URL resource = this.getClass().getClassLoader().getResource(sourceFileName);

        if (resource == null) {
            String message = "Property file not found. " + sourceFileName;
            logger.error(() -> message);
            throw new IOException(message);
        }

        logger.info(() -> "Load " + sourceFileName);
        try (InputStream inputStream = resource.openStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty("baseURI");
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        initAssured();
    }
}

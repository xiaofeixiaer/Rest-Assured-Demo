package com.init;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.init.RomProperty.getPropertyValue;

public class RestAssuredExtension implements BeforeAllCallback {

    private static Boolean isiInit = false;

    private void initAssured() {
        if (!isiInit) {
            RestAssured.baseURI = getPropertyValue("baseURI");
            isiInit = true;
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        initAssured();
    }
}

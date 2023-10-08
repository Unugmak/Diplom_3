package constant;

import io.restassured.RestAssured;

public class BaseUrl {
    public static void setUp() {
        RestAssured.baseURI = Endpoints.BASE_URL;
    }
}
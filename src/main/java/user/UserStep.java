package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import constant.Endpoints;

import static constant.BaseUrl.setUp;
import static io.restassured.RestAssured.given;

public class UserStep {

    @Step("Регистрация пользователя")
    public static Response createUser(UserData user) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(Endpoints.REGISTER_API);
    }

    @Step("Авторизация пользователем")
    public static Response loginUser(UserData user) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(Endpoints.LOGIN_API);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        setUp();
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(Endpoints.USER_API);
    }
}
package com.bestbuy.crudTest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoreCrudTest extends TestBase {

    int idNumber;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }
    @Test//Get
    public void test001(){
        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }
    @Test//post
    public void test002(){
        StorePojo pojo = new StorePojo();
        pojo.setName("Rashi");
        pojo.setType("Big");
        pojo.setAddress("91 Ring Road");
        pojo.setAddress2("48 Circle road");
        pojo.setState("zombo");
        pojo.setCity("Hina");
        pojo.setZip("23145");
        pojo.setLat(541);
        pojo.setLng(15);
        pojo.setHours("Mon:11-5, Tue: 9-3");
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .when()
                .body(pojo)
                .post();
        response.then().statusCode(201);
        int idNumber =response.then().extract().path("id");
        System.out.println(idNumber);


    }
    @Test//patch
    public void test003(){
        StorePojo pojo = new StorePojo();
        pojo.setName("Dhunia");
        pojo.setAddress("89 mazda circle");
        pojo.setState("Dorina");
        pojo.setCity("Forina");

        Response response = given()
                .log().all()
                .header("Content - Type","application/json")
                .pathParam("id","8922")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);
    }
    @Test//delete
    public void test004(){
        Response response = given()
                .log().all()
                .header("Content - type","application/json")
                .pathParam("id","8922")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test//retive id
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content -Type","application/json")
                .pathParam("id","8922")
                .when()
                .get("/{id}");
        response.then().statusCode(404);
    }
}


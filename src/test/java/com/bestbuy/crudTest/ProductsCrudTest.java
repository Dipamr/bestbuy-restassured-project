package com.bestbuy.crudTest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCrudTest extends TestBase {
    int idNumber;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }
      @Test//get
    public void test001() {
         given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);

    }

    //post new
    @Test//post
    public void test002() {
        ProductPojo pojo = new ProductPojo();
        pojo.setName("panasonic batterys");
        pojo.setManufacturer("Panasonic");
        pojo.setModel("B09GW2T4T2");
        pojo.setType("basics");
        pojo.setUpc("78451232");
        pojo.setImage("img/ghjh");
        pojo.setPrice(7.99);
        pojo.setShipping(0);
        pojo.setDescription("easy to go");
        pojo.setUrl("amazon.co.uk");

     Response response=given()
             .log().all()
             .header("Content-Type","application/json")
             .when()
             .body(pojo)
             .post();
     response.then().statusCode(201);
     int idNumber = response.then().extract().path("id");
        System.out.println(idNumber);

   }

    @Test//patch
    public void test003() {
        ProductPojo pojo = new ProductPojo();
        pojo.setManufacturer("Samsung");
        pojo.setModel("B09GGWt7T2");
        pojo.setPrice(9.99);
        pojo.setShipping(0);

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","9999685")
                .when()
                .body(pojo)
                .patch("/{id}");
        response.then().statusCode(200);

    }
    @Test//delete
    public void test004(){
        Response response = given()
                .log().all()
                .header("Content - type","appliction/json")
                .pathParam("id","9999685")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }
    @Test//retrive id
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content - type","application/json")
                .pathParam("id","9999685")
                .when()
                .get("/{id}");
        response.then().statusCode(404);

    }
}

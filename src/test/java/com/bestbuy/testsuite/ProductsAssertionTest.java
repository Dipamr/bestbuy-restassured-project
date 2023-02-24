package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("products")
                .then().statusCode(200);
    }

    @Test//total=51957
    public void test011() {
        response.body("total", equalTo(51957));
    }

    @Test//store limit is equal to 10
    public void test012() {
        response.body("limit", equalTo(10));
    }

    @Test//ArrayListname Duracell - AAA Batteries (4-Pack)
    public void test013() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    @Test
    public void test014() {
        response.body("data.name", hasItem("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));
        response.body("data.name", hasItem("Duracell - AA Batteries (8-Pack)"));
        response.body("data.name", hasItem("Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test//productid 150115 inside categories of the third name householdbatteries
    public void test015() {
        response.body("data[4].categories[3]", hasKey("id"));
    }

    @Test//second name housewares of productid 333179
    public void test016() {
        response.body("data[9].categories[2]", hasKey("id"));
    }

    @Test//verify price 4.99
     public void test017() {
        Arrays.asList("data[3].price",equalTo(4.99));

    }

    @Test//product name Duracell - D Batteries (4-Pack)
    public void test018() {
        response.body("data[5].name", equalTo("Duracell - D Batteries (4-Pack)"));

    }
    @Test//product id 333179 9th product
    public void test019(){
        Arrays.asList("data[9].id",equalTo(333179));
    }
    @Test//product id 346575 have 5 categories
    public void test020(){
       Arrays.asList("data[9].categories",equalTo(5));
    }

}



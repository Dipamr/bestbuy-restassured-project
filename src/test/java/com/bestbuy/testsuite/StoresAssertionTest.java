package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
    }

    //verify the total is equal to 1561
    @Test
    public void test001() {
        response.body("total", equalTo(1561));
    }

    @Test
    public void test002() {
        response.body("limit", equalTo(10));
    }

    //check single name in array Inver Grove heights
    @Test
    public void test003() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //multiple 'Names'in the Arraylist(Roseville,Burnsville,Maplewood
    @Test
    public void test004() {
        response.body("data.name", hasItem("Roseville"));
        response.body("data.name", hasItem("Burnsville"));
        response.body("data.name", hasItem("Maplewood"));
    }

    @Test
    public void test005() {
        response.body("data[2].services[3].storeservices", hasKey("storeId"));
    }

    //check hashmap value createdAt inside store services map store name=Roseville
    @Test
    public void test006() {
        response.body("data[2].services[0].storeservices", hasKey("storeId"));
    }

    @Test //state=MN
    public void test007() {
        response.body("data[3].state", equalTo("MN"));

    }

    @Test//name=rocester of 9th store
    public void test008() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    @Test//storeid=11for6th store
    public void test009() {
        response.body("data[5].id", equalTo(11));
    }

    @Test//verify servicid 4 for 7th store
    public void test010() {
        response.body("data[6].services[3].id", equalTo(4));
    }
}

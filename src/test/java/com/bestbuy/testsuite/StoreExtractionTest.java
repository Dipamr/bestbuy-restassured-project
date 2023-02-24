package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class StoreExtractionTest {
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

    @Test//Extract limit
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("value of limit is :" + limit);
        Assert.assertEquals(10, limit);
        response.body("limit", equalTo(10));
    }

    @Test//extract total
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("value of total is :" + total);
        Assert.assertEquals(1561, total);
        response.body("total", equalTo(1561));
    }

    @Test//name of 5th store
    public void test003() {
        String nameOfstore = response.extract().path("data[4].name");
        System.out.println(nameOfstore);

    }

    @Test//storenames of all store
    public void test004() {
        List<String> strName = response.extract().path("data.name");
        System.out.println(strName);
        for (String a : strName)
            if (a.equals(1561)) {
                Assert.assertTrue(true);
            }

    }

    @Test//All store Id
    public void test005() {
        List<Integer> listOfid = response.extract().path("data.id");
        System.out.println(listOfid);
        for (Integer a : listOfid)
            if (a.equals(1567)) {
                Assert.assertTrue(true);
            }
    }

    @Test//size of Data List
    public void test006() {
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);

    }

    @Test//value of store name=St Cloud
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println(values);

    }

    @Test//address of store name Rochester
    public void test008() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println(address);
    }

    @Test//services 8th store
    public void test009() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println(services);
    }

    @Test//services name=Windows store
    public void test010() {
        List<HashMap<?, ?>> storeservice = response.extract().path("data.findAll{it.service ='Windows Store'}.services");
        System.out.println(storeservice);

    }

    @Test//all storeid
    public void test011() {
        List<Integer> storeIdOfAllStore = response.extract().path("data.services.storeservices.storeId");
        System.out.println("storeId of all the stores :" + storeIdOfAllStore);

    }

    @Test//Get id of all store
    public void test012() {
        List<Integer> listOfstoreid = response.extract().path("data.id");
        System.out.println(listOfstoreid);
    }

    @Test//store names where state name=ND
    public void test013() {
        List<String> stateND = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println(stateND);

    }

    @Test//name=Rochester
    public void test014() {
        List<Integer> noOfServices = response.extract().path("data.findAll{it.name=='Rochester'}.services");
        System.out.println(noOfServices);

    }

    @Test//name=windows store
    public void test015() {
        List<Integer> createdAt = response.extract().path("data.findAll{it.name ='Windows store'}.services");
        System.out.println(createdAt);
    }

    @Test//find the name all services wherecstore name="fargo
    public void test016() {
      String nameOfallservices=response.extract().path("data[7].name");
        System.out.println(nameOfallservices);

    }
    @Test//find the zip of all the store
    public void test017(){
        List<Integer> listOfzip = response.extract().path("data.zip");
        System.out.println(listOfzip);

    }
    @Test//find the zip of store name=Roseville
    public void test018(){
        String zipofstorename=response.extract().path("data[2].zip");
        System.out.println(zipofstorename);
    }
    @Test
    public void test019(){
        List<?> storeserviess =response.extract().path("data.findAll{it.name ='Magnolia Home Theater'}.services");
        System.out.println(storeserviess);

    }
    @Test
    public void test020(){
        List<Integer> listOfLat = response.extract().path("data.lat");
        System.out.println(listOfLat);

    }




    }











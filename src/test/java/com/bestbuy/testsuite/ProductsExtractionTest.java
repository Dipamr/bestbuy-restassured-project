package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductsExtractionTest {
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
    @Test//extract limit
    public void test021(){
        response.body("limit", equalTo(10));
    }
    @Test//extraxt total
    public void test022(){
        response.body("total", equalTo(51957));
    }
    @Test//names of all products
    public void test023(){
        List<String > products = response.extract().path("data.name");
        System.out.println(products);

    }
    @Test//extract products id
    public void test024(){
        List<Integer > productId = response.extract().path("data.id");
        System.out.println(productId);
    }
    @Test//extract names
    public void test025(){
        List<String > name = response.extract().path("data.name");
        System.out.println(name);
    }
    @Test//size of data
    public void test026(){
        List<Integer> dataSize = response.extract().path("data");
        int size = dataSize.size();
        System.out.println(size);

    }
    @Test//get all the value product where product name =MAX Batteries AA (4-Pack)
    public void test027(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println(values);
    }
    @Test//get model of the product where product name=Energizer - N Cell E90 Batteries (2-Pack)
    public void test028(){
       List<String> model = response.extract().path("data.findAll{it.name=='Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product is : " + model);

    }
    @Test//get all categories of 8th products
    public void test029(){
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println(categories);

    }
    @Test//get categories of the store where product id =150115
    public void test030(){
        List<HashMap<?, ?>> categories = response.extract().path("data.findAll{it.id ==150115}.categories");
        System.out.println(categories);

    }
    @Test//get all description of all products
    public void test31(){
        List<String> description = response.extract().path("data.description");
        System.out.println(description);
    }
    @Test//get id of all the categories of all products
    public void test032(){
        List<Integer > categoriesOfallTheProducts = response.extract().path("data.categories.id");
        System.out.println(categoriesOfallTheProducts);
    }
    @Test//find the product names where type =HardGood
    public void test033(){
        List<String> productsName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println(productsName);
    }
    @Test//find total number of categories where product name=Duracell - AA 1.5V CopperTop Batteries (4-pack)
    public void test034(){
//
        List<String> totalNumberOfcategories = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println(totalNumberOfcategories);
    }
    @Test//find the createdAt for all products price<5.49
    public void test035(){
        List<String>createdAt=response.extract().path("data.findAll{it.price <5.49}.createdAt");
        System.out.println(createdAt);
    }
    @Test//find the name of all categories where product name =Energzer MAX batteries AA(4-pack)
    public void test036() {
        List<String> nameOfAllCategories = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println(nameOfAllCategories);
    }
    @Test//find manufacturer of all products
    public void test037(){
        List<String > manufacturer = response.extract().path("data.manufacturer");
        System.out.println(manufacturer);
    }
    @Test//find the image of products whose manufature is=Energizer
    public void test038(){
        List<Integer> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println(image);
    }
    @Test//find the createdat for all categories products whoses price>5.99
    public void test039(){
        List<String>createdAtforAllcategories=response.extract().path("data.findAll{it.price >5.49}.categories");
        System.out.println(createdAtforAllcategories);
    }
    @Test//find the uri of all the products
    public void test040(){
        List<String > products = response.extract().path("data.url");
        System.out.println(products);
    }






}


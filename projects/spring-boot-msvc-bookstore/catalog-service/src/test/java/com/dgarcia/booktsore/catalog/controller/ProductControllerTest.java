package com.dgarcia.booktsore.catalog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import com.dgarcia.booktsore.catalog.AbstractIT;
import com.dgarcia.booktsore.catalog.entity.dto.ProductRecord;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT{
   
   
    @Test
    void shouldReturnListProducts() {

        RestAssured.given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/v1/products?page=1")
        .then()
        .statusCode(200)
        .body("data", hasSize(10))
        .body("totalElements", is(15))
        .body("pageNumber", is(0))
        .body("totalPages", is(2))
        .body("isFirst", is(true))
        .body("isLast", is(false))
        .body("hasNext", is(true))
        .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode(){
       ProductRecord product =  RestAssured.given()
        .contentType(ContentType.JSON)
        .get("/api/v1/products/code/{code}","P100")
        .then()
        .statusCode(200)
        .assertThat()
        .extract()
        .body()
        .as(ProductRecord.class);

        Assertions.assertThat(product.code()).isEqualTo("P100");
        Assertions.assertThat(product.name()).isEqualTo("The Hunger Games");
        
    }

    @Test
    void shouldReturnNotFoundGetProductByCodeNotExists(){
         RestAssured.given()
        .contentType(ContentType.JSON)
        .get("/api/v1/products/code/{code}","P10023")
        .then()
        .statusCode(404)
        .body("status",is(404))
        .body("title", is("Product Not Found"));
        
    }
}

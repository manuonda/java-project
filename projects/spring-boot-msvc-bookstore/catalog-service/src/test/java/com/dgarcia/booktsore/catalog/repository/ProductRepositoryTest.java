package com.dgarcia.booktsore.catalog.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.dgarcia.booktsore.catalog.entity.domain.Product;



/**
 * Nota 1: Recommendations use repository spring.datasource.url
 * becasues in Import COntainerConfig use RabbitMq that not necessario
 */
@DataJpaTest(
    properties = {
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
    }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/test-data.sql")
//@Import(ContainerConfig.class)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    Product product;


    @BeforeEach
    void setUp(){
       product = Product.builder()
       .name("Product 1")
       .code("Code 1")
       .description("Description one")
       .imageUrl("Url one")
       .build();

    }

    @Test
    void givenObjectProduct_whenSaveObjectProduct_thenReturnObjectProduct(){

        //when
        this.repository.save(product);
        //then
        assertThat(product).isNotNull();
        assertThat(product.getId()).isPositive();
    }

    @Test
    void givenProductsList_whenFindAll_returnListObjectProducts(){
        //given
        Product product1 = Product.builder()
        .name("Product 1")
        .code("Code1")
        .description("Description one")
        .price(Double.valueOf(1.2))
        .build();
        Product product2 = Product.builder()
        .name("Product 2")
        .code("Code one")
        .price(Double.valueOf(1))
        .description("description one").build();
        this.repository.saveAll(List.of(product1,product2));
        
        //when
        List<Product> products = this.repository.findAll();
        
        //then
        assertThat(products).isNotNull();
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isGreaterThan(0);
    }


    @Test
    void given_whenSearchByCode_thenReturnObjectCode(){
        //then 
        Product productCode = this.repository.findByCode("P100").orElseThrow();
        assertThat(productCode.getCode()).isEqualTo("P100");
        assertThat(productCode.getName()).isEqualTo("The Hunger Games");
    }

    @Test
    void shouldReturnEmptyProduct(){
        Optional<Product> productByCode = this.repository.findByCode("P1032");
        assertThat(productByCode.empty()).isEmpty();
    }

}
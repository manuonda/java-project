package com.tutorial.subprocess.facade;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.tutorial.subprocess.dto.ProductDetailDTO;
import com.tutorial.subprocess.entity.Inventory;
import com.tutorial.subprocess.entity.Price;
import com.tutorial.subprocess.entity.Product;
import com.tutorial.subprocess.service.InventoryService;
import com.tutorial.subprocess.service.PriceService;
import com.tutorial.subprocess.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class ProductAsyncFacade {

    private final ProductService productService;
    private final PriceService priceService;
    private final InventoryService inventoryService;



      public CompletableFuture<Product> getProductById(long productId) {
        return CompletableFuture
                .supplyAsync(() -> productService.findById(productId));
    }

    public CompletableFuture<Price> getPriceByProductById(long productId) {
        return CompletableFuture
                .supplyAsync(() -> priceService.getPriceByProductId(productId));
    }

    public CompletableFuture<Inventory> getInventoryByProductId(long productId) {
        return CompletableFuture
                .supplyAsync(() -> inventoryService.getInventoryByProductId(productId));
    }

    public ProductDetailDTO getProductDetails(long id){
        //fetch all async
        CompletableFuture<Product> productFuture = getProductById(id);
        CompletableFuture<Price> priceFuture = getPriceByProductById(id);
        CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(id);

        //Wait for all
        CompletableFuture.allOf(productFuture, priceFuture, inventoryFuture);

        //Combine the result
        Product product = productFuture.join();
        Price price = priceFuture.join();
        Inventory inventory = inventoryFuture.join();

        //build dto 
        return new ProductDetailDTO(id, product.getCategory().getName(),
        product.getName(), product.getDescription(),
        inventory.getAvailableQuantity(), price.getPrice(),
        inventory.getStatus());
        
    }

 
}

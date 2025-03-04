package com.tutorial.subprocess.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tutorial.subprocess.dto.ProductDetailDTO;
import com.tutorial.subprocess.entity.Inventory;
import com.tutorial.subprocess.entity.Price;
import com.tutorial.subprocess.entity.Product;
import com.tutorial.subprocess.service.InventoryService;
import com.tutorial.subprocess.service.PriceService;
import com.tutorial.subprocess.service.ProductService;


@Component
@Slf4j
public class ProductSyncFacade {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PriceService priceService;


    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Sync facade for getting product details for the product id {}",productId);

        //fetch product
        Product product = productService.findById(productId);

        //fetch the price
        Price price = priceService.getPriceByProductId(productId);

        //fetch the inventory
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        //combine result
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }

}
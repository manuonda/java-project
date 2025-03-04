# Example Project: Divide in Subprocess Asyncronic the call of api

This project emulates a series of services: `ProductService`, `PriceService`, `InventoryService`. Two working modes are established: synchronous and asynchronous.

## Concepts

### Synchronous
In a synchronous process, tasks are executed sequentially. Each task must be completed before the next one can begin. This can lead to longer wait times if a task takes a long time to complete.

### Asynchronous
In an asynchronous process, tasks can be executed concurrently. This allows multiple tasks to run at the same time, improving efficiency and reducing wait times.

## Using `CompletableFuture` in Java
For the asynchronous part, we use Java's `CompletableFuture`. `CompletableFuture` is a class that represents a future result of an asynchronous operation. It allows writing non-blocking code and handling concurrent tasks more efficiently.

### Advantages of `CompletableFuture`
- **Non-blocking**: Allows other tasks to execute while waiting for the result of an operation.
- **Composition**: Facilitates combining multiple asynchronous tasks.
- **Error handling**: Provides methods to handle exceptions more clearly.

## Facade Pattern
We use the Facade pattern to call the three services (`ProductService`, `PriceService`, `InventoryService`). This pattern simplifies interaction with a set of complex interfaces by providing a unified interface.

### Usage Example
```java
public class ProductFacade {
    private ProductService productService;
    private PriceService priceService;
    private InventoryService inventoryService;

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
```

## Running the Service
To run the service, you can use the following commands:

```sh
 Async petition:

 time curl -X GET http://localhost:8085/api/v1/products/2/async
 {"id":2,"categoryName":"Electronics","name":"Laptop","description":"15-inch laptop with 8GB RAM, 256GB SSD","availableQuantity":50,"price":1299.99,"status":"available"}
 real    0m2,015s
 user    0m0,003s
 sys     0m0,002s

Sync petition:
time curl -X GET http://localhost:8085/api/v1/products/2/sync
{"id":2,"categoryName":"Electronics","name":"Laptop","description":"15-inch laptop with 8GB RAM, 256GB SSD","availableQuantity":50,"price":1299.99,"status":"available"}
real    0m6,041s
user    0m0,007s
sys     0m0,009s

```

## Conclusion
This project demonstrates how to work with services synchronously and asynchronously in Java, using `CompletableFuture` to improve efficiency and the Facade pattern to simplify interaction with multiple services.

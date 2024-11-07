import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntermediaOperationsStream {

    public static void main(String[] args){
      
     /*
      The filter() method helps us to filter the elements by a specific 
      parameter represented by a boolean condition. 
      Only the elements that match the requirement will be sent down the 
      pipeline for further processing. 
      For instance, let’s find and print out all even numbers
     */   
      System.out.println("* Filter");
      List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
      numbers.stream()
      .filter( n -> n % 2 == 0)
      .forEach(System.out::println);


      /*
       * The map() method accepts a stream of elements, performs some 
       *  operation on each element, and returns a stream of modified 
       *  elements.
       *  Schematically, it looks like that:
       */
       System.out.println("* Map");
       List<String> words =  Arrays.asList("Apple","Banana","Pear");
       words.stream()
       .map(String::toUpperCase)
       .forEach(System.out::println);

       /**
        * The flatMap() method enables us to obtain a stream for each element of the incoming 
         stream, and then unify these streams into one.
        */
        System.out.println(" ** FlatMap");

        Product chair = new Product("Chair", 150);
        Product table = new Product("Table", 350);
        Product pencil = new Product("Pencil", 2);

        Order firstOrder = new Order(Arrays.asList(chair, table));
        Order secondOrder = new Order(Arrays.asList(pencil, chair));

        List<Order> orders = Arrays.asList(firstOrder, secondOrder);
        orders.stream()
        .flatMap( order -> order.getProducts().stream())
        .map(Product::name)
        .forEach(System.out::println);

        /*
         * The sorted() method enables us to sort the elements of the stream.
         *  Let’s take a look at the example above: we have a stream of 
         * Strings, and we can simply add sorted() without parameters 
         * to sort them in natural (here, alphabetical) order, or we can use 
         * Comparator.reverseOrder() 
         * in this method to sort the elements in descending order
         */

         System.out.println("**Sorted");
         orders.stream()
         .flatMap(order -> order.getProducts().stream())
         .map(Product::name)
         .sorted(Comparator.reverseOrder())
         .forEach(System.out::println);

         System.out.println("**Distinct()");
         orders.stream()
         .flatMap(order -> order.getProducts().stream())
         .map(Product::name)
         .distinct()
         .forEach(System.out::println);

         /**
          * The skip() method enables us to skip 
          first N elements of the stream:
          */

          System.out.println("**skip()");

          numbers.stream().skip(2)
          .forEach(System.out::println);


          System.out.println("**limit()");

          numbers.stream().limit(2)
          .forEach(System.out::println);
    }

    static class Order {
        List<Product> products;
        double totalPrice;

        public Order(List<Product> products) {
            this.products = products;
            calculatetotalPrice(products);
        }

        private void calculatetotalPrice(List<Product> products) {
            totalPrice = products.stream().map( product -> product.price).reduce(0,Integer::sum);
        }

        public List<Product> getProducts() {
            return products;
        }
        public double getTotalPrice() {
            return totalPrice;
        }
    }
    record Product(String name, int price){

    }

    record Product2(String name, int price){

    }
    
    
}




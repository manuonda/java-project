package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TutorialStreamApplication {


	//Article 

	public static void main(String[] args) {
		SpringApplication.run(TutorialStreamApplication.class, args);
	    
		 //Finite --- Stream
		 System.out.println("*** Finites Stream  ****");
		 List<String> countries = Arrays.asList("Argentina","Bolivia","France");
		 Stream<String> countriesStream = countries.stream();
		 countriesStream.forEach(System.out::println);

		 //Stream from array  - Stream primitve
		 int[] numbers = {1,2,3,4,5};
		 IntStream numbersStream = Arrays.stream(numbers);
         numbersStream.forEach(System.out::println); 

		 //Stream from a file 
		 try {
			
		 } catch (Exception e) {
			// TODO: handle exception
		 }


		 // == Infinite Streams 
		 System.out.println(" == Infinite Streams =====");;
		 Random random = new Random();
		 IntStream randomIntStream = IntStream.generate(random::nextInt).limit(10);
		 randomIntStream.forEach(System.out::println);

		 // other 
		 IntStream.generate(random::nextInt)
		 .limit(10)
		 .forEach(System.out::println);

         //Parallel streams
		 System.out.println("==== Parallel Streams ======");
		 List<Integer> nums = Arrays.asList(1,2,3,4,5);
		 int sum = nums.stream().parallel().map(  i -> i + 1)
		 .reduce(0,Integer::sum);
		 System.out.println("Suma : " + sum);

		 int sum2 = nums.parallelStream().map( i -> i + 1)
		 .reduce( 0 , Integer::sum);

		 System.out.println("Sum parallelStream : " + sum2);


		 int total = IntStream.range(1,10).map(TutorialStreamApplication::principal).sum();
         System.out.println("Total threads: " + total); 
	}

	public static int principal(int numero) {	
	 try {
		Thread.sleep(1000);
	 } catch (Exception e) {
		// TODO: handle exception
	 }
	 return numero * 2;
	}

}

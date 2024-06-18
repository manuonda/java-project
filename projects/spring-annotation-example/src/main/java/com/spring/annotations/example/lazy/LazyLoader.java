package com.spring.annotations.example.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bean bajo demanda , no se carga en el contexto 
 * de Spring.
 */

@Component
@Lazy
public class LazyLoader {
    
    public LazyLoader(){
        System.out.println("LazyLoader...");
    }
}

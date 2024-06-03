package com.springboot.blog;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractBaseContainer {

    static final  MySQLContainer mySQLContainer ;

    static {
        mySQLContainer = new MySQLContainer<>("mysql:8");
            
        mySQLContainer.start();
    }
}

package com.springboot.blog;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractBaseContainer {

    static final  MySQLContainer mySQLContainer ;

    static {
        mySQLContainer= new MySQLContainer()
                ; /*.withUsername("root")
                .withPassword("root")
                .withDatabaseName("my_blog");
                */
        mySQLContainer.start();
    }
}

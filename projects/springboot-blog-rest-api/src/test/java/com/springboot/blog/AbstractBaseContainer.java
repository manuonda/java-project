package com.springboot.blog;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractBaseContainer {

    static final MySQLContainer mySQLContainer;

    @ClassRule
    public static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8");

    static {
        mySQLContainer.start();
    }
}
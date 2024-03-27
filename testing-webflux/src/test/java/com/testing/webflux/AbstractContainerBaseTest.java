package com.testing.webflux;

import org.testcontainers.containers.MongoDBContainer;

public abstract  class AbstractContainerBaseTest {

    static final MongoDBContainer MONGO_DB_CONTAINER;

    static {
        MONGO_DB_CONTAINER = new  MongoDBContainer("mongo:latest").withExposedPorts(27017);

        MONGO_DB_CONTAINER.start();
        var mappedPort = MONGO_DB_CONTAINER.getMappedPort(27017);
        System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
    }

}

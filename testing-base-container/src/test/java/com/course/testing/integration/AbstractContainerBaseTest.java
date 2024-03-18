package com.course.testing.integration;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract  class AbstractContainerBaseTest {
    static final PostgreSQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new  PostgreSQLContainer<>("postgres:latest")
                .withUsername("root")
                .withPassword("root")
                .withDatabaseName("db_course_testing");

        MY_SQL_CONTAINER.start();
    }

}

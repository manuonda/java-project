package com.testing.webflux;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;



@Testcontainers
public class AbstractContainerBaseTest {


    @Container
    @ServiceConnection
    public static final  MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"))
            .withExposedPorts(27017)
                .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1));


    static{
        mongoDBContainer.start();
    }
}

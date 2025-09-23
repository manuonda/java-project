package com.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {
   static ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

   @Test
    void verifiesModularStructure(){
       modules.verify();
   }
}

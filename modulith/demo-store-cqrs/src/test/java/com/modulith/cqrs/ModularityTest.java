package com.modulith.cqrs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import java.util.stream.Collectors;

/**
 * Test CRÍTICO que verifica la estructura modular.
 *
 * ¿Para qué sirve?
 * 1. Valida que Spring Modulith reconoce nuestros módulos
 * 2. Detecta violaciones de encapsulamiento
 * 3. Previene dependencias circulares
 * 4. Genera documentación automática
 *
 * Este test debe ejecutarse ANTES de implementar funcionalidad.
 */
@Slf4j
public class ModularityTest {

    // Spring Modulith analiza la aplicación y encuentra módulos
    ApplicationModules modules = ApplicationModules.of(DemoStoreCqrsApplication.class);

    /**
     * Test principal - verifica todas las reglas modulares.
     *
     * ¿Qué verifica?
     * - Cada paquete bajo com.geovannycode.store es un módulo
     * - No hay violaciones de acceso entre módulos
     * - No hay dependencias circulares
     * - Las APIs públicas están bien definidas
     */
    @Test
    void shouldHaveValidModularStructure(){
        modules.verify();
    }


    /**
     * Genera documentación automática de la arquitectura.
     *
     * ¿Qué genera?
     * - Diagramas PlantUML de los módulos
     * - Documentación AsciiDoc
     * - Module Canvas (diagramas C4)
     *
     * Los archivos se generan en: target/spring-modulith-docs/
     */
    @Test
    void shouldGenerateDocumentation(){
          new Documenter(modules)
                  .writeDocumentation()  //AsciiDoc
                  .writeModuleCanvases() //Diagramas C4
                  .writeIndividualModulesAsPlantUml(); //PlantUML
    }

    /**
     * Test Informativo -  muestra que modulos encontro Spring Modulith
     */
    @Test
    void showModuleStructure(){
        log.info("Showing module structure");
        modules.forEach(module -> {
            var name = module.getDisplayName();
            var basePkg = module.getBasePackage().getName();

            // Dependencias directas (o usa getDependencies(modules, DependencyDepth.DIRECT, ...) si prefieres)
            long directDeps = module.getDirectDependencies(modules).uniqueModules().count();

            String depsList = module.getDirectDependencies(modules)
                    .uniqueModules()
                    .map(ApplicationModule::getDisplayName)
                    .sorted()
                    .collect(Collectors.joining(", "));

            if (depsList.isBlank()) depsList = "(ninguna)";

            log.info("📦 Módulo: {}", name);
            log.info("   📁 Paquete: {}", basePkg);
            log.info("   🔗 Dependencias directas ({}): {}", directDeps, depsList);
        });

    }




}

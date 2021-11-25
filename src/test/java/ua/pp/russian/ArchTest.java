package ua.pp.russian;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ua.pp.russian");

        noClasses()
            .that()
            .resideInAnyPackage("ua.pp.russian.service..")
            .or()
            .resideInAnyPackage("ua.pp.russian.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ua.pp.russian.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}

package br.com.portifolify;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import javax.transaction.Transactional;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.tngtech.archunit.lang.conditions.ArchConditions.beAnnotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(
        packages = CleanArchitectureRuleSetTest.BASE_PACKAGE,
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class CleanArchitectureRuleSetTest {

    private static final int USECASES_PUBLIC_METHODS_LIMIT = 1;
    public static final String BASE_PACKAGE = "br.com.portifolify";
    private static final String ENTRYPOINTS_PACKAGE = BASE_PACKAGE + "..entrypoint..";
    private static final String USECASES_PACKAGE = BASE_PACKAGE + ".core.usecase..";
    private static final String SERVICES_PACKAGE = BASE_PACKAGE + ".core.service..";
    private static final String DATAPROVIDER_PACKAGE = BASE_PACKAGE + ".core.dataprovider..";

    private static final String ENTITY_PACKAGE = "br.com.portifolify.application.dataprovider.impl.persistence.entity..";

    private static final String USECASE_IMPL_PACKAGE = BASE_PACKAGE + ".core.usecase.impl..";

    static final Architectures.LayeredArchitecture LAYERS = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Application").definedBy("..")
            .layer("Core").definedBy("..core..")
            .layer("Domain").definedBy("..domain..");

    @ArchTest
    static final ArchRule RESPECT_INDIRECTION_RULE = LAYERS
            // Add constraints
            .whereLayer("Application").mayNotBeAccessedByAnyLayer()
            .whereLayer("Core").mayOnlyBeAccessedByLayers("Application")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Core", "Application");

    @ArchTest
    static final ArchRule ENTRYPOINT_SHOULD_RESIDE_IN_APPLICATION =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameContaining("Controller")
                    .or()
                    .haveSimpleNameContaining("Entrypoint")
                    .or()
                    .haveSimpleNameContaining("Endpoint")
                    .or()
                    .haveSimpleNameContaining("Api")
                    .or()
                    .haveSimpleNameContaining("Job")
                    .or()
                    .haveSimpleNameContaining("Http")
                    .should()
                    .resideInAPackage(ENTRYPOINTS_PACKAGE)
                    .because("Entrypoints are just how outside world interacts with the application");

    @ArchTest
    static final ArchRule USESCASES_SHOULD_ONLY_CALL_SERVICES_CLASSES =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("UseCaseImpl")
                    .should()
                    .accessClassesThat()
                    .resideInAPackage(SERVICES_PACKAGE)
                    .because("Use Cases only orchestrate service classes");

    @ArchTest
    static final ArchRule USECASES_SHOULD_BE_TRANSACTIONAL =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(USECASE_IMPL_PACKAGE)
                    .should()
                    .beAnnotatedWith(Transactional.class)
                    .because("Use cases are classes that unite fragments of other operations and are therefore"
                            + " the ideal point for encompassing transactions");

    @ArchTest
    static final ArchRule SERVICE_CLASSES_CANNOT_CALL_EACH_OTHER =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("ServiceImpl")
                    .should()
                    .accessClassesThat()
                    .resideOutsideOfPackage(SERVICES_PACKAGE)
                    .because("Service classes need to pass security to the caller, so they must have unique "
                            + "responsibility, that is, have a clear and well-defined context and objective");

    @ArchTest
    static final ArchRule USECASES_SHOULD_RESIDE_INSIDE_CORE =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("UseCase")
                    .should()
                    .resideInAPackage(USECASES_PACKAGE)
                    .because("UseCases are the core of our business, hence they should stay inside core");

    @ArchTest
    static final ArchRule DATAPROVIDERS_IMPLEMENTATION_RESIDE_OUTSIDE_CORE =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(DATAPROVIDER_PACKAGE)
                    .should().dependOnClassesThat().doNotHaveFullyQualifiedName("org.springframework.")
                    .because("Dataprovider implementations must be inside the application-layer");

    @ArchTest
    static final ArchRule ENTITIES_SHOULD_BE_FREE_OF_CYCLES =
            SlicesRuleDefinition.slices()
                    .matching("..entity.(*)..")
                    .should()
                    .beFreeOfCycles()
                    .allowEmptyShould(true)
                    .because("We should not have entities with cyclical dependencies");

    @ArchTest
    static final ArchRule ENTITIES_SHOULD_NOT_BE_COMPONENTS =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("..entity")
                    .or()
                    .resideInAPackage("..model")
                    .should()
                    .notBeAnnotatedWith(Component.class)
                    .allowEmptyShould(true)
                    .because("Entities are not components, this was a common mistake in CRs so added this rule");

    @ArchTest
    static final ArchRule TABLES_SHOULD_BE_ANNOTATED_BY_ENTITY_AND_RESIDE_OUTSIDE_CORE =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("Entity")
                    .should()
                    .resideInAPackage(ENTITY_PACKAGE)
                    .allowEmptyShould(true)
                    .because("Table entities belong to the Dataprovider layer, and those annotations are required"
                            + " to use JPA in our architecture");

    @ArchTest
    static final ArchRule NO_JAVA_UTIL_LOGGING = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    static final ArchRule NO_FIELD_INJECTION = noFields()
            .should(beAnnotatedWithAnInjectionAnnotation())
            .as("no classes should use field injection")
            .because("field injection is considered harmful; use constructor injection or setter injection "
                    + "instead; see https://stackoverflow.com/q/39890849 for detailed explanations");

    @ArchTest
    static final ArchRule USECASES_SHOULD_HAVE_ONLY_ONE_PUBLIC_METHOD =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameEndingWith("UseCase")
                    .should(containOnlyOnePublicMethod())
                    .because("Use cases tell a story, it's about \"WHAT\" not \"HOW\"");

    private static ArchCondition<JavaField> beAnnotatedWithAnInjectionAnnotation() {
        ArchCondition<JavaField> annotatedWithSpringAutowired = beAnnotatedWith("org.springframework.beans"
                + ".factory.annotation.Autowired");
        ArchCondition<JavaField> annotatedWithGuiceInject = beAnnotatedWith("com.google.inject.Inject");
        ArchCondition<JavaField> annotatedWithjavaxInject = beAnnotatedWith("javax.inject.Inject");
        ArchCondition<JavaField> annotatedWithjavaxResource = beAnnotatedWith("javax.annotation.Resource");
        return annotatedWithSpringAutowired
                .or(annotatedWithGuiceInject)
                .or(annotatedWithjavaxInject).or(annotatedWithjavaxResource)
                .as("be annotated with an injection annotation");
    }

    private static ArchCondition<JavaClass> containOnlyOnePublicMethod() {

        return new ArchCondition<JavaClass>("Only one public method") {

            @Override
            public void check(final JavaClass clazz, final ConditionEvents events) {

                final String name = clazz.getName();
                final Set<JavaMethod> methodsSet = clazz.getMethods();
                int publicMethodsCount = 0;

                for (final JavaMethod javaMethod : methodsSet) {
                    if (javaMethod.getModifiers()
                            .contains(JavaModifier.PUBLIC)) {
                        publicMethodsCount = publicMethodsCount + 1;
                    }
                }

                if (publicMethodsCount > USECASES_PUBLIC_METHODS_LIMIT) {
                    throw new AssertionError(String.format(
                            "Class %s contains %d public methods, when limit is %d",
                            name,
                            publicMethodsCount,
                            USECASES_PUBLIC_METHODS_LIMIT
                    ));
                }
            }
        };
    }
}

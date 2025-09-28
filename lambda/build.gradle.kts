plugins {
    java
    id("software.amazon.smithy.gradle.smithy-base").version("1.3.0")
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    smithyBuild("software.amazon.smithy.java:plugins:0.0.3")

    implementation("software.amazon.smithy.java:core:0.0.3")
    implementation("software.amazon.smithy.java:framework-errors:0.0.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.0")

    implementation(project(":model"))

    testImplementation("junit:junit:4.13.2")
}

afterEvaluate {
    val typePath = smithy.getPluginProjectionPath("java-types", "java-type-codegen")
    sourceSets {
        main {
            java {
                srcDir(typePath)
            }
        }
    }
}

tasks {
    val smithyBuild by getting
    compileJava {
        dependsOn(smithyBuild)
    }
}

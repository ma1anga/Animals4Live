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

    implementation(project(":model"))

    annotationProcessor("com.google.auto.service:auto-service:1.1.1")
    compileOnly("com.google.auto.service:auto-service:1.1.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")

    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    implementation("com.amazonaws:aws-lambda-java-events:3.11.0")

    implementation("software.amazon.smithy.java:aws-lambda-endpoint:0.0.3")
    implementation("software.amazon.smithy.java:server-api:0.0.3")
    implementation("software.amazon.smithy.java:aws-server-restjson:0.0.3")
    implementation("software.amazon.smithy.java:server-rpcv2-cbor:0.0.3")

    testImplementation("junit:junit:4.13.2")
}

val genDir = layout.buildDirectory.dir(
    "smithyprojections/lambda/java-server/java-server-codegen"
)

sourceSets.named("main") {
    java.srcDir(genDir)
}

tasks.named("compileJava") { dependsOn("smithyBuild") }

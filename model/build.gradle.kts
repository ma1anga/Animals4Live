plugins {
    java
    id("software.amazon.smithy.gradle.smithy-jar").version("1.3.0")
}

dependencies {
    implementation("software.amazon.smithy:smithy-aws-traits:1.61.0")
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

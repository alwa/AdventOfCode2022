import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("org.sonarqube") version "3.5.0.2730"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}

apply(plugin = "org.sonarqube")

sonarqube {
    properties {
        property("sonar.projectKey", "alwa_AdventOfCode2022")
        property("sonar.organization", "alwa")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
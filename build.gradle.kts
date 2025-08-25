plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
        implementation("org.springframework.kafka:spring-kafka")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
        implementation("org.jsoup:jsoup:1.17.2")
        implementation("com.rometools:rome:1.18.0")
        implementation("org.apache.commons:commons-lang3:3.14.0")
        implementation("commons-io:commons-io:2.15.1")
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
        runtimeOnly("org.slf4j:slf4j-simple:2.0.13")
        implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
        implementation("org.yaml:snakeyaml:2.2")

        runtimeOnly("org.postgresql:postgresql")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.boot:spring-boot-testcontainers")
        testImplementation("org.testcontainers:junit-jupiter")
        testImplementation("org.testcontainers:kafka")
        testImplementation("org.testcontainers:postgresql")
        testImplementation("org.springframework.kafka:spring-kafka-test")
        testImplementation("io.projectreactor:reactor-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

}

tasks.withType<Test> {
	useJUnitPlatform()
}

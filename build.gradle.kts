plugins {
    java
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "field"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf");
    implementation("org.springframework.boot:spring-boot-starter-web");
    testImplementation("org.springframework.boot:spring-boot-starter-test");
    implementation ("org.springframework.boot:spring-boot-starter-jdbc");
    runtimeOnly ("com.h2database:h2");
    testImplementation("org.springframework.boot:spring-boot-starter-test");
}

tasks.withType<Test> {
    useJUnitPlatform()
}

import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "kuzmin.anton"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val agent = configurations.create("agent")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.17.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("com.redis:testcontainers-redis:2.2.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    agent("io.opentelemetry.javaagent:opentelemetry-javaagent:2.17.0")
}

val copyAgent = tasks.register<Copy>("copyAgent") {
    from(agent.singleFile)
    into(layout.buildDirectory.dir("agent"))
    rename("opentelemetry-javaagent-.*\\.jar", "opentelemetry-javaagent.jar")
}

tasks.named<BootRun>("bootTestRun") {
    dependsOn(copyAgent)
    jvmArgs = listOf(
        "-javaagent:build/agent/opentelemetry-javaagent.jar",
        "-Dotel.service.name=test-service",
        "-Dotel.exporter.otlp.endpoint=http://localhost:4318",
        "-Dotel.exporter.otlp.protocol=http/protobuf",
        "-Dotel.metrics.exporter=none",
        "-Dotel.logs.exporter=none",
        "-Dotel.javaagent.logging=application",
        "-Dotel.instrumentation.experimental.span-suppression-strategy=span-kind",
    )
    mainClass.set("kuzmin.opentelemetry.TestApplicationKt")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

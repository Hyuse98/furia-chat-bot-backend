plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.hyuse"
version = "0.0.1-SNAPSHOT"

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

extra["snippetsDir"] = file("build/generated-snippets")
extra["springModulithVersion"] = "1.3.5"

dependencies {
	// Spring Boot Starters
//	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
//	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
//	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")

	// Spring Modulith
//	implementation("org.springframework.modulith:spring-modulith-events-api")
//	implementation("org.springframework.modulith:spring-modulith-starter-core")
//	implementation("org.springframework.modulith:spring-modulith-starter-jpa")

	// Observabilidade e Tracing
//	implementation("io.micrometer:micrometer-tracing-bridge-brave")
//	implementation("io.zipkin.reporter2:zipkin-reporter-brave")
//	runtimeOnly("io.micrometer:micrometer-registry-otlp")
//	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
//	runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
//	runtimeOnly("org.springframework.modulith:spring-modulith-observability")

	// Banco de Dados
	implementation("org.flywaydb:flyway-core")
//	implementation("org.flywaydb:flyway-database-postgresql")
	runtimeOnly("com.h2database:h2")
//	runtimeOnly("org.postgresql:postgresql")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// AMQP Extra
	implementation("org.springframework.amqp:spring-rabbit-stream")
//	runtimeOnly("org.springframework.modulith:spring-modulith-events-amqp")

	// OpenAPI / Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Desenvolvimento
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
//	testImplementation("org.springframework.batch:spring-batch-test")
//	testImplementation("org.springframework.modulith:spring-modulith-starter-test")
//	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
//	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


dependencyManagement {
	imports {
		mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
	inputs.dir(project.extra["snippetsDir"]!!)
	dependsOn(tasks.test)
}

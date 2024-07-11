import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import io.mateo.cxf.codegen.wsdl2java.Wsdl2Java

plugins {
    java
    application
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.mateo.cxf-codegen") version "1.2.1"
}

group = "demo.hw"
version = "1.0.0"

val junitJupiterVersion = "5.10.2"

repositories {
    mavenCentral()
}


cxfCodegen {
    cxfVersion.set("4.0.4")
}

tasks.withType(Wsdl2Java::class).configureEach {
    jvmArgs = listOf("--add-opens=java.base/java.lang=ALL-UNNAMED")
}



tasks.register("compileWSDL", Wsdl2Java::class) {
    toolOptions {
        wsdl.set(file("src/main/java/demo/hw/wsdl/hello_world_async.wsdl"))
        outputDir.set(file("src/main/java"))
        markGenerated.set(true)
        packageNames.set(listOf("demo.hw.ws"))
        asyncMethods.set(listOf("greetMeSometime"))
        bindingFiles.add("src/main/java/demo/hw/wsdl/async_binding.xml")
        //extraArgs.add("-soap12")
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:deprecation")
}


dependencies {
    implementation("com.sun.xml.bind:jaxb-ri:4.0.5")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    implementation("org.apache.cxf:cxf-rt-features-logging:4.0.4")
    implementation("org.apache.cxf:cxf-rt-transports-http-netty-client:4.0.4")
    implementation("org.apache.cxf:cxf-rt-transports-http-hc:4.0.4")
    implementation("org.apache.cxf:cxf-rt-transports-http-jetty:4.0.4")
    implementation("org.apache.cxf:cxf-rt-bindings-soap:4.0.4")
    implementation("org.apache.cxf:cxf-rt-databinding-jaxb:4.0.4")
    implementation("org.apache.cxf:cxf-rt-frontend-jaxws:4.0.4")
    implementation("javax.xml.ws:jaxws-api:2.3.1")
    testImplementation("junit:junit:4.13.1")
    cxfCodegen("ch.qos.logback:logback-classic:1.5.6")
    cxfCodegen ("jakarta.xml.ws:jakarta.xml.ws-api:4.0.2")
    cxfCodegen("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    testImplementation("io.vertx:vertx-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()

}



tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
    manifest {
    }

    mergeServiceFiles("META-INF/cxf")

    mergeServiceFiles()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}


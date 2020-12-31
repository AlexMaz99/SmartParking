plugins {
    kotlin("jvm") version "1.4.21"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "pl.edu.agh.smartparking"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

application {
    mainClass.set("pl.edu.agh.smartparking.SmartParkingApplication")
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.graphics")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.4")
}

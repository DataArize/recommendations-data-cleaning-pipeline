plugins {
    id("java")
}

group = "org.dataarize"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.beam:beam-sdks-java-io-google-cloud-platform:2.60.0")
    implementation("org.apache.beam:beam-runners-google-cloud-dataflow-java:2.60.0")
    implementation("org.apache.beam:beam-sdks-java-core:2.60.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
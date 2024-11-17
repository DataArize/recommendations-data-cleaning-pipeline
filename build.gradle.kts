plugins {
    id("java")
}

group = "org.dataarize"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.1.0-alpha1")
    implementation("org.slf4j:slf4j-log4j12:2.1.0-alpha1")
    implementation("org.apache.beam:beam-sdks-java-io-google-cloud-platform:2.60.0")
    implementation("org.apache.beam:beam-runners-google-cloud-dataflow-java:2.60.0")
    implementation("org.apache.beam:beam-sdks-java-core:2.60.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform()
}


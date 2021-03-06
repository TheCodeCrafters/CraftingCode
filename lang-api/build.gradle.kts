plugins {
    `java-library`
}

group = rootProject.group
version = "1.0.0"  // lang API has its own version, so we don't need to bump it every mod release

repositories {
    mavenCentral()
}

dependencies {
    compileOnlyApi("org.jetbrains:annotations:22.0.0")
}
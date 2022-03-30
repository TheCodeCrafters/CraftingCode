plugins {
    `java-library`
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    maven(url = "http://artifactory.terasology.org/artifactory/libs-snapshot-local/") {
        isAllowInsecureProtocol = true
    }
}

dependencies {
    api(project(":lang-api"))
    // TODO: host elsewhere
    implementation("org.terasology.jnlua:JNLua:0.1.0-SNAPSHOT")
    implementation("org.terasology.jnlua:jnlua_natives:0.1.0-SNAPSHOT")
}

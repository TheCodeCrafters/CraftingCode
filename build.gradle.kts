@file:Suppress("UnstableApiUsage", "PropertyName")

plugins {
	id( "com.github.johnrengelman.shadow" ) version "7.1.2"
	id( "org.quiltmc.loom" ) version "0.12.+"
	`maven-publish`
}

val minecraft_version = "1.18.2" // by project
val mappings = "7" // by project
val shade = configurations.create("shade")

repositories {
	mavenCentral()
	maven( url="https://maven.blamejared.com" )
	maven( url="https://maven.terraformersmc.com/releases/" )
}

dependencies {
	minecraft( "com.mojang:minecraft:$minecraft_version" )
	mappings( loom.layered {
		addLayer( quiltMappings.mappings("org.quiltmc:quilt-mappings:$minecraft_version+build.$mappings:v2") )
	})
	modImplementation( libs.bundles.modImplementation )
	include( libs.bundles.include )
	shade( libs.bundles.implementation )

	shade( implementation( project(":lang-api") )!! )
	shade( implementation( project(":lang-dummy") )!! )
//	shade( compileOnly( project(":lang-lua") ) )  // uncomment to test lua

}

tasks.withType<ProcessResources>() {
	inputs.property( "version", project.version )

	filesMatching("fabric.mod.json") {
		expand( Pair( "version", project.version ) )
	}
}

allprojects {
	tasks.withType<JavaCompile>() {
		// Minecraft 1.18 (1.18-pre2) and upwards uses Java 17.
		options.encoding = "UTF-8"
		options.release.set(17)
//		options.compilerArgs = listOf( "--add-modules", "jdk.incubator.foreign" )
	}
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present. If you remove this line, sources will not be generated.
	withSourcesJar()
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
	configurations = listOf( shade )
	from( sourceSets.main.get().allSource )
	archiveClassifier.set( "" )
}

tasks.withType<Jar>() {
	from("LICENSE") {
		rename { "${it}_${archiveBaseName}"}
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from( components["java"] )
		}
	}

	repositories { }
}

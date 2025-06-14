plugins {
	id("java-library")
	id("maven-publish")
}

// Target Java version since Minecraft 1.20.6 (24w14a)
val javaVersion = 21

repositories {
	maven("https://maven.parchmentmc.org")
}

java {
	withSourcesJar()
	// TODO - Resurrect the version getter method

	toolchain {
		languageVersion = JavaLanguageVersion.of(javaVersion)
	}
}

project.version = System.getenv("TAG") ?: "0.0.0-development"
project.group = "page.langeweile.pause_music_on_pause"

tasks.withType<JavaCompile>().configureEach {
	options.release = javaVersion
}

tasks.named<Jar>("jar").configure {
	val name = project.name
	inputs.files("LICENSE.md")
	inputs.property("name", name)

	from("LICENSE.md") {
		rename { "LICENSE_${name}.md" }
	}
}

publishing {
	publications {
		register<MavenPublication>("mavenJava_${project.name}") {
			from(components["java"])
		}
	}

	repositories {
		val uploadUrl = System.getenv("MAVEN_UPLOAD_URL")
		if (uploadUrl != null) {
			maven {
				url = uri(uploadUrl)
				credentials {
					username = System.getenv("MAVEN_UPLOAD_USERNAME")
					password = System.getenv("MAVEN_UPLOAD_PASSWORD")
				}
			}
		}
	}
}

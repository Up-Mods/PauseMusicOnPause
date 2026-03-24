plugins {
	id("java_conventions")
	alias(libs.plugins.fabric.loom)
	alias(libs.plugins.mod.publish.plugin)
	`maven-publish`
}

project.version = System.getenv("TAG") ?: "0.0.0-development"
project.group = "page.langeweile"

base.archivesName = "pause_music_on_pause"

dependencies {
	minecraft(libs.minecraft)
	compileOnly(libs.fabric.loader)
}

loom {
	mods {
		register("pause_music_on_pause") {
			sourceSet("main")
		}
	}

	accessWidenerPath = file("src/main/resources/pause_music_on_pause.classtweaker")
}

java {
	withSourcesJar()
	// TODO - Resurrect the version getter method
}

tasks.processResources {
	filteringCharset = "UTF-8"

	val version = project.version
	inputs.property("version", version)

	filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml")) {
		expand("version" to version)
	}
}

tasks.named<Jar>("jar").configure {
	val name = project.name
	inputs.files("LICENSE.md")
	inputs.property("name", name)

	from("LICENSE.md") {
		rename { "LICENSE_${name}.md" }
	}
}

publishMods {
	file = tasks.named<Jar>("jar").get().archiveFile
	modLoaders = listOf("fabric", "neoforge")
	changelog = "To Be Updated"
	type = STABLE

	github {
		accessToken = providers.environmentVariable("GITHUB_TOKEN")
		repository = "Up-Mods/PauseMusicOnPause"
		commitish = "main"
	}
}

publishing {
	publications {
		register<MavenPublication>("mavenJava") {
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

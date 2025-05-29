plugins {
	id("mod_conventions_loader")
	alias(libs.plugins.fabric.loom)
}

base.archivesName = "pause_music_on_pause-fabric"

repositories {}

dependencies {
	minecraft(libs.minecraft)

	mappings(loom.layered {
		officialMojangMappings()
		parchment(libs.parchment)
	})
	modImplementation(libs.fabric.loader)

	modImplementation(libs.fabric.api)
}

loom {
	mods {
		register("pause_music_on_pause") {
			sourceSet("main")
		}
	}

	mixin {
		useLegacyMixinAp = false
	}

	runs {
		named("client") {
			client()
			ideConfigGenerated(true)
			runDir("run")
		}
	}

	accessWidenerPath = project(":mod_common").file("src/main/resources/pause_music_on_pause.accesswidener")
}

tasks.named<ProcessResources>("processResources").configure {
	val version = project.version
	inputs.property("version", version)

	filesMatching("fabric.mod.json") {
		expand("version" to version)
	}
}

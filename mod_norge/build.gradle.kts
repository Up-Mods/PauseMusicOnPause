plugins {
	id("mod_conventions_loader")
	alias(libs.plugins.moddevgradle)
}

base.archivesName = "pause_music_on_pause-neo"

repositories {
	maven {
		setUrl("https://prmaven.neoforged.net/NeoForge/pr2297")
		content {
			includeModule("net.neoforged", "neoforge")
		}
	}
}

neoForge {
	version = libs.versions.neoforge.get()

	runs {
		register("client") {
			client()
		}
	}

	mods {
		register("pause_music_on_pause") {
			sourceSet(sourceSets.main.get())
		}
	}
}

tasks.named<ProcessResources>("processResources").configure {
	val version = project.version
	inputs.property("version", version)

	filesMatching("META-INF/neoforge.mods.toml") {
		expand("version" to version)
	}

	exclude("pause_music_on_pause.accesswidener")
}

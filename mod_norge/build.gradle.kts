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

	parchment {
		//minecraftVersion = libs.versions.minecraft
		minecraftVersion = "1.21.5"
		mappingsVersion = libs.versions.parchment
	}

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

	accessTransformers {
		file("src/main/resources/boring_default_game_rules.accesswidener")
	}
}

tasks.named<ProcessResources>("processResources").configure {
	val version = project.version
	inputs.property("version", version)

	filesMatching("META-INF/neoforge.mods.toml") {
		expand("version" to version)
	}

	filesMatching("src/main/resources/boring_default_game_rules.accesswidener") {
		exclude()
	}
}

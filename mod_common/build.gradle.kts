plugins {
	id("mod_conventions_common")
	alias(libs.plugins.fabric.loom)
}

dependencies {
	minecraft(libs.minecraft)

	mappings(loom.layered {
		officialMojangMappings()
		parchment(libs.parchment)
	})
	modImplementation(libs.fabric.loader)
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

	fabricApi {
		configureDataGeneration {
			client = true
		}
	}

	accessWidenerPath = file("src/main/resources/pause_music_on_pause.accesswidener")
}

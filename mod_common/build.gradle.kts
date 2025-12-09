plugins {
	id("mod_conventions_common")
	alias(libs.plugins.fabric.loom)
}

dependencies {
	minecraft(libs.minecraft)

	mappings(loom.officialMojangMappings())
	modImplementation(libs.fabric.loader)
}

loom {
	mods {
		register("pause_music_on_pause") {
			sourceSet("main")
		}
	}

	accessWidenerPath = file("src/main/resources/pause_music_on_pause.accesswidener")
}

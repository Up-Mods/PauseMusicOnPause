rootProject.name = "pause_music_on_pause"

pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/")
		gradlePluginPortal()
	}

	includeBuild("build-logic")
}

include("mod", "test_norge")

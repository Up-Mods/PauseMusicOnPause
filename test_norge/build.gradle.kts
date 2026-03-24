plugins {
	id("java_conventions")
	alias(libs.plugins.moddevgradle)
}

dependencies {
	runtimeOnly(project(":mod"))
}

neoForge {
	version = libs.versions.neoforge.get()

	runs {
		register("Test") {
			client()
		}
	}
}

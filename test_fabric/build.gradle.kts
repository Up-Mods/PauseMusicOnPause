plugins {
	id("java_conventions")
	alias(libs.plugins.fabric.loom)
}

repositories {}

dependencies {
	minecraft(libs.minecraft)
	implementation(libs.fabric.loader)

	runtimeOnly(libs.fabric.api)
	runtimeOnly(project(":mod"))
}

loom {
	runs {
		named("client") {
			client()
			displayName = "Fabric Client"
			generateRunConfig = true
			runDirectory = projectDir.resolve("run")
		}
	}
}

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
			name("Test")
			client()
			ideConfigGenerated(true)
			runDir("run")
		}
	}
}

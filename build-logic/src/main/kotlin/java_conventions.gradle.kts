plugins {
	id("java-library")
}

// Target Java version since Minecraft 26.1 (26.1-snapshot-1)
val javaVersion = 25

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(javaVersion)
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release = javaVersion
}

tasks.named<Jar>("jar").configure {
	val name = project.name
	inputs.files("LICENSE.md")
	inputs.property("name", name)

	from("LICENSE.md") {
		rename { "LICENSE_${name}.md" }
	}
}

plugins {
	id("java-library")
}

// Target Java version since Minecraft 21.6 (21.6-snapshot-1)
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

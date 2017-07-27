package com.bonespirito.kotlinflyway

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder) : SpringApplicationBuilder {
		return application.sources(KotlinflywayApplication::class.java)
	}

}

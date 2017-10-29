package com.bonespirito.kotlinflyway.security.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


fun main(args: Array<String>) {
    val encoder = BCryptPasswordEncoder()
    System.out.println(encoder.encode("admin"))
}


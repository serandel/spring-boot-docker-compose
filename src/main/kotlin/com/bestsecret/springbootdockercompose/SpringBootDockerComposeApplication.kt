package com.bestsecret.springbootdockercompose

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootDockerComposeApplication

fun main(args: Array<String>) {
	runApplication<SpringBootDockerComposeApplication>(*args)
}

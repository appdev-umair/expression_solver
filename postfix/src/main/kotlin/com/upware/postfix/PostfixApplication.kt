package com.upware.postfix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PostfixApplication

fun main(args: Array<String>) {
	runApplication<PostfixApplication>(*args)
}

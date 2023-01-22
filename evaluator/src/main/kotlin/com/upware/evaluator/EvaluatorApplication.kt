package com.upware.evaluator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EvaluatorApplication

fun main(args: Array<String>) {
	runApplication<EvaluatorApplication>(*args)
}

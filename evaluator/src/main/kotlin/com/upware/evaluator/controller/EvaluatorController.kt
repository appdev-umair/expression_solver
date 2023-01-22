package com.upware.evaluator.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/expression")
class EvaluatorController {

    @PostMapping("/evaluate")
    fun evaluate(@RequestBody expression: String): String? {
        val url = "http://localhost:8081/convert/postfix"
        val headers = HttpHeaders()
        headers.contentType = MediaType.TEXT_PLAIN
        val entity = HttpEntity(expression, headers)

        val restTemplate = RestTemplate()
        val response = restTemplate.postForEntity(url, entity, ArrayList::class.java)
        var stack = Stack<String>()
        var exprArray = ArrayList<String>()
        exprArray = response.body as ArrayList<String>
        exprArray.forEach() {
            if (it in "0".."9") {
                stack.add(it)
            } else {
                var top: String = stack.peek()
                stack.pop()
                var bot: String = stack.peek()
                stack.pop()
                if (it == "+") {
                    stack.add((bot.toDouble() + top.toDouble()).toString())
                } else if (it == "-") {
                    stack.add((bot.toDouble() - top.toDouble()).toString())
                } else if (it == "*") {
                    stack.add((bot.toDouble() * top.toDouble()).toString())
                } else {
                    stack.add((bot.toDouble() / top.toDouble()).toString())
                }
            }
        }
        return stack.peek()
    }

}
package com.upware.postfix.controller

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/convert")
class PostfixController {

    @PostMapping("/postfix")
    fun postfix(@RequestBody expression: String): ArrayList<String> {
        var postFix = ArrayList<String>()
        var stackOp = Stack<String>()
        var stackOp1 = Stack<String>()
        var bracket = false

        // +++++++ To Convert String to Array List ++++++++++++++
        var exprArray = ArrayList<String>()
        var number = ""
        var expr = expression
        expr += '.'
        expr?.forEach {
            if (it in '0'..'9') {
                number += it
            } else {
                exprArray.add(number)
                exprArray.add(it.toString())
                number = ""
            }
        }
        // -------------------------------------------------------
        exprArray.forEach() {

            var str = it
            if (str == "\u001F") {
                str = "/"
            }
            if (str == "(") {
                bracket = true
            }
            if (str == ")") {
                bracket = false
                for (i in 1..stackOp1.size) {
                    postFix.add(stackOp1.peek())
                    stackOp1.pop()
                }
            }
            if (str in "0".."9") {
                postFix.add(str)
            } else if (!bracket) {
                if (stackOp.isNotEmpty())
                    if (str == "+" || str == "-") {
                        if (stackOp.peek() == "+" || stackOp.peek() == "-") {
                            postFix.add(stackOp.peek())
                            stackOp.pop()
                        } else {
                            for (i in 1..stackOp.size) {
                                postFix.add(stackOp.peek())
                                stackOp.pop()
                            }
                        }
                    } else if (str == "*" || str == "/") {
                        if (stackOp.peek() == "*" || stackOp.peek() == "/") {
                            postFix.add(stackOp.peek())
                            stackOp.pop()
                        }
                    }
                if (str != "(" && str != ")" && str != "" && str != ".")
                    stackOp.add(str)
            } else {
                if (stackOp1.isNotEmpty())
                    if (str == "+" || str == "-") {
                        if (stackOp1.peek() == "+" || stackOp1.peek() == "-") {
                            postFix.add(stackOp1.peek())
                            stackOp1.pop()
                        } else {
                            for (i in 1..stackOp1.size) {
                                postFix.add(stackOp1.peek())
                                stackOp1.pop()
                            }
                        }
                    } else if (str == "*" || str == "/") {
                        if (stackOp1.peek() == "*" || stackOp1.peek() == "/") {
                            postFix.add(stackOp1.peek())
                            stackOp1.pop()
                        }
                    }
                if (str != "(" && str != ")" && str != "" && str != ".")
                    stackOp1.add(str)
            }
        }
        for (i in 1..stackOp.size) {
            postFix.add(stackOp.peek())
            stackOp.pop()
        }
        return postFix
    }
}
package com.example.poc

class RmcPoc {
    fun fun2() {
        println("fun1() called")
//        callMethod()
//        callMethodKt()
//        puneet()
        println("fun1() call end")
    }

    fun callMethod() {
        val method = this::class.java.getMethod("puneet")
        method.invoke(this)
    }

    fun callMethodKt() {
        val method = this::class.members.find { it.name == "puneet" }
        method?.call(this)
    }
}
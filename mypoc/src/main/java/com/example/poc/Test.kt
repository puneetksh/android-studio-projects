package com.example.myapplication

import com.example.poc.RmcPoc


fun main() {
    val instance = RmcPoc()
//    callMethod(instance)
//    callMethodKt(instance)
    instance.fun1()
}

fun callMethod(instance: RmcPoc) {
    val method = instance::class.java.getMethod("puneet")
    method.invoke(instance)
}

fun callMethodKt(instance: RmcPoc) {
    val method = instance::class.members.find { it.name == "puneet" }
    method?.call(instance)
}

fun RmcPoc.puneet() {
    println("puneet() called")
}

fun RmcPoc.fun1() {
    println(" extension fun1() called")

}

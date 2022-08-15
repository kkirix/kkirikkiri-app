package com.kkirrix.kkirikkiri

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
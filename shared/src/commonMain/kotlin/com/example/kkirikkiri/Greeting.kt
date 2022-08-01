package com.example.kkirikkiri

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
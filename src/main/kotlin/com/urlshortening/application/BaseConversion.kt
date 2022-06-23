package com.urlshortening.application

import org.springframework.stereotype.Service

@Service
class BaseConversion {

    private val allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private val allowedCharacters = allowedString.toCharArray()
    private val base = allowedCharacters.size

    fun encode(input: Long): String? {
        var input = input
        val encodedString = StringBuilder()
        if (input == 0L) {
            return allowedCharacters[0].toString()
        }
        while (input > 0) {
            encodedString.append(allowedCharacters[(input % base).toInt()])
            input /= base
        }
        return "http://rkb/"+encodedString.reverse().toString()
    }

    fun decode(input: String): Long {
        val id = input.replace("http://rkb/","")
        val characters = id.toCharArray()
        val length = characters.size
        var decoded = 0

        //counter is used to avoid reversing input string
        var counter = 1
        for (i in 0 until length) {
            decoded += (allowedString.indexOf(characters[i]) * Math.pow(
                base.toDouble(),
                (length - counter).toDouble()
            )).toInt()
            counter++
        }
        return decoded.toLong()
    }
}
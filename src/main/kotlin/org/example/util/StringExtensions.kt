package org.example.util

import java.io.File

fun String.parseAllLinesFromFile(): List<String> {
    val result: MutableList<String> = mutableListOf()
    File(ClassLoader.getSystemResource(this).file).forEachLine { line ->
        result.add(line)
    }
    return result
}
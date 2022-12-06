package org.example.util

import java.io.File

fun File.parseAllLinesFromFile(): List<String> {
    val result: MutableList<String> = mutableListOf()
    forEachLine { line -> result.add(line) }
    return result
}
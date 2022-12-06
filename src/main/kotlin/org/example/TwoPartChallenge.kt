package org.example

import java.io.File

interface TwoPartChallenge<T> {

    fun part1(file: File): T

    fun part2(file: File): T

}
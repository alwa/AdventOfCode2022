package org.example

interface TwoPartChallenge<T> {

    fun part1(filename: String): T

    fun part2(filename: String): T

}
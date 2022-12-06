package org.example

interface Challenge<T> {

    fun part1(filename: String): T

    fun part2(filename: String): T

}
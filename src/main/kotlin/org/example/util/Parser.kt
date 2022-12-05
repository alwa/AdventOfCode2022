package org.example.util

interface Parser<T, V> {

    fun parse(input: T): V

}
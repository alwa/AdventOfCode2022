package org.example

interface Parser<T, V> {

    fun parse(input: T): V

}
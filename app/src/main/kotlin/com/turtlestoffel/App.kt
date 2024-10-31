package com.turtlestoffel

import java.io.File
import java.nio.file.Paths

fun printWorkingDirectory() {
    println(Paths.get("").toAbsolutePath().toString())
}

fun main() {
    printWorkingDirectory()

    File("repositories")
        .list()!!
        .filter {
            // Filter hidden folders
            !it.startsWith(".")
        }.map {
            Repository("repositories/$it")
        }.forEach {
            it.printFileCount()
            it.printStatistics()
            FileSizeHistogram(it.sourceFiles).calculate()
        }
}

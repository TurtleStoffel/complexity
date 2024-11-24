package com.turtlestoffel

import java.io.File
import java.nio.file.Paths

fun printWorkingDirectory() {
    println(Paths.get("").toAbsolutePath().toString())
}

fun main() {
    printWorkingDirectory()

    val repositoryConfiguration = RepositoryManager.loadConfig()

    RepositoryManager.validate(repositoryConfiguration)

    File("repositories")
        .list()!!
        .filter {
            // Filter hidden folders
            !it.startsWith(".")
        }.map {
            Repository("repositories/$it")
        }.forEach {
            it.printStatistics()
            it.printFileCount()
            // FileSizeHistogram(it.sourceFiles).calculate()
        }
}

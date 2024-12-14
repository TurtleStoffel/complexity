package com.turtlestoffel

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.nio.file.Paths

fun printWorkingDirectory() {
    println(Paths.get("").toAbsolutePath().toString())
}

fun writeMetricsToFile(metrics: List<SizeMetric>) {
    val content = Json.encodeToJsonElement(metrics).toString()

    println("Writing metrics to file $content")

    writeToFile("metrics.json", content)
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
            val metrics = FileSizeMetric(it.sourceFiles).calculate()
            writeMetricsToFile(metrics)
            //FileSizeMetric(it.sourceFiles).calculateHistogram()
        }
}

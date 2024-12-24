package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.nio.file.Paths

fun printWorkingDirectory() {
    println(Paths.get("").toAbsolutePath().toString())
}

/**
 * Hardcoded path of the Frontend public folder
 */
const val DATA_OUTPUT_DIR = "/coding/personal/graph-visualisation/public"

fun writeMetricsToFile(metrics: List<SizeMetric>) {
    val content = Json.encodeToJsonElement(metrics).toString()

    // Java doesn't recognize '~' as the home directory
    val home = System.getProperty("user.home")
    val metricsFilePath = "$home/$DATA_OUTPUT_DIR/metrics.json"

    println("Writing metrics to file $content")

    writeToFile(metricsFilePath, content)
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
            val metrics = FileSizeMetric(it.repositoryFiles.filterIsInstance<CodeFile>()).calculate()
            writeMetricsToFile(metrics)
        }
}

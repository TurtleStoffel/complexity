package com.turtlestoffel

import com.turtlestoffel.metrics.fileChangeFrequency
import com.turtlestoffel.metrics.printFileCount
import com.turtlestoffel.repository.Repository
import com.turtlestoffel.repository.RepositoryManager
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.Path

fun printWorkingDirectory() {
    println(Paths.get("").toAbsolutePath().toString())
}

/**
 * Hardcoded path of the Frontend public folder
 */
const val DATA_OUTPUT_DIR = "/coding/personal/graph-visualisation/public"

private fun writeExportToFile(export: Export) {
    val content = Json.encodeToJsonElement(export).toString()

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
            val path = Path("repositories/$it").toAbsolutePath()
            Repository(path)
        }.forEach {
            it.printStatistics()
            printFileCount(it)
            fileChangeFrequency(it.path)
            val metrics = calculateFileMetrics(it.codeFiles)
            val export = Export(it.name, metrics)
            writeExportToFile(export)
        }
}

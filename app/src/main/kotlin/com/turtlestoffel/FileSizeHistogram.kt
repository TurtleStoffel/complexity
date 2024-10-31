package com.turtlestoffel

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class TestData(
    val values: List<Int>,
)

class FileSizeHistogram(
    private val sourceFiles: List<SourceFile>,
) {
    fun calculate() {
        val histogram = sourceFiles.map { it.getNumberOfLines() }.sorted()
        val data = TestData(histogram)

        writeToFile("data/histogram.json", Json.encodeToString(data))
    }
}

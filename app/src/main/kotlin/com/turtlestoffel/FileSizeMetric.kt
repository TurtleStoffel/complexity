package com.turtlestoffel

import kotlinx.serialization.Serializable

@Serializable
data class SizeMetric(
    val path: String,
    val size: Int,
)

class FileSizeMetric(
    private val codeFiles: List<CodeFile>,
) {
    fun calculate(): List<SizeMetric> {
        return codeFiles.map { SizeMetric(it.path, it.getNumberOfLines()) }
    }

    fun calculateHistogram() {
        val orderedFileSizes = codeFiles.map { it.getNumberOfLines() }

        val bins = listOf(Pair(0, 50), Pair(51, 100), Pair(101, 250), Pair(251, 500), Pair(501, Int.MAX_VALUE))
        val histogram =
            bins.map { bin ->
                orderedFileSizes.count { it in bin.first..bin.second }
            }

        histogram.zip(bins).forEach { (count, bin) ->
            println("[${bin.first}-${bin.second}]: $count")
        }
    }
}

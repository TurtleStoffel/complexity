package com.turtlestoffel

class FileSizeHistogram(
    private val sourceFiles: List<SourceFile>,
) {
    fun calculate() {
        val orderedFileSizes = sourceFiles.map { it.getNumberOfLines() }.sorted()

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

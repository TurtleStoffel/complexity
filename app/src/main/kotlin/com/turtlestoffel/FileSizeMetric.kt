package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import kotlinx.serialization.Serializable

@Serializable
data class Export(
    val repositoryName: String,
    val sizeMetrics: List<SizeMetric>
)

@Serializable
data class SizeMetric(
    val path: String,
    val filename: String,
    val extension: String,
    val size: Int,
)

class FileSizeMetric(
    private val codeFiles: List<CodeFile>,
) {
    fun calculate(): List<SizeMetric> {
        return codeFiles.map {
            SizeMetric(
                it.repositoryFile.path.toString(),
                it.repositoryFile.filename.toString(),
                it.repositoryFile.extension,
                it.getNumberOfLines()
            )
        }
    }
}

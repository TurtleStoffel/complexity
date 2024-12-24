package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import kotlinx.serialization.Serializable

@Serializable
data class SizeMetric(
    val path: String,
    val extension: String,
    val size: Int,
)

class FileSizeMetric(
    private val codeFiles: List<CodeFile>,
) {
    fun calculate(): List<SizeMetric> {
        return codeFiles.map { SizeMetric(it.path, it.extension, it.getNumberOfLines()) }
    }
}

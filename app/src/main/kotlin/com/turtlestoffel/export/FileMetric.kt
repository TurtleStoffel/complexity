package com.turtlestoffel.export

import com.turtlestoffel.files.CodeFile
import kotlinx.serialization.Serializable

@Serializable
data class Export(
    val repositoryName: String,
    val fileMetrics: List<FileMetric>,
)

@Serializable
data class FileMetric(
    val path: String,
    val filename: String,
    val extension: String,
    val size: Int,
    val numberOfImports: Int,
)

fun calculateFileMetrics(codeFiles: List<CodeFile>): List<FileMetric> =
    codeFiles.map {
        FileMetric(
            it.repositoryFile.relativePath.toString(),
            it.repositoryFile.filename.toString(),
            it.repositoryFile.extension,
            it.newLines,
            it.totalImportStatements,
        )
    }

package com.turtlestoffel.files

import java.io.File
import java.nio.file.Path
import kotlin.io.path.extension

/**
 * Represents any type of file in a [com.turtlestoffel.Repository]
 */
data class RepositoryFile(
    val relativePath: Path,
    val absolutePath: Path,
) {
    val extension = relativePath.extension
    val filename: Path = relativePath.fileName

    val content by lazy {
        File(absolutePath.toUri()).readText()
    }
}

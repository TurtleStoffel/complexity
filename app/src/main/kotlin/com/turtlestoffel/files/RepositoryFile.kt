package com.turtlestoffel.files

import java.nio.file.Path
import kotlin.io.path.extension

/**
 * Represents any type of file in a [com.turtlestoffel.Repository]
 */
data class RepositoryFile(
    val content: String,
    val relativePath: Path,
    private val absolutePath: Path,
) {
    val extension = relativePath.extension
    val filename: Path = relativePath.fileName
}

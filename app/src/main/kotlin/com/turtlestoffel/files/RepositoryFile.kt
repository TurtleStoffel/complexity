package com.turtlestoffel.files

import java.nio.file.Path
import kotlin.io.path.extension

const val IMPORT_KEYWORD = "import"

/**
 * Represents any type of file in a [com.turtlestoffel.Repository]
 */
data class RepositoryFile(
    val content: String,
    val path: Path,
) {
    val extension = path.extension
    val filename = path.fileName
}

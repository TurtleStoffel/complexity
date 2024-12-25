package com.turtlestoffel.files

import java.io.File
import java.nio.file.Path
import kotlin.io.path.extension

const val IMPORT_KEYWORD = "import"

/**
 * Represents any type of file in a [com.turtlestoffel.Repository]
 */
open class RepositoryFile(
    val content: String,
    val path: Path
) {
    val extension = path.extension

    init {
        println("Read source file at path $path")
    }
}

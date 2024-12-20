package com.turtlestoffel

import java.io.File

const val IMPORT_KEYWORD = "import"

/**
 * Represents any type of file in a [Repository]
 */
open class RepositoryFile(
    file: File,
) {
    val extension = file.extension
    val path: String = file.path.removePrefix("repositories/")

    protected val content: String by lazy {
        file.readText()
    }

    init {
        println("Read source file at path ${file.path}")
    }
}

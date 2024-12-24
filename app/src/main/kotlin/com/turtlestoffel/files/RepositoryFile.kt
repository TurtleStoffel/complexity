package com.turtlestoffel.files

import java.io.File

const val IMPORT_KEYWORD = "import"

/**
 * Represents any type of file in a [com.turtlestoffel.Repository]
 */
open class RepositoryFile(
    file: File,
    val path: String
) {
    val extension = file.extension

    protected val content: String by lazy {
        file.readText()
    }

    init {
        println("Read source file at path ${file.path}")
    }
}

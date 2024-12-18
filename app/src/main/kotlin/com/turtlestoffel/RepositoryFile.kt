package com.turtlestoffel

import java.io.File

const val IMPORT_KEYWORD = "import"

/**
 * Represents any type of file in a [Repository]
 */
class RepositoryFile(
    file: File,
) {
    val extension = file.extension
    val path: String = file.path

    private val content: String = file.readText()
    private val newLines: Int by lazy {
        content.count { it == '\n' }
    }
    private val numberOfImportStatements: Int by lazy {
        content.windowed(IMPORT_KEYWORD.length).count { it == IMPORT_KEYWORD }
    }

    init {
        println("Read source file at path ${file.path}")
    }

    fun getNumberOfLines(): Int {
        println("Number of new lines in file: $newLines")
        return newLines
    }

    fun getNumberOfImports(): Int {
        println("Number of imports in file: $numberOfImportStatements")
        return numberOfImportStatements
    }
}

package com.turtlestoffel.files

import java.io.File

class CodeFile(file: File, path: String) : RepositoryFile(file, path) {

    private val newLines: Int by lazy {
        content.count { it == '\n' }
    }
    private val numberOfImportStatements: Int by lazy {
        content.windowed(IMPORT_KEYWORD.length).count { it == IMPORT_KEYWORD }
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

package com.turtlestoffel

import java.io.File

const val IMPORT_KEYWORD = "import"

class SourceFile(
    file: File,
) {
    private val content: String = file.readText()
    private val newLines: Int by lazy {
        content.count { it == '\n' }
    }
    private val imports: Int by lazy {
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
        println("Number of imports in file: $imports")
        return imports
    }
}

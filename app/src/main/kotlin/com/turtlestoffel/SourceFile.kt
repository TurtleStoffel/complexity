package com.turtlestoffel

import java.io.File

class SourceFile(
    file: File,
) {
    private val content: String = file.readText()
    private val newLines: Int by lazy {
        content.count { it == '\n' }
    }

    init {
        println("Read source file at path ${file.path} with content $content")
    }

    fun getNumberOfLines(): Int {
        println("Number of new lines in file: $newLines")
        return newLines
    }
}

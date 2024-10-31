package com.turtlestoffel

import java.io.File

class SourceFile(
    file: File,
) {
    private val content: String = file.readText()

    init {
        println("Read source file at path ${file.path} with content $content")
    }
}

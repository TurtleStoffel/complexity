package com.turtlestoffel

import java.io.File

class Repository(
    path: String,
) {
    private val files: List<File> =
        File(path)
            .walk()
            .onEnter {
                // Ignore hidden directories such as .git
                !it.name.startsWith(".")
            }.toList()
    val sourceFiles: List<SourceFile> =
        files
            .filter {
                // Ignore directories
                it.isFile
            }.map {
                println("Processing file: ${it.path}")
                SourceFile(it)
            }

    init {
        println("Repository detected: $path")
    }

    fun printFileCount() {
        val fileCount = files.size
        val typeScriptFileCount = files.filter { it.extension == "ts" }.size
        println("Number of files in repository: $fileCount")
        println("Number of TypeScript files in repository: $typeScriptFileCount")
    }

    fun printStatistics() {
        sourceFiles.forEach { it.getNumberOfLines() }
    }
}

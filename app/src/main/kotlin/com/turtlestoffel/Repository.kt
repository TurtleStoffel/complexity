package com.turtlestoffel

import java.io.File

class Repository(
    path: String,
) {
    // Only process a subset of files to prevent running out of memory in a big repository
    private val files: List<File> =
        File(path)
            .walk()
            .toList()
            .shuffled()
            .take(100)
    private val sourceFiles: List<SourceFile> =
        files
            .filter {
                // Ignore directories
                it.isFile
            }.map { SourceFile(it) }

    init {
        println("Repository detected: $path")
    }

    fun printFileCount() {
        val fileCount = files.size
        val typeScriptFileCount = files.filter { it.extension == "ts" }.size
        println("Number of files in repository: $fileCount")
        println("Number of TypeScript files in repository: $typeScriptFileCount")
    }
}

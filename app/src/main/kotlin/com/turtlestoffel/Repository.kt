package com.turtlestoffel

import java.io.File

class Repository(
    path: String,
) {
    val sourceFiles: List<SourceFile> =
        File(path)
            .walk()
            .onEnter {
                // Ignore hidden directories such as .git
                !it.name.startsWith(".")
            }.toList()
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
        val fileCount = sourceFiles.size
        val filesByExtension = sourceFiles.groupBy { it.extension }.mapValues { it.value.size }
        val typeScriptFileCount = filesByExtension["ts"] ?: 0
        println("Files by extension: $filesByExtension")
        println("Number of files in repository: $fileCount")
        println("Number of TypeScript files in repository: $typeScriptFileCount")
    }

    fun printStatistics() {
        sourceFiles.forEach {
            it.getNumberOfLines()
            it.getNumberOfImports()
        }
    }
}

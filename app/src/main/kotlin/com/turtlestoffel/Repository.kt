package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import com.turtlestoffel.files.RepositoryFile
import com.turtlestoffel.files.UnknownFile
import java.io.File

class Repository(
    path: String,
) {
    val repositoryFiles: List<RepositoryFile> =
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
                val filePath = it.path.removePrefix("repositories/")
                if (extensionMapper(it.extension) == FileType.CODE) {
                    CodeFile(it, filePath)
                } else {
                    UnknownFile(it, filePath)
                }
            }

    init {
        println("Repository detected: $path")
    }

    fun printFileCount() {
        val fileCount = repositoryFiles.size
        val filesByExtension = repositoryFiles.groupBy { it.extension }.mapValues { it.value.size }
        val typeScriptFileCount = filesByExtension["ts"] ?: 0
        println("Files by extension: $filesByExtension")
        println("Number of files in repository: $fileCount")
        println("Number of TypeScript files in repository: $typeScriptFileCount")
    }

    fun printStatistics() {
        repositoryFiles.forEach {
            when (it) {
                is CodeFile -> {
                    println("Code file detected: ${it.path}")
                    it.getNumberOfLines()
                    it.getNumberOfImports()
                }
                is UnknownFile -> {
                    println("Unknown file detected: ${it.path}")
                }
                else -> throw IllegalArgumentException("Unsupported file type")
            }
        }
    }
}

package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import com.turtlestoffel.files.RepositoryFile
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.relativeTo

class Repository(
    val path: Path,
) {
    // fileName is the name of the directory containing the repository
    val name = path.fileName.toString()

    val files: List<RepositoryFile> =
        File(path.toUri())
            .walk()
            .onEnter {
                // Ignore hidden directories such as .git
                !it.name.startsWith(".")
            }.toList()
            .filter {
                // Ignore directories
                it.isFile
            }.map {
                val filePath = Path(it.path).relativeTo(path)
                println("Processing file: ${it.path}")
                RepositoryFile(filePath, Path(it.path).toAbsolutePath())
            }

    val codeFiles =
        files
            .filter {
                extensionMapper(it.extension) == FileType.CODE
            }.map {
                CodeFile(it)
            }

    init {
        println("Repository detected: $path")
    }

    fun printStatistics() {
        files.forEach {
            if (extensionMapper(it.extension) == FileType.CODE) {
                println("Code file detected: ${it.relativePath}")
            } else {
                println("Unknown file detected: ${it.relativePath}")
            }
        }
    }
}

package com.turtlestoffel

import java.io.File

class Repository(
    path: String,
) {
    private val files: List<File> = File(path).walk().toList()

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

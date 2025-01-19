package com.turtlestoffel.metrics

import com.turtlestoffel.Repository

fun printFileCount(repository: Repository) {
    val fileCount = repository.files.size
    val filesByExtension = repository.files.groupBy { it.extension }.mapValues { it.value.size }
    val typeScriptFileCount = filesByExtension["ts"] ?: 0
    println("Files by extension: $filesByExtension")
    println("Number of files in repository: $fileCount")
    println("Number of TypeScript files in repository: $typeScriptFileCount")
}

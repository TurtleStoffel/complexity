package com.turtlestoffel

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun writeToFile(
    path: String,
    content: String,
) {
    // Create parent directories if they don't exist
    Path.of(path).parent?.let { Files.createDirectories(it) }

    File(path).printWriter().use { out ->
        out.print(content)
    }
}

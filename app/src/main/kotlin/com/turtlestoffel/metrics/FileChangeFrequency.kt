package com.turtlestoffel.metrics

import com.turtlestoffel.runCommand
import java.io.File
import java.nio.file.Path

fun fileChangeFrequency(path: Path) {
    val scriptResult = "git log -n 10 --pretty='' --name-only | sort | uniq -c | sort -n".runCommand(File(path.toUri()))
    println("Ran script with result: $scriptResult")
}

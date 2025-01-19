package com.turtlestoffel.util

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(workingDir: File): String? {
    try {
        // Invoking a shell is required for executing pipelines (e.g., 'ls | grep foo')
        val proc =
            ProcessBuilder("/bin/sh", "-c", this)
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        proc.errorStream
            .bufferedReader()
            .readText()
            .also { println(it) }
        return proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}

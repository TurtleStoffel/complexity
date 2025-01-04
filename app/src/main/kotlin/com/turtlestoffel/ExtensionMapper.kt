package com.turtlestoffel

enum class FileType {
    CODE,
    CONFIGURATION,
    UNKNOWN,
}

fun extensionMapper(extension: String): FileType =
    when (extension) {
        "ts" -> FileType.CODE
        else -> FileType.UNKNOWN
    }

package com.turtlestoffel.files

import java.io.File
import java.nio.file.Path

class UnknownFile(file: File, path: Path) : RepositoryFile(file, path) {
}

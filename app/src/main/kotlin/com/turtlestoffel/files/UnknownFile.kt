package com.turtlestoffel.files

import java.nio.file.Path

class UnknownFile(content: String, path: Path) : RepositoryFile(content, path) {
}

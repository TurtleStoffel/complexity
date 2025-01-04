package com.turtlestoffel.files

class CodeFile(val repositoryFile: RepositoryFile) {
    val newLines: Int by lazy {
        repositoryFile.content.count { it == '\n' }
    }
    val numberOfImportStatements: Int by lazy {
        repositoryFile.content.windowed(IMPORT_KEYWORD.length).count { it == IMPORT_KEYWORD }
    }
}

package com.turtlestoffel.files

class CodeFile(val repositoryFile: RepositoryFile) {

    private val newLines: Int by lazy {
        repositoryFile.content.count { it == '\n' }
    }
    private val numberOfImportStatements: Int by lazy {
        repositoryFile.content.windowed(IMPORT_KEYWORD.length).count { it == IMPORT_KEYWORD }
    }

    fun getNumberOfLines(): Int {
        println("Number of new lines in file: $newLines")
        return newLines
    }

    fun getNumberOfImports(): Int {
        println("Number of imports in file: $numberOfImportStatements")
        return numberOfImportStatements
    }
}

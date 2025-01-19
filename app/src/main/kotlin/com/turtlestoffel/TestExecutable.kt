package com.turtlestoffel

import com.turtlestoffel.files.CodeFile
import com.turtlestoffel.files.RepositoryFile
import kotlin.io.path.Path

fun main() {
    val rawPath = "repositories/vscode/src/vs/editor/common/cursorCommon.ts"
    val path = Path(rawPath)
    val repositoryFile = RepositoryFile(path, path.toAbsolutePath())

    val codeFile = CodeFile(repositoryFile)
    codeFile.importStatements.forEach {
        println(it)
    }

    println(codeFile.totalInternalImportStatements)
}

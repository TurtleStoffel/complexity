package com.turtlestoffel.files

import com.turtlestoffel.util.runCommand
import java.io.File

private const val IMPORT_KEYWORD = "import"

class CodeFile(
    val repositoryFile: RepositoryFile,
) {
    val newLines: Int by lazy {
        // + 1 needed to account for the last line that doesn't end with a newline character
        repositoryFile.content.count { it == '\n' } + 1
    }
    val totalImportStatements: Int by lazy {
        importStatements.size
    }
    val totalInternalImportStatements: Int by lazy {
        importStatements
            .filter {
                val regex = Regex(""".* from (.*)""")
                val (importFile) = regex.find(it)?.destructured!!
                println(importFile)

                importFile.startsWith("'./")
            }.size
    }
    val importStatements: List<String> by lazy {
        // parseImportsFromAST(repositoryFile.absolutePath.toString())
        val importRegex = Regex("""${IMPORT_KEYWORD}\s+.*""")
        val result = importRegex.findAll(repositoryFile.content)
        result.map { it.value }.toList()
    }
}

private fun parseImportsFromAST(path: String) {
    val home = System.getProperty("user.home")
    val typeScriptParserPath = "$home/coding/personal/complexity-typescript-parser/"
    val scriptResult = "deno run -A main.ts $path".runCommand(File(typeScriptParserPath))
    println("Ran script with result: $scriptResult")
}

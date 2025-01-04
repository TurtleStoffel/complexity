package com.turtlestoffel.files

private const val IMPORT_KEYWORD = "import"

class CodeFile(
    val repositoryFile: RepositoryFile,
) {
    val newLines: Int by lazy {
        // + 1 needed to account for the last line that doesn't end with a newline character
        repositoryFile.content.count { it == '\n' } + 1
    }
    val numberOfImportStatements: Int by lazy {
        val importRegex = Regex("""${IMPORT_KEYWORD}\s+.*""")
        val result = importRegex.findAll(repositoryFile.content)
        result.count()
    }
}

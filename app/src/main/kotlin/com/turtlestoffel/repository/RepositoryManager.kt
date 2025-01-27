package com.turtlestoffel.repository

import com.charleskorn.kaml.Yaml
import com.turtlestoffel.util.runCommand
import kotlinx.serialization.Serializable
import java.io.File

private const val REPOSITORIES_FOLDER = "repositories"
private const val REPOSITORIES_CONFIGURATION_RESOURCE = "/repositories.yaml"

@Serializable
data class RepositoryConfiguration(
    val repositories: List<String>,
)

class RepositoryManager {
    companion object {
        fun validate(config: RepositoryConfiguration) {
            // Check if configured repositories exist in repositories folder
            val repositories =
                File(REPOSITORIES_FOLDER)
                    .list()!!
                    .filter {
                        // Filter hidden folders
                        !it.startsWith(".")
                    }.toSet()

            val repositoryNameToUrl =
                config.repositories.associateBy {
                    it.split("/").last()
                }

            val configuredRepositories = repositoryNameToUrl.keys

            val unconfiguredRepositories = repositories.minus(configuredRepositories)
            require(unconfiguredRepositories.isEmpty()) {
                "Unrecognized repositories found in the repositories folder: $unconfiguredRepositories"
            }

            val unreplicatedRepositories = configuredRepositories.minus(repositories)
            if (unreplicatedRepositories.isNotEmpty()) {
                println("Found repositories that haven't been replicated yet")
                unreplicatedRepositories.forEach {
                    val result = "git clone ${repositoryNameToUrl[it]}".runCommand(File(REPOSITORIES_FOLDER))
                    println("Cloned repository $it with result $result")
                }
            }
        }

        fun loadConfig(): RepositoryConfiguration {
            val configFile = this::class.java.getResource(REPOSITORIES_CONFIGURATION_RESOURCE)

            requireNotNull(configFile) { "No configuration file exists for the Repository Manager" }

            val configuration = Yaml.default.decodeFromString(RepositoryConfiguration.serializer(), configFile.readText())

            println("Configuration $configuration")
            return configuration
        }
    }
}

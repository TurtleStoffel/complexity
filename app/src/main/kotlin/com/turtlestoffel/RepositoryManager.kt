package com.turtlestoffel

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable

private const val REPOSITORIES_CONFIGURATION_RESOURCE = "/repositories.yaml"

@Serializable
data class RepositoryConfiguration(
    val repositories: List<String>,
)

class RepositoryManager {
    companion object {
        fun loadConfig() {
            val configFile = this::class.java.getResource(REPOSITORIES_CONFIGURATION_RESOURCE)

            requireNotNull(configFile) { "No configuration file exists for the Repository Manager" }

            val configuration = Yaml.default.decodeFromString(RepositoryConfiguration.serializer(), configFile.readText())

            println("Configuration $configuration")
        }
    }
}

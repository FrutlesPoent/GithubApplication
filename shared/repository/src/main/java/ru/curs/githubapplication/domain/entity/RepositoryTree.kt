package ru.curs.githubapplication.domain.entity

data class RepositoryTree(
	val owner: String,
	val path: String = "",
	val repo: String,
	val branch: String = "",
) : java.io.Serializable

package ru.curs.githubapplication.repositorydetail.entity

enum class Type {
	FILE,
	FOLDER,
}

data class TypeOfFile(
	val url: String,
	val name: String,
	val type: Type,
)

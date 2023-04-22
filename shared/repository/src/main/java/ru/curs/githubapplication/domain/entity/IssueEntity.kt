package ru.curs.githubapplication.domain.entity

data class IssueEntity(
	val id: Int,
	val state: String,
	val title: String,
	val body: String?,
	val number: Int,
	val user: IssueUserEntity,
	val repository_url: String,
) : java.io.Serializable
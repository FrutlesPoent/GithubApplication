package ru.curs.githubapplication.domain.entity

data class IssueUserEntity(
	val login: String,
	val avatar_url: String,
) : java.io.Serializable
package ru.curs.githubapplication.domain.entity

data class IssuesEntity(
	val id: Int,
	val state: String,
	val title: String,
	val body: String,
)
package ru.curs.githubapplication.shared.issue.domain.entity

import ru.curs.githubapplication.domain.entity.IssueUserEntity

data class IssueComment(
	val id: Int,
	val body: String,
	val user: IssueUserEntity,
) : java.io.Serializable

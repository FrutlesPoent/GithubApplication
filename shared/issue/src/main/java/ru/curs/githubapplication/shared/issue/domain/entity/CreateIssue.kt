package ru.curs.githubapplication.shared.issue.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateIssue(
	@Json(name = "title")
	val title: String,
	@Json(name = "body")
	val body: String,
)

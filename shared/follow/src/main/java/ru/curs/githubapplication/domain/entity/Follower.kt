package ru.curs.githubapplication.domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Follower(
	val id: Long,
	val login: String,
	val avatar_url: String,
)

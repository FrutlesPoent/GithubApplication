package ru.curs.githubapplication.domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserProfile(
	val id: Long,
	val login: String,
	val name: String,
	val avatar_url: String,
	val followers: Int?,
	val following: Int?,
	val public_repos: Int,
	val owned_private_repos: Int,
)

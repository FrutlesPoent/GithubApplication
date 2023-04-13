package ru.curs.githubapplication.data.auth.domain.entity

data class Tokens(
	val accessToken: String,
	val refreshToken: String,
	val idToken: String,
)

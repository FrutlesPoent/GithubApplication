package ru.curs.githubapplication.data.auth.repository

object TokenStorage {

	var accessToken: String? = null
	var refreshToken: String? = null
	var idToken: String? = null
}
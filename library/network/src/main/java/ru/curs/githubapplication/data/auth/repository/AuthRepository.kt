package ru.curs.githubapplication.data.auth.repository

class AuthRepository {

	fun corruptAccessToken() {
		TokenStorage.accessToken = "fake token"
	}

	fun logout() {
		TokenStorage.accessToken = null
		TokenStorage.idToken = null
		TokenStorage.refreshToken = null
	}
}
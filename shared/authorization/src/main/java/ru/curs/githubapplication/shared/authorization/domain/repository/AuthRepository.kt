package ru.curs.githubapplication.shared.authorization.domain.repository

interface AuthRepository {

	suspend fun login(login: String, token: String)
}
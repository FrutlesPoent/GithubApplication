package ru.curs.githubapplication.shared.authorization.data.repository

import ru.curs.githubapplication.shared.authorization.domain.repository.AuthRepository

class AuthRepositoryImpl() : AuthRepository {

	override suspend fun login(login: String, token: String) {
		TODO("Not yet implemented")
	}
}
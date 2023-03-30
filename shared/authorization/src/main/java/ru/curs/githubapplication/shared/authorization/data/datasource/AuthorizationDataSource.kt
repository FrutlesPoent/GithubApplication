package ru.curs.githubapplication.shared.authorization.data.datasource

import ru.curs.githubapplication.shared.authorization.data.api.GithubAuthorizationApi

interface AuthorizationDataSource {

	fun get(login: String, token: String)
}

class AuthorizationDataSourceImp(
	private val api: GithubAuthorizationApi,
) : AuthorizationDataSource {

	override fun get(login: String, token: String) {
		api.get()
	}

}
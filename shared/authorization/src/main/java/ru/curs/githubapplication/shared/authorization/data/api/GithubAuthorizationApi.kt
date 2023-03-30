package ru.curs.githubapplication.shared.authorization.data.api

import retrofit2.http.GET


//curl -H "Authorization: Bearer OAUTH-TOKEN" https://api.github.com/user
interface GithubAuthorizationApi {
	@GET("/user")
	fun get()
}
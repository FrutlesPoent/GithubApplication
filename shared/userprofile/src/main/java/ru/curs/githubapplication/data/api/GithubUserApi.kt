package ru.curs.githubapplication.data.api

import retrofit2.http.GET
import ru.curs.githubapplication.domain.entity.UserProfile

interface GithubUserApi {

	@GET("user")
	suspend fun getUserProfile(): UserProfile
}
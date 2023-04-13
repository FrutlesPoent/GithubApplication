package ru.curs.githubapplication.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.curs.githubapplication.domain.entity.Follower

interface GithubFollowerApi {

	@GET("users/{username}/followers")
	suspend fun getFollowers(@Path("username") username: String): List<Follower>

	@GET("users/{username}/following")
	suspend fun getFollowing(@Path("username") username: String): List<Follower>
}
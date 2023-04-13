package ru.curs.githubapplication.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface GithubRepositoryApi {

	@GET("/user/repos")
	suspend fun getRepositoryList(): List<RepositoryEntity>

	@GET("/repos/{owner}/{repo}")
	suspend fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String): RepositoryEntity
}
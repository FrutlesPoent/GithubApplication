package ru.curs.githubapplication.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.curs.githubapplication.domain.entity.BranchEntity
import ru.curs.githubapplication.domain.entity.IssuesEntity
import ru.curs.githubapplication.domain.entity.RepositoryContent
import ru.curs.githubapplication.domain.entity.RepositoryEntity

interface GithubRepositoryApi {

	@GET("/user/repos")
	suspend fun getRepositoryList(): List<RepositoryEntity>

	@GET("/repos/{owner}/{repo}")
	suspend fun getRepository(@Path("owner") owner: String, @Path("repo") repo: String): List<RepositoryContent>

	@GET("/repos/{owner}/{repo}/contents/{path}")
	suspend fun getRepositoryContent(
		@Path("owner") owner: String,
		@Path("repo") repo: String,
		@Path("path") path: String,
	): List<RepositoryContent>

	@GET("/repos/{owner}/{repo}/branches")
	suspend fun getBranchesList(@Path("owner") owner: String, @Path("repo") repo: String): List<BranchEntity>

	@GET("/repos/{owner}/{repo}/issues")
	suspend fun getIssuesList(@Path("owner") owner: String, @Path("repo") repo: String): List<IssuesEntity>
}
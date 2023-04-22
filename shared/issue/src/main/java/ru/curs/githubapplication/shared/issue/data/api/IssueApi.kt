package ru.curs.githubapplication.shared.issue.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment
import ru.curs.githubapplication.shared.issue.domain.entity.TextBody

interface IssueApi {

	@GET("/repos/{owner}/{repo}/issues/comments")
	suspend fun getIssueComments(@Path("owner") owner: String, @Path("repo") repo: String): List<IssueComment>

	@GET("/repos/{owner}/{repo}/issues/{issue_number}/comments")
	suspend fun getIssueCommentsForIssue(@Path("owner") owner: String, @Path("repo") repo: String, @Path("issue_number") issueNumber: Int): List<IssueComment>

	@POST("/repos/{owner}/{repo}/issues")
	suspend fun createNewIssue(@Path("owner") owner: String, @Path("repo") repo: String, @Body issueBody: CreateIssue)

	@POST("/repos/{owner}/{repo}/issues/{issue_number}/comments")
	suspend fun addCommentToIssue(
		@Path("owner") owner: String,
		@Path("repo") repo: String,
		@Path("issue_number") issueNumber: Int,
		@Body issueComment: TextBody,
	)
}
package ru.curs.githubapplication.shared.issue.data.datasource

import ru.curs.githubapplication.shared.issue.data.api.IssueApi
import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment
import ru.curs.githubapplication.shared.issue.domain.entity.TextBody

class IssueDataSourceImpl(
	private val api: IssueApi,
) : IssueDataSource {

	override suspend fun getRepositoryIssuesComments(owner: String, repo: String): List<IssueComment> {
		return api.getIssueComments(owner, repo)
	}

	override suspend fun getIssueComments(owner: String, repo: String, issueNumber: Int): List<IssueComment> {
		return api.getIssueCommentsForIssue(owner, repo, issueNumber)
	}

	override suspend fun createNewIssue(owner: String, repo: String, issue: CreateIssue) {
		api.createNewIssue(owner, repo, issue)
	}

	override suspend fun addComment(owner: String, repo: String, issueNumber: Int, comment: String) {
		api.addCommentToIssue(owner, repo, issueNumber = issueNumber, TextBody(comment))
	}
}
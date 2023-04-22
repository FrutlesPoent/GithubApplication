package ru.curs.githubapplication.shared.issue.data.repository

import ru.curs.githubapplication.shared.issue.data.datasource.IssueDataSource
import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment
import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository

class IssueRepositoryImpl(
	private val dataSource: IssueDataSource,
) : IssueRepository {

	override suspend fun getRepositoryIssueComments(owner: String, repo: String): List<IssueComment> {
		return dataSource.getRepositoryIssuesComments(owner, repo)
	}

	override suspend fun getIssueComment(owner: String, repo: String, issueNumber: Int): List<IssueComment> {
		return dataSource.getIssueComments(owner, repo, issueNumber)
	}

	override suspend fun createNewIssue(owner: String, repo: String, issue: CreateIssue) {
		dataSource.createNewIssue(owner, repo, issue)
	}

	override suspend fun addNewComment(owner: String, repo: String, issueNumber: Int, body: String) {
		dataSource.addComment(owner, repo, issueNumber, body)
	}

}
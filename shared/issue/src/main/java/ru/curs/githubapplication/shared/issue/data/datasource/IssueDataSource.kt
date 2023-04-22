package ru.curs.githubapplication.shared.issue.data.datasource

import ru.curs.githubapplication.shared.issue.domain.entity.CreateIssue
import ru.curs.githubapplication.shared.issue.domain.entity.IssueComment

interface IssueDataSource {

	suspend fun getRepositoryIssuesComments(owner: String, repo: String): List<IssueComment>

	suspend fun getIssueComments(owner: String, repo: String, issueNumber: Int): List<IssueComment>

	suspend fun createNewIssue(owner: String, repo: String, issue: CreateIssue)

	suspend fun addComment(owner: String, repo: String, issueNumber: Int, comment: String)
}
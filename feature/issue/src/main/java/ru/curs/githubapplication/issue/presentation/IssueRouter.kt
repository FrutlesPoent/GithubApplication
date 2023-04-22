package ru.curs.githubapplication.issue.presentation

import ru.curs.githubapplication.domain.entity.IssueEntity

interface IssueRouter {

	fun openIssueDetail(issueEntity: IssueEntity)

	fun back()
}
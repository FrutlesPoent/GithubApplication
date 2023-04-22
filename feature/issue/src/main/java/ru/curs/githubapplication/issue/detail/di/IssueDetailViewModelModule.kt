package ru.curs.githubapplication.issue.detail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.issue.detail.presentation.IssueDetailViewModel

val issueDetailViewModelModule = module {
	viewModel { (issue: IssueEntity) ->
		IssueDetailViewModel(
			issue = issue,
			getIssueCommentsUseCase = get(),
			addCommentUseCase = get(),
			router = get(),
		)
	}
}
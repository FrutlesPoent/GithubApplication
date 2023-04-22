package ru.curs.githubapplication.issue.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.issue.presentation.IssueViewModel

val issueViewModelModule = module {
	viewModel { (repositoryTree: RepositoryTree) ->
		IssueViewModel(
			repositoryTree = repositoryTree,
			getRepositoryIssuesUseCase = get(),
			createNewIssueUseCase = get(),
			router = get(),
		)
	}
}
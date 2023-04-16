package ru.curs.githubapplication.repository.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.repository.presentation.RepositoryViewModel

val repositoryViewModel = module {
	viewModel { (repository: RepositoryTree) ->
		RepositoryViewModel(
			router = get(),
			repository = repository,
			getRepositoryContentUseCase = get(),
			getBranchesUseCase = get(),
		)
	}
}
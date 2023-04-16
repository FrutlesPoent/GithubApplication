package ru.curs.githubapplication.repositorydetail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.repositorydetail.presentation.RepositoryDetailViewModel

val repositoryDetailViewModelModule = module {
	viewModel { (repository: RepositoryTree) ->
		RepositoryDetailViewModel(
			getRepositoryContentUseCase = get(),
			repository = repository,
			parseFileNameUseCase = get(),
			parseFolderNameUseCase = get(),
			router = get(),
		)
	}
}
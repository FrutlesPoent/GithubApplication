package ru.curs.githubapplication.repositorydetail.presentation

import ru.curs.githubapplication.repositorydetail.entity.TypeOfFile

sealed class RepositoryDetailState {

	object Initial : RepositoryDetailState()

	object Loading : RepositoryDetailState()

	data class Content(
		val itemList: List<TypeOfFile>,
	) : RepositoryDetailState()
}

package ru.curs.githubapplication.fileview.presentation

import ru.curs.githubapplication.entity.Line

sealed class FileViewState {

	object Initial : FileViewState()

	data class Content(
		val lines: List<Line>,
	) : FileViewState()
}

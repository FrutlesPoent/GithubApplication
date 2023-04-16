package ru.curs.githubapplication.fileview.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.curs.githubapplication.component.ui.mvvm.BaseViewModel
import ru.curs.githubapplication.parser.ParseRawData
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer

class FileViewViewModel(
	private val rawDataFile: FileUrlTransfer,
	private val parseRawData: ParseRawData,
	private val router: FileViewRouter,
) : BaseViewModel() {

	var state by mutableStateOf<FileViewState>(FileViewState.Initial)
		private set

	fun init() {
		launch {
			val lines = parseRawData(rawDataFile.content)
			state = FileViewState.Content(lines)
		}
	}

	fun back() {
		router.back()
	}
}
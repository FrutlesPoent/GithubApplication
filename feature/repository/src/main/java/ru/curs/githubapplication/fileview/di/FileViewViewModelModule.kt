package ru.curs.githubapplication.fileview.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.fileview.presentation.FileViewViewModel
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer

val fileViewViewModelModule = module {
	viewModel { (rawDataFile: FileUrlTransfer) ->
		FileViewViewModel(
			rawDataFile = rawDataFile,
			parseRawData = get(),
			router = get(),
		)
	}
}
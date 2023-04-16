package ru.curs.githubapplication.di

import org.koin.dsl.module
import ru.curs.githubapplication.parser.ParseFileNameUseCase
import ru.curs.githubapplication.parser.ParseFolderNameUseCase
import ru.curs.githubapplication.parser.ParseRawData

val rawDataParser = module {
	factory { ParseRawData() }
	factory { ParseFileNameUseCase() }
	factory { ParseFolderNameUseCase() }
}
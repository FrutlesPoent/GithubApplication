package ru.curs.githubapplication.feature.authorization.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationViewModel

val authorizationModule = module {
	viewModel {
		AuthorizationViewModel(
			context = androidContext(),
			getAuthRequestUseCase = get(),
			performTokenRequestUseCase = get(),
			router = get(),
		)
	}
}
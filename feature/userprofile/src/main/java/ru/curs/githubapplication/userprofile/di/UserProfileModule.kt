package ru.curs.githubapplication.userprofile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.userprofile.presentation.UserProfileViewModel

val userProfileViewModelModule = module {
	viewModel {
		UserProfileViewModel(
			getUserProfileUseCase = get(),
			router = get(),
		)
	}
}
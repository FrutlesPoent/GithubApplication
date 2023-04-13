package ru.curs.githubapplication.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.presentation.FollowViewModel

val followModule = module {
	viewModel {
		FollowViewModel(
			fetchFollowersScenario = get(),
			fetchFollowingScenario = get(),
			router = get(),
		)
	}
}
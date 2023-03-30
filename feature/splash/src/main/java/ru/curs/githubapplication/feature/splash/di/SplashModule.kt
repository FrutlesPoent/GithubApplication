package ru.curs.githubapplication.feature.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.curs.githubapplication.feature.splash.presentation.SplashViewModel

val splashModule = module {
	viewModel { SplashViewModel(get()) }
}
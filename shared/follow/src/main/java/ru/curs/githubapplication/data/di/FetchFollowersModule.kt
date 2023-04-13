package ru.curs.githubapplication.data.di

import org.koin.dsl.module
import ru.curs.githubapplication.data.api.GithubFollowerApi
import ru.curs.githubapplication.data.auth.retrofit.createRetrofitService
import ru.curs.githubapplication.data.auth.retrofit.getRetrofit
import ru.curs.githubapplication.data.datasource.FetchFollowUsersDataSource
import ru.curs.githubapplication.data.datasource.FetchFollowUsersDataSourceImpl
import ru.curs.githubapplication.data.repository.FetchFollowUsersRepositoryImpl
import ru.curs.githubapplication.domain.repository.FetchFollowUsersRepository
import ru.curs.githubapplication.domain.scenario.FetchFollowersScenario
import ru.curs.githubapplication.domain.scenario.FetchFollowingScenario
import ru.curs.githubapplication.domain.usecase.GetFollowersUseCase
import ru.curs.githubapplication.domain.usecase.GetFollowingUseCase

val fetchFollowersModule = module {
	single { createRetrofitService<GithubFollowerApi>(getRetrofit()) }
	single<FetchFollowUsersDataSource> { FetchFollowUsersDataSourceImpl(get()) }
	single<FetchFollowUsersRepository> { FetchFollowUsersRepositoryImpl(get()) }
	factory { GetFollowingUseCase(get()) }
	factory { GetFollowersUseCase(get()) }
	factory { FetchFollowersScenario(get(), get()) }
	factory { FetchFollowingScenario(get(), get()) }
}
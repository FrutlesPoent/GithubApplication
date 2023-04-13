package ru.curs.githubapplication.data.di

import org.koin.dsl.module
import ru.curs.githubapplication.data.api.GithubRepositoryApi
import ru.curs.githubapplication.data.auth.retrofit.createRetrofitService
import ru.curs.githubapplication.data.auth.retrofit.getRetrofit
import ru.curs.githubapplication.data.datasource.RepositoryDataSource
import ru.curs.githubapplication.data.datasource.RepositoryDataSourceImpl
import ru.curs.githubapplication.data.repository.RepositoryRepositoryImpl
import ru.curs.githubapplication.domain.repository.RepositoryRepository
import ru.curs.githubapplication.domain.usecase.GetRepositoryListUseCase
import ru.curs.githubapplication.domain.usecase.GetRepositoryReadmeUseCase

val repositoryModule = module {
	single { createRetrofitService<GithubRepositoryApi>(getRetrofit()) }
	single<RepositoryDataSource> { RepositoryDataSourceImpl(get()) }
	single<RepositoryRepository> { RepositoryRepositoryImpl(get()) }
	factory { GetRepositoryListUseCase(get()) }
	factory { GetRepositoryReadmeUseCase(get()) }
}
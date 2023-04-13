package ru.curs.githubapplication.data.di

import org.koin.dsl.module
import ru.curs.githubapplication.data.api.GithubUserApi
import ru.curs.githubapplication.data.auth.retrofit.createRetrofitService
import ru.curs.githubapplication.data.auth.retrofit.getRetrofit
import ru.curs.githubapplication.data.datasource.UserProfileDataSource
import ru.curs.githubapplication.data.datasource.UserProfileDataSourceImpl
import ru.curs.githubapplication.data.repository.UserProfileRepositoryImpl
import ru.curs.githubapplication.domain.repository.UserProfileRepository
import ru.curs.githubapplication.domain.usecase.GetUserProfileUseCase

val userProfileModule = module {
	single { createRetrofitService<GithubUserApi>(getRetrofit()) }
	single<UserProfileDataSource> { UserProfileDataSourceImpl(get()) }
	single<UserProfileRepository> { UserProfileRepositoryImpl(get()) }
	factory { GetUserProfileUseCase(get()) }
}

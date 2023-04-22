package ru.curs.githubapplication.shared.issue.data.di

import org.koin.dsl.module
import ru.curs.githubapplication.data.auth.retrofit.createRetrofitService
import ru.curs.githubapplication.data.auth.retrofit.getRetrofit
import ru.curs.githubapplication.shared.issue.data.api.IssueApi
import ru.curs.githubapplication.shared.issue.data.datasource.IssueDataSource
import ru.curs.githubapplication.shared.issue.data.datasource.IssueDataSourceImpl
import ru.curs.githubapplication.shared.issue.data.repository.IssueRepositoryImpl
import ru.curs.githubapplication.shared.issue.domain.repository.IssueRepository
import ru.curs.githubapplication.shared.issue.domain.usecase.AddCommentUseCase
import ru.curs.githubapplication.shared.issue.domain.usecase.CreateNewIssueUseCase
import ru.curs.githubapplication.shared.issue.domain.usecase.GetIssueCommentsUseCase
import ru.curs.githubapplication.shared.issue.domain.usecase.GetRepositoryIssueComments

val issueModule = module {
	single { createRetrofitService<IssueApi>(getRetrofit()) }
	single<IssueDataSource> { IssueDataSourceImpl(get()) }
	single<IssueRepository> { IssueRepositoryImpl(get()) }
	factory { GetRepositoryIssueComments(get()) }
	factory { CreateNewIssueUseCase(get()) }
	factory { AddCommentUseCase(get()) }
	factory { GetIssueCommentsUseCase(get()) }
}
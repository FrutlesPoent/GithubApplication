package ru.curs.githubapplication.di

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationRouter
import ru.curs.githubapplication.feature.splash.presentation.SplashRouter
import ru.curs.githubapplication.fileview.presentation.FileViewRouter
import ru.curs.githubapplication.navigation.router.AuthorizationRouterImpl
import ru.curs.githubapplication.navigation.router.FileViewRouterImpl
import ru.curs.githubapplication.navigation.router.FollowRouterImpl
import ru.curs.githubapplication.navigation.router.RepositoryDetailRouterImpl
import ru.curs.githubapplication.navigation.router.RepositoryRouterImpl
import ru.curs.githubapplication.navigation.router.SplashRouterImpl
import ru.curs.githubapplication.navigation.router.UserProfileRouterImpl
import ru.curs.githubapplication.presentation.FollowRouter
import ru.curs.githubapplication.repository.presentation.RepositoryRouter
import ru.curs.githubapplication.repositorydetail.presentation.RepositoryDetailRouter
import ru.curs.githubapplication.userprofile.presentation.UserProfileRouter

val routersModule = module {
	single<NavController> {
		NavHostController(androidContext()).apply {
			navigatorProvider.addNavigator(ComposeNavigator())
			navigatorProvider.addNavigator(DialogNavigator())
		}
	}
	factory<SplashRouter> { SplashRouterImpl(get()) }
	factory<AuthorizationRouter> { AuthorizationRouterImpl(get()) }
	factory<UserProfileRouter> { UserProfileRouterImpl(get()) }
	factory<FollowRouter> { FollowRouterImpl(get()) }
	factory<RepositoryRouter> { RepositoryRouterImpl(get()) }
	factory<RepositoryDetailRouter> { RepositoryDetailRouterImpl(get()) }
	factory<FileViewRouter> { FileViewRouterImpl(get()) }
}
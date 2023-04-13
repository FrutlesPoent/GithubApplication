package ru.curs.githubapplication.di

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationRouter
import ru.curs.githubapplication.feature.splash.presentation.SplashRouter
import ru.curs.githubapplication.navigation.router.AuthorizationRouterImpl
import ru.curs.githubapplication.navigation.router.FollowRouterImpl
import ru.curs.githubapplication.navigation.router.SplashRouterImpl
import ru.curs.githubapplication.navigation.router.UserProfileRouterImpl
import ru.curs.githubapplication.presentation.FollowRouter
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
}
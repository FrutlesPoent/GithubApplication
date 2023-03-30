package ru.curs.githubapplication.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.java.KoinJavaComponent.inject
import ru.curs.githubapplication.feature.authorization.ui.AuthorizationScreen
import ru.curs.githubapplication.feature.splash.ui.SplashScreen
import ru.curs.githubapplication.navigation.NavigationTree

@Composable
fun ApplicationScreen() {
	val navController by inject<NavHostController>(NavController::class.java)
	NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {
		composable(NavigationTree.Splash.name) { SplashScreen() }
		composable(NavigationTree.Authorization.name) { AuthorizationScreen() }

	}

}
package ru.curs.githubapplication.component.design.navbarcolor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.curs.githubapplication.component.design.resources.theme.Dark100
import ru.curs.githubapplication.component.design.resources.theme.Dark75

@Composable
fun NavbarColor() {
	val systemUiController = rememberSystemUiController()
	systemUiController.setNavigationBarColor(Dark100)
	systemUiController.navigationBarDarkContentEnabled = true
	systemUiController.setStatusBarColor(Dark75)
	SideEffect {
		systemUiController.setNavigationBarColor(
			color = Dark100,
		)
	}
}
package ru.curs.githubapplication.feature.splash.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.resources.theme.Dark100
import ru.curs.githubapplication.component.design.resources.theme.Dark50
import ru.curs.githubapplication.component.design.resources.theme.Dark75
import ru.curs.githubapplication.component.design.resources.theme.Light100
import ru.curs.githubapplication.component.design.resources.theme.Violet100
import ru.curs.githubapplication.feature.splash.presentation.SplashViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel = getViewModel()) {
	val systemUiController = rememberSystemUiController()
	systemUiController.setNavigationBarColor(Light100)
	systemUiController.navigationBarDarkContentEnabled = true
	systemUiController.setStatusBarColor(Dark75)

	AnimatedVisibility(
		visible = true,
		enter = fadeIn(),
		exit = fadeOut(),
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Dark50)
				.fillMaxSize(),
			contentAlignment = Alignment.Center,
		) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Icon(
					modifier = Modifier
						.size(320.dp)
						.padding(top = 100.dp),
					painter = painterResource(ru.curs.githubapplication.component.design.resources.R.drawable.github_mark),
					contentDescription = null,
					tint = Dark100,
				)
				Box(modifier = Modifier
					.fillMaxSize()
					.size(50.dp)) {
					CircularProgressIndicator(
						modifier = Modifier.align(Alignment.Center),
						color = Color.White,
					)
				}
			}
		}
	}
}
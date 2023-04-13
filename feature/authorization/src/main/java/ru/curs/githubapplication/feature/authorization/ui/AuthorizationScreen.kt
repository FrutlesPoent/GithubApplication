package ru.curs.githubapplication.feature.authorization.ui

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.loading.LoadingScreen
import ru.curs.githubapplication.component.design.resources.theme.Dark50
import ru.curs.githubapplication.component.design.resources.theme.Inter
import ru.curs.githubapplication.component.design.resources.theme.Light100
import ru.curs.githubapplication.feature.authorization.R
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationState
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationViewModel

@Composable
fun AuthorizationScreen(viewModel: AuthorizationViewModel = getViewModel()) {
	val getAuthResponse = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.StartActivityForResult()
	) {
		val dataIntent = it.data ?: return@rememberLauncherForActivityResult
		viewModel.handleResponseIntent(dataIntent)
	}

	val openAuthPage: Intent? by viewModel.openAuthPageFlow.collectAsState(initial = null)
	if (openAuthPage != null) {
		getAuthResponse.launch(openAuthPage)
	}

	val systemUiController = rememberSystemUiController()
	systemUiController.setNavigationBarColor(Light100)
	systemUiController.navigationBarDarkContentEnabled = true

	AnimatedVisibility(visible = viewModel.state == AuthorizationState.Content(isLoginPageOpen = true)) {
		LoadingScreen()
	}

	AnimatedVisibility(
		visible = viewModel.state == AuthorizationState.Content(isLoginPageOpen = false)
	) {
		LoginFields(
			onLoginClick = viewModel::openLoginPage,
			viewModel = viewModel,
		)
	}

}

@Preview
@Composable
fun LoginFields(
	onLoginClick: () -> Unit,
	viewModel: AuthorizationViewModel,
) {
	Box(
		modifier = Modifier
			.background(Dark50)
			.fillMaxSize()
	) {

		val buttonColor = ButtonDefaults.buttonColors(
			backgroundColor = Color.White,
			contentColor = Color.White,
		)

		Icon(
			modifier = Modifier
				.padding(vertical = 20.dp)
				.size(160.dp)
				.offset(129.dp),
			painter = painterResource(ru.curs.githubapplication.component.design.resources.R.drawable.github_mark),
			contentDescription = null,
			tint = Color.Black,
		)

		Column(
			modifier = Modifier
				.fillMaxHeight()
				.padding(horizontal = 30.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(
				text = stringResource(id = R.string.authorization_hint),
				color = Color.White,
				fontFamily = Inter,
				textAlign = TextAlign.Center,
			)

			Button(
				onClick = { onLoginClick() },
				colors = buttonColor,
				modifier = Modifier.fillMaxWidth(),
				enabled = viewModel.state != AuthorizationState.Content(isLoginPageOpen = true)
			) {
				Text(text = stringResource(id = R.string.authorization_button), color = Color.Black)
			}

		}
	}
}
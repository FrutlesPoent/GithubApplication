package ru.curs.githubapplication.feature.authorization.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel
import ru.curs.githubapplication.component.design.resources.theme.Dark50
import ru.curs.githubapplication.component.design.resources.theme.Green80
import ru.curs.githubapplication.component.design.resources.theme.Inter
import ru.curs.githubapplication.component.design.resources.theme.Light100
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationState
import ru.curs.githubapplication.feature.authorization.presentation.AuthorizationViewModel

@Composable
fun AuthorizationScreen(viewModel: AuthorizationViewModel = getViewModel()) {
	val systemUiController = rememberSystemUiController()
	systemUiController.setNavigationBarColor(Light100)
	systemUiController.navigationBarDarkContentEnabled = true


	AnimatedVisibility(
		visible = viewModel.state == AuthorizationState.Initial || viewModel.state == AuthorizationState.Loading,
		enter = fadeIn(),
		exit = fadeOut()
	) {
		LoadingScreen()
	}

	AnimatedVisibility(
		visible = viewModel.state == AuthorizationState.Content
	) {
		LoginFields(
			onLoginClick = viewModel::login
		)
	}

}

@Preview
@Composable
fun LoginFields(
	onLoginClick: (String, String) -> Unit,
) {
	val focusManager = LocalFocusManager.current
	var emailFieldState by remember {
		mutableStateOf("")
	}
	var passwordFieldState by remember {
		mutableStateOf("")
	}
	Box(
		modifier = Modifier
			.background(Dark50)
			.fillMaxSize()
	) {

		val outlineTextColor = TextFieldDefaults.outlinedTextFieldColors(
			textColor = Color.White,
			focusedLabelColor = Color.White,
			unfocusedBorderColor = Color.White,
			focusedBorderColor = Color.White,
			unfocusedLabelColor = Color.White,
			placeholderColor = Color.White
		)

		val buttonColor = ButtonDefaults.buttonColors(
			backgroundColor = Green80,
			contentColor = Green80,
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
			Text(text = "Введите данные от своего аккаунта GITHUB", color = Color.White, fontFamily = Inter)

			OutlinedTextField(
				value = emailFieldState,
				placeholder = { Text(text = "username") },
				label = { Text(text = "Username") },
				onValueChange = { emailFieldState = it },
				keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
				keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
				modifier = Modifier.padding(10.dp),
				colors = outlineTextColor,
			)

			OutlinedTextField(
				value = passwordFieldState,
				placeholder = { Text(text = "Token") },
				label = { Text(text = "Токен") },
				onValueChange = { passwordFieldState = it },
				visualTransformation = PasswordVisualTransformation(),
				keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
				keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
				modifier = Modifier.padding(10.dp),
				colors = outlineTextColor,
			)

			Button(
				onClick = { onLoginClick(emailFieldState, passwordFieldState) },
				colors = buttonColor,
				modifier = Modifier.fillMaxWidth()
			) {
				Text(text = "Авторизация", color = Color.White)
			}

		}
	}
}

@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoadingScreen() {
	Box(modifier = Modifier.fillMaxSize()) {
		CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
	}

	val keyboardController = LocalSoftwareKeyboardController.current
	keyboardController?.hide()
}
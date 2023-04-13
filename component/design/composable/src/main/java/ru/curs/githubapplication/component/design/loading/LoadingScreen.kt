package ru.curs.githubapplication.component.design.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview

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
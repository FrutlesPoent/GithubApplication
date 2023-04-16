package ru.curs.githubapplication.component.design.error

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(onErrorClick: () -> Unit) {
	androidx.compose.material.Surface(
		color = MaterialTheme.colors.primaryVariant,
		modifier = Modifier.fillMaxSize(),
	) {
		Column {
			Text(text = "Произошла ошибка, повторите позже")
			Button(onClick = { onErrorClick() }) {
				Text(text = "Повторить")
			}
		}
	}
}
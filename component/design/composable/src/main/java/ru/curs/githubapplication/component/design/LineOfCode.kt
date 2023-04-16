package ru.curs.githubapplication.component.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LineOfCode(position: Int, line: String) {
	Row(modifier = Modifier
		.fillMaxSize()
		.clickable { }) {
		Text(
			text = position.toString(),
			color = Color.White,
			modifier = Modifier.background(MaterialTheme.colors.onBackground)
		)
		Spacer(modifier = Modifier.padding(8.dp))
		Text(text = line, color = Color.Gray)
	}
}

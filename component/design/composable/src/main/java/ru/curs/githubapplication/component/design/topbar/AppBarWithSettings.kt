package ru.curs.githubapplication.component.design.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppBarWithSettings(text: String, onBackArrowClick: () -> Unit, onSettingsClick: () -> Unit) {
	Surface(color = MaterialTheme.colors.primary) {
		TopAppBar(modifier = Modifier.fillMaxWidth()) {
			Row(
				modifier = Modifier
					.padding(8.dp)
					.fillMaxWidth()
					.fillMaxHeight(),
				verticalAlignment = Alignment.CenterVertically,
			) {
				Icon(
					imageVector = Icons.Filled.ArrowBack,
					contentDescription = "back button",
					Modifier.clickable { onBackArrowClick() },
				)
				Icon(
					imageVector = Icons.Filled.Settings,
					contentDescription = "settings button",
					modifier = Modifier.clickable { onSettingsClick() },
				)
			}

		}

	}
}
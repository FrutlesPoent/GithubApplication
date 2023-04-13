package ru.curs.githubapplication.component.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Followers(
	followersCount: Int?,
	followingCount: Int?,
	followersButton: () -> Unit,
	followingButton: () -> Unit,
) {
	Row(modifier = Modifier.fillMaxWidth(1f)) {
		Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
			Text(text = "Следят: $followersCount",
				 style = MaterialTheme.typography.caption,
				 modifier = Modifier.clickable { followersButton() })
		}
		Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
			Text(text = "Слежу: $followingCount", style = MaterialTheme.typography.caption, modifier = Modifier.clickable { followingButton() })
		}
	}
}
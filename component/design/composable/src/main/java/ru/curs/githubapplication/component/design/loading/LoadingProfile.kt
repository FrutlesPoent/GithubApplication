package ru.curs.githubapplication.component.design.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProfile(
) {
	Surface(color = MaterialTheme.colors.primary) {
		Column(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth()
				.wrapContentHeight(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				contentScale = ContentScale.Crop,
				imageVector = Icons.Filled.Person,
				contentDescription = "Image of user",
				modifier = Modifier
					.clip(CircleShape)
					.width(150.dp)
					.height(150.dp)
					.padding(8.dp),
			)

			Spacer(modifier = Modifier.padding(8.dp))
//			Text(text = text, style = MaterialTheme.typography.h6)
			Spacer(modifier = Modifier.padding(top = 2.dp))
//			Text(text = text2, style = MaterialTheme.typography.caption)
			Spacer(modifier = Modifier.padding(8.dp))
		}
		LoadingScreen()
	}
}
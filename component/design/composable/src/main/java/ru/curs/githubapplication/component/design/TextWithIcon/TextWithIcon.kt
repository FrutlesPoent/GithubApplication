package ru.curs.githubapplication.component.design.TextWithIcon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.curs.githubapplication.component.design.resources.R

@Composable
fun TextWithIcon(
	text: String,
	count: Int,
	repositoryListClick: () -> Unit,
	painter: Painter,
) {
	Surface(
		color = MaterialTheme.colors.primary,
		modifier = Modifier
			.fillMaxWidth()
			.clickable { repositoryListClick() },
	) {
		Column(
			horizontalAlignment = Alignment.End
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(39.dp)
			) {
				Image(
					contentScale = ContentScale.Crop,
					painter = painter,
					contentDescription = "Repository",
					modifier = Modifier.size(30.dp)
				)
				Spacer(modifier = Modifier.padding(8.dp))
				Box(modifier = Modifier.fillMaxSize()) {
					Text(
						text = text,
						style = MaterialTheme.typography.h6,
						color = Color.White,
						textAlign = TextAlign.Left
					)
					Text(
						text = count.toString(),
						style = MaterialTheme.typography.h6,
						textAlign = TextAlign.Center,
						modifier = Modifier
							.fillMaxHeight()
							.align(Alignment.BottomEnd)
					)
				}
			}
		}

	}
}
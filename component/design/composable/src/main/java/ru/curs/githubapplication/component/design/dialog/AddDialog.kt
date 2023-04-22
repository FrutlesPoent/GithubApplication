package ru.curs.githubapplication.component.design.dialog

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String, String) -> Unit) {
	val titleFieldError = remember { mutableStateOf("") }
	val textFieldError = remember { mutableStateOf("") }
	val titleField = remember { mutableStateOf(value) }
	val textField = remember { mutableStateOf(value) }

	Dialog(onDismissRequest = { setShowDialog(false) }) {
		Surface(
			shape = RoundedCornerShape(16.dp),
			color = MaterialTheme.colors.primary
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				Column(modifier = Modifier.padding(20.dp)) {

					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = "Заполните поля",
							style = TextStyle(
								fontSize = 24.sp,
								fontFamily = FontFamily.Default,
								fontWeight = FontWeight.Bold
							)
						)
						Icon(
							imageVector = Icons.Filled.ExitToApp,
							contentDescription = "",
							tint = colorResource(R.color.darker_gray),
							modifier = Modifier
								.width(30.dp)
								.height(30.dp)
								.clickable { setShowDialog(false) }
						)
					}

					Spacer(modifier = Modifier.height(20.dp))

					TextField(
						modifier = Modifier
							.fillMaxWidth()
							.border(
								BorderStroke(
									width = 2.dp,
									color = colorResource(id = if (titleFieldError.value.isEmpty()) R.color.holo_blue_dark else R.color.holo_red_dark)
								),
								shape = RoundedCornerShape(50)
							),
						colors = TextFieldDefaults.textFieldColors(
							backgroundColor = Color.Transparent,
							focusedIndicatorColor = Color.Transparent,
							unfocusedIndicatorColor = Color.Transparent
						),
						leadingIcon = {
							Icon(
								imageVector = Icons.Filled.Create,
								contentDescription = "",
								tint = colorResource(R.color.holo_purple),
								modifier = Modifier
									.width(20.dp)
									.height(20.dp)
							)
						},
						placeholder = { Text(text = "Введите заголовок", color = Color.White) },
						value = titleField.value,
						onValueChange = {
							titleField.value = it
						})
					Spacer(modifier = Modifier.padding(10.dp))
					TextField(
						value = textField.value,
						onValueChange = { textField.value = it },
						placeholder = { Text(text = "Опишите проблему", color = Color.White) },
						modifier = Modifier
							.fillMaxWidth()
							.height(150.dp)
							.border(
								BorderStroke(
									width = 2.dp,
									color = colorResource(id = if (textFieldError.value.isEmpty()) R.color.holo_blue_dark else R.color.holo_red_dark)
								),
								shape = RoundedCornerShape(10)
							),
						colors = TextFieldDefaults.textFieldColors(
							backgroundColor = Color.Transparent,
							focusedIndicatorColor = Color.Transparent,
							unfocusedIndicatorColor = Color.Transparent
						),
					)

					Spacer(modifier = Modifier.height(5.dp))

					Box() {
						Button(
							onClick = {
								if (titleField.value.isEmpty() || textField.value.isEmpty()) {
									titleFieldError.value = "Поля должны быть заполнены"
									return@Button
								}
								setValue(titleField.value, textField.value)
								setShowDialog(false)
							},
							shape = RoundedCornerShape(50.dp),
							modifier = Modifier
								.fillMaxWidth()
								.height(50.dp)
						) {
							Text(text = "Добавить", color = MaterialTheme.colors.primary)
						}
					}
				}
			}
		}
	}
}

@Composable
fun AddCommentDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
	val textFieldError = remember { mutableStateOf("") }
	val textField = remember { mutableStateOf(value) }

	Dialog(onDismissRequest = { setShowDialog(false) }) {
		Surface(
			shape = RoundedCornerShape(16.dp),
			color = MaterialTheme.colors.primary
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				Column(modifier = Modifier.padding(20.dp)) {

					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						Text(
							text = "Заполните поля",
							style = TextStyle(
								fontSize = 24.sp,
								fontFamily = FontFamily.Default,
								fontWeight = FontWeight.Bold
							)
						)
						Icon(
							imageVector = Icons.Filled.ExitToApp,
							contentDescription = "",
							tint = colorResource(R.color.darker_gray),
							modifier = Modifier
								.width(30.dp)
								.height(30.dp)
								.clickable { setShowDialog(false) }
						)
					}

					Spacer(modifier = Modifier.height(20.dp))
					TextField(
						value = textField.value,
						onValueChange = { textField.value = it },
						placeholder = { Text(text = "Опишите проблему", color = Color.White) },
						modifier = Modifier
							.fillMaxWidth()
							.height(150.dp)
							.border(
								BorderStroke(
									width = 2.dp,
									color = colorResource(id = if (textFieldError.value.isEmpty()) R.color.holo_blue_dark else R.color.holo_red_dark)
								),
								shape = RoundedCornerShape(10)
							),
						colors = TextFieldDefaults.textFieldColors(
							backgroundColor = Color.Transparent,
							focusedIndicatorColor = Color.Transparent,
							unfocusedIndicatorColor = Color.Transparent
						),
					)

					Spacer(modifier = Modifier.height(5.dp))

					Box() {
						val buttonColor = ButtonDefaults.buttonColors(
							backgroundColor = MaterialTheme.colors.primary,
							contentColor = Color.White,
						)
						Button(
							onClick = {
								if (textField.value.isEmpty()) {
									textFieldError.value = "Поля должны быть заполнены"
									return@Button
								}
								setValue(textField.value)
								setShowDialog(false)
							},
							shape = RoundedCornerShape(50.dp),
							modifier = Modifier
								.fillMaxWidth()
								.height(50.dp),
							colors = buttonColor
						) {
							Text(text = "Добавить", color = MaterialTheme.colors.primary)
						}
					}
				}
			}
		}
	}
}

@Composable
fun ChangeBranchDialog(list: List<String>, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
	Dialog(onDismissRequest = { setShowDialog(false) }) {
		Surface(
			shape = RoundedCornerShape(16.dp),
			color = MaterialTheme.colors.primary
		) {
			Box(
				contentAlignment = Alignment.Center
			) {
				Column(modifier = Modifier.padding(20.dp)) {
					VerticalRecyclreView(list, setValue)
				}
			}
		}
	}
}

@Composable
private fun VerticalRecyclreView(branchList: List<String>, openRepositoryWithBranch: (String) -> Unit) {
	LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
		items(items = branchList) {
			ListItem(branch = it, openRepositoryWithBranch)
		}
	}
}

@Composable
private fun ListItem(branch: String, openRepostitoryWithBranch: (String) -> Unit) {
	Surface(
		color = MaterialTheme.colors.secondary,
		modifier = Modifier
			.padding(vertical = 4.dp, horizontal = 8.dp)
			.fillMaxWidth()
	) {
		Column(modifier = Modifier.clickable { (openRepostitoryWithBranch(branch)) }
		) {
			Text(text = branch, style = MaterialTheme.typography.body1, color = Color.White)
		}
	}
}

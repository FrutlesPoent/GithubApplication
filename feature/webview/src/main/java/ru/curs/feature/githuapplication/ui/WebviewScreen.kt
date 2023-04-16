package ru.curs.feature.githuapplication.ui

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

// TODO Добавить viewmodel, чтоб можно было передавать ссылки аргументами

@Composable
fun WebviewScreen() {

	val url = "https://github.com/FrutlesPoent/FrutlesPoent"
	AndroidView(factory = {
		WebView(it).apply {
			layoutParams = ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT,
			)
			webViewClient = WebViewClient()
			loadUrl(url)
		}
	}, update = {
		it.loadUrl(url)
	})
}
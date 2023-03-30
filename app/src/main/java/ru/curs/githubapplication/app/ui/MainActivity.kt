package ru.curs.githubapplication.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import org.koin.java.KoinJavaComponent
import ru.curs.githubapplication.component.design.resources.theme.GithubApplicationTheme
import ru.curs.githubapplication.di.routersModule
import ru.curs.githubapplication.feature.authorization.di.authorizationModule
import ru.curs.githubapplication.feature.splash.di.splashModule

class MainActivity : FragmentActivity() {

	private companion object {

		val modules = listOf(
			routersModule,
			splashModule,
			authorizationModule,
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		KoinJavaComponent.getKoin().loadModules(modules)

		setContent {
			GithubApplicationTheme {
				ApplicationScreen()
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()

		KoinJavaComponent.getKoin().unloadModules(modules)
	}
}
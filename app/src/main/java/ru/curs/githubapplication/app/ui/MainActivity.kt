package ru.curs.githubapplication.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import org.koin.java.KoinJavaComponent
import ru.curs.githubapplication.component.design.resources.theme.GithubApplicationTheme
import ru.curs.githubapplication.data.auth.di.appAuthModule
import ru.curs.githubapplication.data.auth.di.networkModule
import ru.curs.githubapplication.data.di.fetchFollowersModule
import ru.curs.githubapplication.data.di.repositoryModule
import ru.curs.githubapplication.data.di.userProfileModule
import ru.curs.githubapplication.di.followModule
import ru.curs.githubapplication.di.rawDataParser
import ru.curs.githubapplication.di.routersModule
import ru.curs.githubapplication.feature.authorization.di.authorizationModule
import ru.curs.githubapplication.feature.splash.di.splashModule
import ru.curs.githubapplication.fileview.di.fileViewViewModelModule
import ru.curs.githubapplication.issue.detail.di.issueDetailViewModelModule
import ru.curs.githubapplication.issue.di.issueViewModelModule
import ru.curs.githubapplication.repository.di.repositoryViewModel
import ru.curs.githubapplication.repositorydetail.di.repositoryDetailViewModelModule
import ru.curs.githubapplication.shared.issue.data.di.issueModule
import ru.curs.githubapplication.userprofile.di.userProfileViewModelModule

class MainActivity : FragmentActivity() {

	private companion object {

		val modules = listOf(
			routersModule,
			networkModule,
			splashModule,
			authorizationModule,
			userProfileViewModelModule,
			appAuthModule,
			userProfileModule,
			followModule,
			fetchFollowersModule,
			repositoryModule,
			repositoryViewModel,
			rawDataParser,
			repositoryDetailViewModelModule,
			fileViewViewModelModule,
			issueViewModelModule,
			issueDetailViewModelModule,
			issueModule,
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		KoinJavaComponent.getKoin().loadModules(modules)

		setContent {
			GithubApplicationTheme {
				Column(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
				) {
					ApplicationScreen()
				}
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()

		KoinJavaComponent.getKoin().unloadModules(modules)
	}
}
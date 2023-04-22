package ru.curs.githubapplication.app.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.java.KoinJavaComponent.inject
import ru.curs.feature.githuapplication.ui.WebviewScreen
import ru.curs.githubapplication.domain.entity.IssueEntity
import ru.curs.githubapplication.domain.entity.RepositoryTree
import ru.curs.githubapplication.feature.authorization.ui.AuthorizationScreen
import ru.curs.githubapplication.feature.splash.ui.SplashScreen
import ru.curs.githubapplication.fileview.ui.FileViewScreen
import ru.curs.githubapplication.issue.detail.ui.IssueDetailScreen
import ru.curs.githubapplication.issue.ui.IssueScreen
import ru.curs.githubapplication.navigation.NavigationTree
import ru.curs.githubapplication.navigation.ext.fromJson
import ru.curs.githubapplication.repository.ui.RepositoryScreen
import ru.curs.githubapplication.repositorydetail.entity.FileUrlTransfer
import ru.curs.githubapplication.repositorydetail.ui.RepositoryDetailScreen
import ru.curs.githubapplication.ui.FollowScreen
import ru.curs.githubapplication.userprofile.ui.UserProfileScreen

@Composable
fun ApplicationScreen() {
	val navController by inject<NavHostController>(NavController::class.java)
	NavHost(navController = navController, startDestination = NavigationTree.Splash.name) {
		composable(NavigationTree.Splash.name) { SplashScreen() }
		composable(NavigationTree.Authorization.name) { AuthorizationScreen() }
		composable(NavigationTree.UserProfile.name) { UserProfileScreen() }
		composable(NavigationTree.Follows.name) { FollowScreen() }
		composable(NavigationTree.UserProfileDetail.name + "/{username}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("username")?.let { userNameString ->
				// TODO Добавить детали
			}
		}
		composable(NavigationTree.Repository.name + "/{repository}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("repository")?.let { model ->
				val repository = model.fromJson(RepositoryTree::class.java)
				RepositoryScreen(repository)
			}
		}
		composable(NavigationTree.Webview.name) { WebviewScreen() }
		composable(NavigationTree.RepositoryDetail.name + "/{repository}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("repository")?.let { it ->
				val repository = it.fromJson(RepositoryTree::class.java)
				RepositoryDetailScreen(repository = repository)
			}
		}
		composable(NavigationTree.FileView.name + "/{rawDataFile}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("rawDataFile")?.let { it ->
				val rawDataFile = it.fromJson(FileUrlTransfer::class.java)
				FileViewScreen(rawDataFile = rawDataFile)
			}
		}
		composable(NavigationTree.Issue.name + "/{repository}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("repository")?.let { it ->
				val repository = it.fromJson(RepositoryTree::class.java)
				IssueScreen(repository = repository)
			}
		}
		composable(NavigationTree.IssueDetail.name + "/{issue}") { navBackStackEntry ->
			navBackStackEntry.arguments?.getString("issue")?.let { it ->
				val issue = it.fromJson(IssueEntity::class.java)
				IssueDetailScreen(issue = issue)
			}
		}
//		composable(NavigationTree.Repository.name + "/{repository") { navBackStackEntry ->
//			navBackStackEntry.arguments?.get("repository")?.let { it ->
//				val repository = it.fromJson(RepositoryTree::class.java)
//				RepositoryScreen(repository)
//			}
//		}
	}

}
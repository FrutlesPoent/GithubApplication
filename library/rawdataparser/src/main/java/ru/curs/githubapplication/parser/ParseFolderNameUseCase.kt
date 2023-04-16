package ru.curs.githubapplication.parser

// example RepositoryContent(content=null, download_url=null, encoding=null, git_url=https://api.github.com/repos/FrutlesPoent/ABCLabs/git/trees/115a2addfc7fa80cd5a4ae8b0c49007425e48c00, html_url=https://github.com/FrutlesPoent/ABCLabs/tree/main/abc4, name=abc4, path=abc4, sha=115a2addfc7fa80cd5a4ae8b0c49007425e48c00, size=0, type=dir, url=https://api.github.com/repos/FrutlesPoent/ABCLabs/contents/abc4?ref=main)
class ParseFolderNameUseCase {

	operator fun invoke(url: String): String =
		url.split("/").last()
}
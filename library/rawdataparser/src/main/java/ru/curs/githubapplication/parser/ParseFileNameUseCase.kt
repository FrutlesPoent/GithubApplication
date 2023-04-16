package ru.curs.githubapplication.parser

// exampleRepositoryContent(content=null, download_url=https://raw.githubusercontent.com/FrutlesPoent/ABCLabs/main/manager.cpp?token=ANAYNRZIXV4UNRCQL72NNYLEHL4EE, encoding=null, git_url=https://api.github.com/repos/FrutlesPoent/ABCLabs/git/blobs/187fe797699e2528ee1fbf3aaa9e669ee3ba36dc, html_url=https://github.com/FrutlesPoent/ABCLabs/blob/main/manager.cpp, name=manager.cpp, path=manager.cpp, sha=187fe797699e2528ee1fbf3aaa9e669ee3ba36dc, size=3587, type=file, url=https://api.github.com/repos/FrutlesPoent/ABCLabs/contents/manager.cpp?ref=main)
class ParseFileNameUseCase {

	operator fun invoke(url: String): String =
		url.split("/").last().split("?").first()
}
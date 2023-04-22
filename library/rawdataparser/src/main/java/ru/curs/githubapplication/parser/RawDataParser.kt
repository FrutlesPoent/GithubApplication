package ru.curs.githubapplication.parser

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import ru.curs.githubapplication.entity.Line
import java.net.URL

class ParseRawData {

	suspend operator fun invoke(text: URL): List<Line> {
		val resultList = mutableListOf<Line>()
		val job = CoroutineScope(Dispatchers.IO).async {
			val response = text.readText().split("\n")
			for (i in response.indices) {
				resultList.add(
					Line(
						position = i + 1,
						info = response[i],
					)
				)
			}
			return@async resultList
		}
		job.await()
		return resultList
	}
}
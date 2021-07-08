package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

interface MoviesDataSource {
	fun getMovies(): List<MovieDto>
}
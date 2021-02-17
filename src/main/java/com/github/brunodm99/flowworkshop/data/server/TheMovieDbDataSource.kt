package com.github.brunodm99.flowworkshop.data.server

import com.github.brunodm99.flowworkshop.data.domain.Movie
import com.github.brunodm99.flowworkshop.data.datasource.RemoteDataSource
import com.github.brunodm99.flowworkshop.data.toDomainMovie

class TheMovieDbDataSource(private val apiKey: String) : RemoteDataSource {
    override suspend fun getMovies(page: Int): List<Movie> {
        return TheMovieDb.service
                .listPopularMoviesAsync(apiKey, page)
                .results
                .map { it.toDomainMovie() }
    }


}
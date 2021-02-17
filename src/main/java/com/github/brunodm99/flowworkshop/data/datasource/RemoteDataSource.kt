package com.github.brunodm99.flowworkshop.data.datasource

import com.github.brunodm99.flowworkshop.data.domain.Movie

interface RemoteDataSource {
    suspend fun getMovies(page: Int): List<Movie>
}
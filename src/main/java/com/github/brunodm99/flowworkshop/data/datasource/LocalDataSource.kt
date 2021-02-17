package com.github.brunodm99.flowworkshop.data.datasource

import com.github.brunodm99.flowworkshop.data.domain.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun moviesSize(): Int
    suspend fun saveMovies(movies: List<Movie>)
    fun getMovies(): Flow<List<Movie>>
}
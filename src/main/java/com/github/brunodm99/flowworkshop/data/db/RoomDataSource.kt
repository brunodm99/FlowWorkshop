package com.github.brunodm99.flowworkshop.data.db

import com.github.brunodm99.flowworkshop.data.datasource.LocalDataSource
import com.github.brunodm99.flowworkshop.data.domain.Movie
import com.github.brunodm99.flowworkshop.data.toDomainMovie
import com.github.brunodm99.flowworkshop.data.toRoomMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(db: Database) : LocalDataSource {
    private val movieDao = db.movieDao()

    override suspend fun moviesSize(): Int = movieDao.movieCount()

    override suspend fun saveMovies(movies: List<Movie>) {
       movieDao.insertMovies(movies.map{ it.toRoomMovie() })
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getAll()
                .map { movies -> movies.map{ it.toDomainMovie() } }
    }
}
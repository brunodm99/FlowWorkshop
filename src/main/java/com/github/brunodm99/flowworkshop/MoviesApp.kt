package com.github.brunodm99.flowworkshop

import android.app.Application
import androidx.room.Room
import com.github.brunodm99.flowworkshop.data.db.Database

class MoviesApp : Application() {
    lateinit var db: Database
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, Database::class.java, "movie-db").build()
    }
}
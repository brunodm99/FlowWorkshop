package com.github.brunodm99.flowworkshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.RoomDatabase
import com.github.brunodm99.flowworkshop.R
import com.github.brunodm99.flowworkshop.data.db.RoomDataSource
import com.github.brunodm99.flowworkshop.data.domain.MoviesRepository
import com.github.brunodm99.flowworkshop.data.server.TheMovieDbDataSource
import com.github.brunodm99.flowworkshop.databinding.ActivityMainBinding
import com.github.brunodm99.flowworkshop.ui.common.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply{
            setContentView(root)

            viewModel = getViewModel(::buildViewModel)

            val moviesAdapter = MoviesAdapter(lifecycleScope)

            lifecycleScope.collectFlow(viewModel.spinner){ progress.visible = it }
            lifecycleScope.collectFlow(viewModel.movies){ moviesAdapter.submitList(it) }

            lifecycleScope.collectFlow(recycler.lastVisibleEvents){ viewModel.notifyLastVisible(it) }

            recycler.adapter = moviesAdapter
        }
    }

    fun buildViewModel() = MainViewModel(
        MoviesRepository(
            RoomDataSource(app.db),
            TheMovieDbDataSource(getString(R.string.api_key))))
}
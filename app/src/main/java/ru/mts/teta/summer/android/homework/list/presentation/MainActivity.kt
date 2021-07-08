package ru.mts.teta.summer.android.homework.list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import ru.mts.teta.summer.android.homework.list.R
import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MainActivity : AppCompatActivity() {
	private lateinit var moviesModel: MoviesModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		initDataSource()
		setupViews()
	}
	
	private fun initDataSource() {
		moviesModel = MoviesModel(MoviesDataSourceImpl())
	}
	
	private fun setupViews() {
		getMovieAt(0)?.let {
			// https://coil-kt.github.io/coil/
			findViewById<ImageView>(R.id.ivHeader)?.load(it.imageUrl)
			
			findViewById<TextView>(R.id.tvTitle)?.text = it.title
			findViewById<TextView>(R.id.tvDescription)?.text = it.description
			findViewById<TextView>(R.id.tvRating)?.text = it.rateScore.toString()
			findViewById<TextView>(R.id.tvAgeRestriction)?.text = it.ageRestriction.toString()
		}
	}
	
	private fun getMovieAt(position: Int): MovieDto? {
		val movies = moviesModel.getMovies()
		return when {
			movies.isEmpty() -> null
			position >= movies.size -> null
			else -> movies[position]
		}
	}
}
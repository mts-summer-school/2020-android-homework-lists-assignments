package ru.mts.teta.summer.android.homework.list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
		setupListeners()
	}
	
	private fun initDataSource() {
		moviesModel = MoviesModel(MoviesDataSourceImpl())
	}

	private fun setupViews() {
		getMovieAt(MOVIES_INITIAL_POSITION)?.let {
			// https://coil-kt.github.io/coil/
			findViewById<ImageView>(R.id.ivHeader)?.load(it.imageUrl)
			
			findViewById<TextView>(R.id.tvTitle)?.text = it.title
			findViewById<TextView>(R.id.tvDescription)?.text = it.description
			findViewById<TextView>(R.id.tvRating)?.text =
				getString(R.string.main_rating_text, it.rateScore)
			findViewById<TextView>(R.id.tvAgeRestriction)?.text =
				getString(R.string.main_age_restriction_text, it.ageRestriction)
		}
	}

	private fun setupListeners() {
		findViewById<View>(R.id.root)?.setOnClickListener {
			showToast(getToastMessage())
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

	private fun getToastMessage() = getMovieAt(MOVIES_INITIAL_POSITION)?.title?.let {
		getString(R.string.main_click_message, it)
	}

	private fun showToast(message: String?) {
		when {
			message.isNullOrEmpty() -> { showToast(getString(R.string.main_empty_message)) }
			else -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
		}
	}
}

private const val MOVIES_INITIAL_POSITION = 0
package com.android.randomjokes.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.android.randomjokes.R
import com.android.randomjokes.databinding.ActivityJokeBinding
import com.android.randomjokes.utils.isNetworkAvailable
import com.android.randomjokes.viewmodel.JokeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ONE_MIN: Long = 60000

class JokeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityJokeBinding
    private var mAdapter: JokesAdapter? = JokesAdapter()
    private val jockViewModel: JokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.jokesRecyclerView.adapter = mAdapter

        callApiInPeriodically()

        with(jockViewModel) {

            jokesData.observe(this@JokeActivity) {
                mAdapter?.jokeList = it
            }

            messageData.observe(this@JokeActivity) {
                Toast.makeText(this@JokeActivity, it, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        mAdapter = null
        super.onDestroy()
    }

    private fun callApiInPeriodically() {
        lifecycleScope.apply {
            launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    while (true) {
                        callApi()
                        delay(ONE_MIN)
                    }
                }
            }
        }
    }

    private fun callApi() {
        if (isNetworkAvailable()) {
            jockViewModel.getJoke()
        } else {
            Toast.makeText(
                this,
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
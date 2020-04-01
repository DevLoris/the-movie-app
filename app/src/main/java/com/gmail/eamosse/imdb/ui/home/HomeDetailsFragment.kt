package com.gmail.eamosse.imdb.ui.home

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentHomeDetailsBinding
import com.gmail.eamosse.imdb.ui.home.adapter.SimilarMovieAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_home_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeDetailsFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeDetailsBinding
    val args: HomeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.i("IMDB", "Pre Loaded")

        binding = FragmentHomeDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeDetailsFragment
            viewModel = homeViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                getMovie(args.id)
                getTrailer(args.id)
                getSimilarMovies(args.id)
            })
            video.observe(viewLifecycleOwner, Observer {
                getLifecycle().addObserver(youtube_player_view);
                youtube_player_view.visibility = View.INVISIBLE
                youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.pause();
                        video.value?.let {
                            youTubePlayer.loadVideo(it.key, 0f)
                            youTubePlayer.play();
                            youtube_player_view.visibility = View.VISIBLE
                        }
                    }
                })
            })
            similarMovie.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = SimilarMovieAdapter(it) {
                }
            })
        }
    }
}

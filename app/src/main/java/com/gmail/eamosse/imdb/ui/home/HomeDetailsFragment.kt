package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gmail.eamosse.imdb.databinding.FragmentDiscoverBinding
import com.gmail.eamosse.imdb.databinding.FragmentHomeDetailsBinding
import com.gmail.eamosse.imdb.ui.home.adapter.DiscoverAdapter
import com.gmail.eamosse.imdb.ui.home.adapter.SimilarMovieAdapter
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
                getSimilarMovies(args.id)
            })
            similarMovie.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = SimilarMovieAdapter(it) {
                }
            })
        }
    }
}

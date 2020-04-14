package com.gmail.eamosse.imdb.ui.actor


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.gmail.eamosse.imdb.databinding.FragmentActorDetailsBinding
import com.gmail.eamosse.imdb.ui.actor.adapter.ActorMoviesAdapter
import com.gmail.eamosse.imdb.ui.actor.adapter.ActorMoviesListAdapter


class ActorDetailsFragment : Fragment() {

    private val actorViewModel: ActorViewModel by viewModel()
    private lateinit var binding: FragmentActorDetailsBinding
    val args: ActorDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("IMDB", "Pre Loaded")

        binding = FragmentActorDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ActorDetailsFragment
            viewModel = actorViewModel
        }

        binding.actorMovies.shimmerViewContainer.startShimmerAnimation()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(actorViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                getActor(args.id)
                getActorMovie(args.id)

            })

            actorMovies.observe(viewLifecycleOwner, Observer {
                binding.actorMovies.shimmerViewContainer.stopShimmerAnimation()
                binding.actorMovies.shimmerViewContainer.visibility =  View.GONE
                binding.actorMovies.categoryList.adapter = ActorMoviesListAdapter(it) {
                }
            })
        }
    }

}

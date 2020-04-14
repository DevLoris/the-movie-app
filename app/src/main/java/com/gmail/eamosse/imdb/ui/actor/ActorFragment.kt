package com.gmail.eamosse.imdb.ui.actor


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentActorBinding
import com.gmail.eamosse.imdb.ui.actor.adapter.ActorAdapter
import com.gmail.eamosse.imdb.ui.actor.adapter.ActorMoviesAdapter
import com.gmail.eamosse.imdb.ui.trending.TrendingFragmentDirections
import kotlinx.android.synthetic.main.fragment_actor.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorFragment : Fragment() {

    private val actorViewModel: ActorViewModel by viewModel()
    private lateinit var binding: FragmentActorBinding

    private lateinit var spinner: Spinner
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText
    private lateinit var moviesContainer: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SPINNER
        //init spinner
        spinner = actor_spinner
        //get spinner data
        val spinnerData = resources.getStringArray(R.array.actors_search)
        //set spinner data
        spinner.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerData
        )

        //EDIT TEXT
        //init edit text
        searchEditText = actor_search_et

        //MOVIES CONTAINER
        moviesContainer = actor_movies

        //init to hidden
        moviesContainer.visibility = View.INVISIBLE

        //SEARCH BUTTON
        searchButton = search_actor_btn
        searchButton.setOnClickListener {
            //search view model
            actorViewModel.onSearch(searchEditText.text.toString())
        }

    }

    private fun _getSearchValue(): String {
        return when (spinner.selectedItem.toString()) {
            "Series" -> "tv"
            "Movies" -> "movie"
            else -> "movie"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ActorFragment
        }

        binding.actorMovies.shimmerViewContainer.startShimmerAnimation()

        with(actorViewModel) {

            movies.observe(viewLifecycleOwner, Observer {

                //show movies
                moviesContainer.visibility = View.VISIBLE

                binding.actorMovies.shimmerViewContainer.stopShimmerAnimation()
                binding.actorMovies.shimmerViewContainer.visibility = View.GONE

                binding.actorMovies.categoryList.adapter = ActorMoviesAdapter(it) {
                    getActors(it.id)
                }
            })

            actors.observe(viewLifecycleOwner, Observer {
                binding.actorMovies.shimmerViewContainer.stopShimmerAnimation()
                binding.actorMovies.shimmerViewContainer.visibility = View.GONE

                binding.actorMovies.categoryList.adapter = ActorAdapter(it) {
                    //action to actor details
                    val action = ActorFragmentDirections
                        .actionActorFragmentToActorDetailsFragment(it.id)
                    NavHostFragment.findNavController(this@ActorFragment)
                        .navigate(action)
                }
            })
        }

        return binding.root

    }
}
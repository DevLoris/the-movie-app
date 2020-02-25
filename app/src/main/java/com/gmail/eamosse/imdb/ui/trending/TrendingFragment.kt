package com.gmail.eamosse.imdb.ui.trending

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.databinding.TrendingFragmentBinding
import com.gmail.eamosse.imdb.ui.home.HomeDiscoverFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import com.gmail.eamosse.imdb.ui.trending.adapter.TrendingCategoryAdapter
import com.gmail.eamosse.imdb.ui.trending.adapter.TrendingMovieAdapter
import com.gmail.eamosse.imdb.ui.trending.adapter.TrendingPeopleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModel()
    private lateinit var binding : TrendingFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = TrendingFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@TrendingFragment
        }

        binding.trendingCategories.shimmerViewContainer.startShimmerAnimation()
        binding.trendingActors.shimmerViewContainer.startShimmerAnimation()
        binding.trendingMovies.shimmerViewContainer.startShimmerAnimation()

        with(trendingViewModel) {

            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                android.util.Log.i("IMDB", "Get category")
                getCategories()
                getTrendingMovies()
                getTrendingPeoples()
            })

            categories.observe(viewLifecycleOwner, Observer {
                binding.trendingCategories.shimmerViewContainer.stopShimmerAnimation()
                binding.trendingCategories.shimmerViewContainer.visibility =  View.GONE

                binding.trendingCategories.categoryList.adapter = TrendingCategoryAdapter(it)  {
                    val action = TrendingFragmentDirections
                        .actionTrendingFragmentToHomeDiscoverFragment(it.id)
                    NavHostFragment.findNavController(this@TrendingFragment)
                        .navigate(action)
                }
            })

            trendingMovies.observe(viewLifecycleOwner, Observer {
                binding.trendingMovies.shimmerViewContainer.stopShimmerAnimation()
                binding.trendingMovies.shimmerViewContainer.visibility =  View.GONE

                binding.trendingMovies.categoryList.adapter = TrendingMovieAdapter(it) {
                    val action = TrendingFragmentDirections
                        .actionTrendingFragmentToHomeDetailsFragment(it.id)
                    NavHostFragment.findNavController(this@TrendingFragment)
                        .navigate(action)
                }
            })

            trendingPeoples.observe(viewLifecycleOwner, Observer {
                binding.trendingActors.shimmerViewContainer.stopShimmerAnimation()
                binding.trendingActors.shimmerViewContainer.visibility =  View.GONE
                binding.trendingActors.categoryList.adapter = TrendingPeopleAdapter(it) {
                }
            })
        }
        return binding.root
    }
}

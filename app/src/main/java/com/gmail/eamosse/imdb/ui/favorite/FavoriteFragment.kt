package com.gmail.eamosse.imdb.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FavoriteFragmentBinding
import com.gmail.eamosse.imdb.ui.favorite.adapter.FavoriteMovieAdapter
import com.gmail.eamosse.imdb.ui.search.adapter.SearchDiscoverAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding : FavoriteFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@FavoriteFragment
            viewModel = favoriteViewModel
        }

        with(favoriteViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                android.util.Log.i("IMDB", "Get category")
            })
            favorites.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = FavoriteMovieAdapter(it) {
                }
            })
        }
        return binding.root
    }
}

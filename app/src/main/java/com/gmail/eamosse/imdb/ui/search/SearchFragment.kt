package com.gmail.eamosse.imdb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.SearchFragmentBinding
import com.gmail.eamosse.imdb.ui.favorite.FavoriteViewModel
import com.gmail.eamosse.imdb.ui.search.adapter.SearchDiscoverAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var binding : SearchFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@SearchFragment
            viewModel = searchViewModel
        }

        with(searchViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                android.util.Log.i("IMDB", "Get category")
            })
            searchs.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = SearchDiscoverAdapter(it) {
                    /*val action = HomeDiscoverFragmentDirections
                        .actionHomeDiscoverFragmentToHomeDetailsFragment(it.id)
                    NavHostFragment.findNavController(this@SearchFragment)
                        .navigate(action)*/
                }
            })
        }
        return binding.root
    }
}

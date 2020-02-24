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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gmail.eamosse.imdb.databinding.FragmentDiscoverBinding
import com.gmail.eamosse.imdb.ui.home.adapter.DiscoverAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDiscoverFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentDiscoverBinding
    val args: HomeDiscoverFragmentArgs by navArgs()

    companion object
    {
        const val ARG_GENRE = "genre"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.i("IMDB", "Pre Loaded")
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("IMDB", "Loaded")
        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                Log.i("IMDB", "Get category")
                getDiscover(genreId = args.genre)
            })
            discoveries.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = DiscoverAdapter(it) {

                }
            })

        }
    }
}

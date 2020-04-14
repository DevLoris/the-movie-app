package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.databinding.FragmentDiscoverBinding
import com.gmail.eamosse.imdb.ui.home.adapter.DiscoverAdapter
import kotlinx.android.synthetic.main.fragment_home_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeDiscoverFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentDiscoverBinding
    val args: HomeDiscoverFragmentArgs by navArgs()

    companion object
    {
        const val ARG_GENRE = "genre"
    }

    var page:Int = 1;

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

                //On charge les films
                getDiscover(genreId = args.genre)

                //On ajoute un listener qui détecte le fin fond de la liste et qui va charger les films suivants. On le fait à ce moment la pour pas qu'il se crée avant d'avoir des films
                category_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                        //Si il peut pas scroll plus loin on déclenche un load more
                        if (!recyclerView.canScrollVertically(1)) {
                            loadMoreDiscover(args.genre, page++)
                        }
                    }
                })
            })

            //Click sur le fragment
            discoveries.observe(viewLifecycleOwner, Observer {
                binding.categoryList.adapter = DiscoverAdapter(it) {
                    val action = HomeDiscoverFragmentDirections
                        .actionHomeDiscoverFragmentToHomeDetailsFragment(it.id)
                    NavHostFragment.findNavController(this@HomeDiscoverFragment)
                        .navigate(action)
                }
            })

        }
    }
}

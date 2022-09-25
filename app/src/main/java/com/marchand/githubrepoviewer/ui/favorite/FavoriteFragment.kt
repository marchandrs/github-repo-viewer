package com.marchand.githubrepoviewer.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marchand.githubrepoviewer.adapters.FavoriteReposAdapter
import com.marchand.githubrepoviewer.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var favReposAdapter: FavoriteReposAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            favoriteViewModel.loadFavRepos()
        }
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvFavRepos.layoutManager = LinearLayoutManager(context)
        favReposAdapter = FavoriteReposAdapter()
        binding.rvFavRepos.adapter = favReposAdapter
    }

    private fun setupObservers() {
        favoriteViewModel.favRepos.observe(viewLifecycleOwner) {
            if (it != null) {
                favReposAdapter.setFavReposList(it)
                favReposAdapter.notifyDataSetChanged()
            }
        }
        favoriteViewModel.tvFavRepoLabelVisible.observe(viewLifecycleOwner) {
            binding.tvFavreposLabel1.visibility = if (it) {View.VISIBLE} else {View.GONE}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
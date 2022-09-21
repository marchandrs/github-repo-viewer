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
import com.marchand.githubrepoviewer.daos.RepoDao
import com.marchand.githubrepoviewer.databinding.FragmentFavoritesBinding
import com.marchand.githubrepoviewer.db.RepoDatabase
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var favReposAdapter: FavoriteReposAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var repoDao: RepoDao

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fetchDatabase()
        setupObservers()
        setupRecyclerView()
        return root
    }

    private fun fetchDatabase() {
        repoDao = RepoDatabase.getInstance(requireContext()).repoDao
        lifecycleScope.launch {
            favoriteViewModel.favReposList.postValue(repoDao.getRepos())
        }

    }

    private fun setupRecyclerView() {
        binding.rvFavRepos.layoutManager = LinearLayoutManager(context)
        favReposAdapter = FavoriteReposAdapter()
        binding.rvFavRepos.adapter = favReposAdapter
    }

    private fun setupObservers() {
        favoriteViewModel.favReposList.observe(viewLifecycleOwner) {
            if (it != null) {
                favReposAdapter.setFavReposList(it)
                favReposAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.marchand.githubrepoviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marchand.githubrepoviewer.adapters.ReposAdapter
import com.marchand.githubrepoviewer.databinding.FragmentHomeBinding
import com.marchand.githubrepoviewer.ui.settings.SettingsViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var reposAdapter: ReposAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupObservers()
        setupRecyclerView()
        setupUser("appswefit")
        return root
    }

    private fun setupUser(user: String) {
        settingsViewModel.user.postValue(user)
    }

    private fun setupObservers() {
        settingsViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                settingsViewModel.user.value?.let {
                        it1 -> homeViewModel.fetchApi(it1)
                }
            }
        }

        homeViewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                reposAdapter.setReposList(it)
                reposAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUserRepos.layoutManager = LinearLayoutManager(context)
        reposAdapter = ReposAdapter()
        binding.rvUserRepos.adapter = reposAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
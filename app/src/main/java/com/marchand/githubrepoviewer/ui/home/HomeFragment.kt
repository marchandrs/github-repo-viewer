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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
    }

    private fun setupObservers() {
        settingsViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                settingsViewModel.user.value?.let {
                        it1 -> homeViewModel.fetchApi(it1)
                }
            }
        }

        homeViewModel.repos.observe(viewLifecycleOwner) {
            if (it != null) {
                reposAdapter.setReposList(it)
                reposAdapter.notifyDataSetChanged()
            }
        }

        homeViewModel.tvReposLabelVisible.observe(viewLifecycleOwner) {
            binding.tvReposLabel1.visibility = if (it) {View.VISIBLE} else {View.INVISIBLE}
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
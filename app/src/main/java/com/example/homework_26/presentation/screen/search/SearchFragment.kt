package com.example.homework_26.presentation.screen.search

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_26.databinding.FragmentSearchBinding
import com.example.homework_26.presentation.adapter.SearchItemRecyclerAdapter
import com.example.homework_26.presentation.base.BaseFragment
import com.example.homework_26.presentation.extention.onDebouncedListener
import com.example.homework_26.presentation.screen.search.event.SearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchFragmentViewModel by viewModels()
    private var adapter: SearchItemRecyclerAdapter? = null

    override fun bind() {
        bindAdapter()
    }

    override fun event() {
        viewModel.onEvent(SearchEvent.GetItem())
    }

    override fun observe() {
        observeItemList()
    }

    override fun listener() {
        itemFilterListener()
    }

    private fun bindAdapter() {
        adapter = SearchItemRecyclerAdapter()
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeItemList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemStateFlow.collect { state ->
                    state.itemUiList?.let {
                        adapter?.submitList(it)
                    }

                    if(state.loader){
                        binding.loader.visibility = View.VISIBLE
                    }else{
                        binding.loader.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun itemFilterListener() {
        binding.etSearch.onDebouncedListener(1000L) {
            viewModel.onEvent(SearchEvent.GetItem(it))
        }
    }
}


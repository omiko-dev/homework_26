package com.example.homework_26.presentation.screen.search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_26.databinding.FragmentSearchBinding
import com.example.homework_26.presentation.adapter.SearchItemRecyclerAdapter
import com.example.homework_26.presentation.base.BaseFragment
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
                }
            }
        }
    }

    private fun itemFilterListener() = with(binding) {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("omiko", s.toString()+ "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("omiko", s.toString() + "onTextChanged")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i("omiko", s.toString() + "afterTextChanged")
            }
        })
    }
}


package com.yamanf.shoppingapp.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.data.model.Products
import com.yamanf.shoppingapp.databinding.FragmentSearchBinding
import com.yamanf.shoppingapp.ui.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel : SearchViewModel by viewModels()
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var productList: Products

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        binding.searchEditText.setText("")
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            filterWithQuery(query)
        }

        binding.voiceSearchQuery.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }
        configureRecyclerView()
    }

    private fun configureRecyclerView(){
        viewModel.result.observe(viewLifecycleOwner) {
            productList = it
            attachAdapter(productList)
            binding.rvSearchList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun attachAdapter(list: Products) {
        searchAdapter = SearchAdapter(list)
        binding.rvSearchList.adapter = searchAdapter
    }

    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: Products = onFilterChanged(query)
            attachAdapter(filteredList)
            toggleRecyclerView(filteredList)
        }
    }

    private fun onFilterChanged(filterQuery: String): Products {
        val filteredList = Products()
        for (currentItem in productList) {
            if (currentItem.title.lowercase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentItem)
            }
        }
        return filteredList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            binding.searchEditText.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun toggleRecyclerView(itemList: Products) {
        if (itemList.isEmpty()) {
            binding.rvSearchList.visibility = View.INVISIBLE
            binding.noSearchResultsFoundText.visibility = View.VISIBLE
        } else {
            binding.rvSearchList.visibility = View.VISIBLE
            binding.noSearchResultsFoundText.visibility = View.INVISIBLE
        }
    }
    companion object {
        const val SPEECH_REQUEST_CODE = 0
    }

    override fun onPause() {
        super.onPause()
        binding.searchEditText.setText("")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchEditText.setText("")
        _binding = null
    }

}
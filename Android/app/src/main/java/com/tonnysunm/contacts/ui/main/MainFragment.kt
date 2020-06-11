package com.tonnysunm.contacts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.google.android.material.snackbar.Snackbar
import com.tonnysunm.contacts.databinding.MainFragmentBinding
import com.tonnysunm.contacts.library.*
import com.tonnysunm.contacts.room.User
import timber.log.Timber

//TODO: add networking state machine and initial state machine

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel> {
        AndroidViewModelFactory(requireActivity().application)
    }

    private var _temp: PagedList<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment = this

        val adapter =
            RecyclerAdapter(RecyclerItem.diffCallback<User>()) { recyclerAdapter, index, user ->

            }

        val binding = MainFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = fragment
            viewModel = fragment.viewModel

            recyclerView.adapter = adapter
        }

        val listing = viewModel.getUserListing()
        listing.pagedList.observe(this.viewLifecycleOwner, Observer {
//            updateEmptyTips(it?.size == 0)
            /**
             * submitList to adapter after data source initialization
             * to avoid white screen (lack of data)
             */
            _temp = it
        })

        listing.initialState.observe(this.viewLifecycleOwner, Observer {
            binding.refresher.isRefreshing = it == State.Loading

            if (it is State.Success) {
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
                adapter.submitList(_temp)

                if (it.source == Source.LOCAL) {
                    Snackbar.make(this.requireView(), "\uD83D\uDCF4 OFFLINE", Snackbar.LENGTH_SHORT)
                        .show();
                } else {
                    Snackbar.make(this.requireView(), "\uD83D\uDCF3 ONLINE", Snackbar.LENGTH_SHORT)
                        .show();
                }
            }
        })

        listing.networkState.observe(this.viewLifecycleOwner, Observer { state ->
            Timber.d(state.toString())

            if (state is State.Error) {
                val message = state.message ?: return@Observer

                Snackbar.make(
                    this.requireView(),
                    "\uD83D\uDE2DUsing Cached Data, $message",
                    Snackbar.LENGTH_LONG
                ).show();
            }
        })

        binding.refresher.setOnRefreshListener {
            listing.refresh()
        }

        return binding.root
    }

}

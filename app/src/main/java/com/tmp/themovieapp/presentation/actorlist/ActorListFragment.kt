package com.tmp.themovieapp.presentation.actorlist

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tmp.themovieapp.R
import com.tmp.themovieapp.base.BaseFragment
import com.tmp.themovieapp.databinding.FragmentListBinding
import com.tmp.themovieapp.presentation.main.MainActivity
import com.tmp.themovieapp.presentation.moviedetail.MovieDetailFragmentDirections
import com.tmp.themovieapp.repositories.ActorListRepository

class ActorListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    companion object {
        fun newInstance() = ActorListFragment()
    }

    private lateinit var viewModel: ActorListViewModel
    private lateinit var viewModelFactory: ActorListViewModelFactory
    private lateinit var actorListAdapter: ActorListAdapter

    private var pageCount = 1

    override fun initView() {
        (activity as MainActivity).changeToolbar(false)

        binding.apply {
            this.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = (recyclerView.layoutManager as GridLayoutManager?)!!.findLastVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.page.value = ++pageCount
                    }
                }
            })

            this.recyclerView.run {
                actorListAdapter = ActorListAdapter(mutableListOf()).apply {
                    listener = object: ActorListAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            actorListAdapter.getItem(position).run {
                                findNavController().navigate(
                                    ActorListFragmentDirections.actionActorListToActorDetail(arrayOf(
                                        this
                                    ))
                                )
                            }
                        }
                    }
                }
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = actorListAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = ActorListViewModelFactory(ActorListRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ActorListViewModel::class.java)

        viewModel.page.value = pageCount

        viewModel.page.observe(this) {
            viewModel.getPopularActors()
        }

        viewModel.actorList.observe(this) {
            actorListAdapter.update(it)
            //actorListAdapter.notifyDataSetChanged()
        }
    }
}
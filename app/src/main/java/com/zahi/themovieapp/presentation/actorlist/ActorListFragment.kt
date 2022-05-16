package com.zahi.themovieapp.presentation.actorlist

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zahi.themovieapp.R
import com.zahi.themovieapp.base.BaseFragment
import com.zahi.themovieapp.databinding.FragmentListBinding
import com.zahi.themovieapp.entity.ActorInfo
import com.zahi.themovieapp.presentation.main.MainActivity
import com.zahi.themovieapp.presentation.main.MainFragmentDirections
import com.zahi.themovieapp.presentation.main.WrapContentGridLayoutMangager
import com.zahi.themovieapp.repositories.ActorListRepository

class ActorListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    companion object {
        fun newInstance() = ActorListFragment()
    }

    private lateinit var viewModel: ActorListViewModel
    private lateinit var viewModelFactory: ActorListViewModelFactory
    private lateinit var actorListAdapter: ActorListAdapter

    private var actorList: MutableList<ActorInfo> = mutableListOf()

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
                actorListAdapter = ActorListAdapter(actorList).apply {
                    listener = object: ActorListAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            actorListAdapter.getItem(position).run {
                                findNavController().navigate(
                                    MainFragmentDirections.actionActorListToActorDetail(arrayOf(
                                        this
                                    ))
                                )
                            }
                        }
                    }
                }
                setHasFixedSize(true)
                layoutManager = WrapContentGridLayoutMangager(requireContext(), 2)
                adapter = actorListAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = ActorListViewModelFactory(ActorListRepository(requireContext()))
        viewModel = ViewModelProvider(this, viewModelFactory).get(ActorListViewModel::class.java)

        viewModel.page.value = pageCount

        viewModel.page.observe(this) {
            when ((activity as MainActivity).iconSelectedInBottomNav) {
                R.id.action_home -> viewModel.getPopularActors()
                R.id.action_favorite -> viewModel.getMyFavoriteActors()
            }
        }

        viewModel.actorList.observe(this) {
            actorListAdapter.update(it)
            actorList = it
        }
    }
}
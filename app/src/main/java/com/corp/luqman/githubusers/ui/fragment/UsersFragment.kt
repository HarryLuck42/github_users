package com.corp.luqman.githubusers.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.corp.luqman.githubusers.R
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.corp.luqman.githubusers.databinding.FragmentUsersBinding
import com.corp.luqman.githubusers.databinding.SearchUsersDialogBinding
import com.corp.luqman.githubusers.ui.adapter.UserAdapter
import com.corp.luqman.githubusers.utils.Const.USER_ID
import com.corp.luqman.githubusers.utils.Helpers
import com.corp.luqman.githubusers.utils.NetworkHelper
import com.corp.luqman.githubusers.utils.UiState
import com.corp.luqman.githubusers.utils.custom.CustomProgressDialog
import com.corp.luqman.githubusers.utils.custom.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var progressDialog : CustomProgressDialog

    private lateinit var binding: FragmentUsersBinding

    private lateinit var adapter : UserAdapter

    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater)
        setHasOptionsMenu(true)
        progressDialog = CustomProgressDialog(binding.root.context, getString(R.string.loading))
        initMenuOptions()
        with(binding){
            initAdapter()
            initObserver()
            initScroll()
        }
        refreshListMovie()
        return binding.root
    }

    private fun initMenuOptions() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.search_movies -> {
                        showDialogSearch(requireView())
                        return true
                    }

                    R.id.list_default -> {
                        refreshListMovie()
                        return true
                    }
                }

                return false
            }

        })
    }

    private fun refreshListMovie() {
        viewModel.clearList()
        viewModel.inputKeyword("")
        viewModel.getUserList()
    }


    private fun FragmentUsersBinding.initAdapter(){
        val layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        rvUsersNow.layoutManager = layoutManager
        rvUsersNow.setHasFixedSize(true)
        rvUsersNow.isFocusable = false
        rvUsersNow.addItemDecoration(GridSpacingItemDecoration(3, 30, false))
        rvUsersNow.visibility = View.VISIBLE
        tvNotFoundUsers.visibility = View.GONE
        ivNotFoundNow.visibility = View.GONE
        adapter = UserAdapter(viewModel.userList.value ?: mutableListOf(), object: UserAdapter.UserItemListener{
            override fun onClickItem(item: UserLocal) {
                Bundle().apply {
                    this.putInt(USER_ID, item.id)
                    findNavController().navigate(R.id.action_usersFragment_to_userDetailFragment, this)
                }

            }

        })
        adapter.notifyDataSetChanged()
        rvUsersNow.adapter = adapter
    }


    private fun FragmentUsersBinding.initObserver(){
        viewModel.usersState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading ->{
                    progressDialog.show()
                }
                is UiState.OnLoad ->{
                    viewModel.addList(it.data)
                    adapter.notifyItemRangeInserted(adapter.itemCount - 3, adapter.itemCount + it.data.size)
                    progressDialog.dismiss()
                    viewModel.startLoading()
                }
                is UiState.Refresh ->{
                    if (it.data.isEmpty()) {
                        rvUsersNow.visibility = View.GONE
                        ivNotFoundNow.visibility = View.VISIBLE
                        tvNotFoundUsers.visibility = View.VISIBLE
                    } else {
                        rvUsersNow.visibility = View.VISIBLE
                        ivNotFoundNow.visibility = View.GONE
                        tvNotFoundUsers.visibility = View.GONE
                    }
                    viewModel.addList(it.data)
                    adapter.notifyDataSetChanged()
                    progressDialog.dismiss()
                    viewModel.startLoading()
                }
                is UiState.Error ->{
                    val message = NetworkHelper().getErrorMessage(it.throwable)
                    Helpers.showGeneralOkDialog(
                        binding.root.context,
                        getString(R.string.perhatian),
                        message
                    )
                    progressDialog.dismiss()
                }
                else ->{
                    progressDialog.dismiss()
                }
            }
        }
    }

    private fun FragmentUsersBinding.initScroll(){
        rvUsersNow.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    val lastVisibleItem = (rvUsersNow.layoutManager!! as GridLayoutManager).findLastVisibleItemPosition()
                    val totalItemCount = (rvUsersNow.layoutManager!! as GridLayoutManager).itemCount
                    if(viewModel.keywordValue.value.isNullOrEmpty() && viewModel.isLoading && totalItemCount <= (lastVisibleItem + 1)){
                        viewModel.stopLoading()
                        val last = viewModel.userList.value?.maxBy { it.id }?.id
                        Log.d("Harry test", "last key: $last")
                        val filter = viewModel.userList.value?.filter { it.id == last }
                        if((filter?.size ?: 0) < 2){
                            last?.let {
                                viewModel.getUserList(since = last)
                            }
                        }
                    }

                }
            }
        })
    }

    private fun showDialogSearch(v : View){
        val binding = SearchUsersDialogBinding.inflate(LayoutInflater.from(v.context))
        val searchMovieDialog = Helpers.customViewDialog(v.context, R.layout.search_users_dialog, binding, true)
        var message = ""
        binding.etKeywordSearch.requestFocus()
        binding.btnSearch.setOnClickListener {
            val keyword = binding.etKeywordSearch.text.toString()
            if(keyword.isEmpty()){
                message += "Please input keyword search !"
            }else{
                viewModel.inputKeyword(keyword)
            }
            if(message.isEmpty()){
                viewModel.getUserList()
                searchMovieDialog.dismiss()
            }else{
                Helpers.showGeneralOkDialog(v.context, "Warning", message)
            }
        }

        searchMovieDialog.show {
            cancelable(true)
        }
    }


}
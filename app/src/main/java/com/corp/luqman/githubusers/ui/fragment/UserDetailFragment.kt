package com.corp.luqman.githubusers.ui.fragment

import android.os.Bundle
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
import androidx.lifecycle.Lifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.corp.luqman.githubusers.R
import com.corp.luqman.githubusers.data.models.response.UserDetail
import com.corp.luqman.githubusers.databinding.FragmentUserDetailBinding
import com.corp.luqman.githubusers.utils.Const.USER_ID
import com.corp.luqman.githubusers.utils.Helpers
import com.corp.luqman.githubusers.utils.NetworkHelper
import com.corp.luqman.githubusers.utils.UiState
import com.corp.luqman.githubusers.utils.custom.CustomProgressDialog
import com.corp.luqman.githubusers.utils.reformatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var progressDialog : CustomProgressDialog

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserDetailBinding.inflate(inflater)
        with(binding){
            initView()
            fetchData()
            initObserve()
        }
        return binding.root
    }

    private fun FragmentUserDetailBinding.initView(){

        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.reload -> {
                        fetchData()
                        return true
                    }
                }

                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        layoutDetailUser.visibility = View.INVISIBLE
        progressDialog = CustomProgressDialog(requireContext(), getString(R.string.loading))
        progressDialog.show()
    }

    private fun fetchData(){
        val id = arguments?.getInt(USER_ID)
        id?.let {
            viewModel.getUserList(it)
        }
    }

    private fun FragmentUserDetailBinding.initObserve(){
        viewModel.detailState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {
                    layoutDetailUser.visibility = View.INVISIBLE
                    progressDialog.show()
                }
                is UiState.Success -> {
                    bindData(it.data)
                    progressDialog.dismiss()
                }
                is UiState.Error ->{
                    val message = NetworkHelper().getErrorMessage(it.throwable)
                    Helpers.showGeneralOkDialog(
                        root.context,
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

    private fun FragmentUserDetailBinding.bindData(data: UserDetail){
        layoutDetailUser.visibility = View.VISIBLE
        tvTitleHeader.text = data.username
        tvDate.text = data.updatedAt?.reformatDate("yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy") ?: "-"
        textLocation.text = data.location ?: "-"
        tvName.text = data.name ?: "-"
        tvEmail.text = data.email ?: "-"
        tvCompany.text = data.company ?: "-"
        tvBio.text = data.bio ?: getString(R.string.empty_bio)
        tvFollowers.text = "${data.followers ?: 0}"
        tvFollowing.text = "${data.following ?: 0}"
        tvRepos.text = "${data.publicRepos ?: 0}"
        tvGists.text = "${data.publicGists ?: 0}"
        Glide.with(requireContext()).load(data.avatarUrl).placeholder(R.drawable.loading_animation)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken))
            .into(ivUserDetail)
    }
}
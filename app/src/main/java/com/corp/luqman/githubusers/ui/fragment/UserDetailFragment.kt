package com.corp.luqman.githubusers.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.corp.luqman.githubusers.R
import com.corp.luqman.githubusers.databinding.FragmentUserDetailBinding

class UserDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserDetailBinding.inflate(inflater)
        with(binding){
            binding.initView()
        }
        return binding.root
    }

    private fun FragmentUserDetailBinding.initView(){
        layoutDetailMovie.visibility = View.GONE
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
package com.corp.luqman.githubusers.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corp.luqman.githubusers.data.models.response.User
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.corp.luqman.githubusers.data.repository.UsersRepository
import com.corp.luqman.githubusers.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository): ViewModel() {
//    var isLoading = false
//
//    fun stopLoading() {
//        isLoading = false
//    }
//
//    fun startLoading(){
//        isLoading = true
//    }
//    init {
//        stopLoading()
//    }

    private val _userList = MutableLiveData<MutableList<UserLocal>>(mutableListOf())
    val userList : LiveData<MutableList<UserLocal>>
        get() = _userList

    fun addList(data: List<UserLocal>){
        _userList.value?.addAll(data)
    }

    fun clearList(){
        _userList.value?.clear()
    }

    val usersState = MutableLiveData<UiState<List<UserLocal>>>()
    val searchUsersState = MutableLiveData<UiState<List<UserLocal>>>()

    fun getUserList(since: Int? = null){
        if(since == null){
            usersState.value = UiState.Loading()
        }

        viewModelScope.launch {
            try {
                val results = repository.getUsers(since ?: 0).await()
                val list = arrayListOf<UserLocal>()
                for(result in results){
                    val data = result.convert()
                    saveData(data)
                    list.add(data)
                }
                if(since != null){
                    usersState.postValue(UiState.OnLoad(list))
                }else{
                    clearList()
                    usersState.postValue(UiState.Refresh(list))
                }

            }catch (e: Exception){
                usersState.postValue(UiState.Error(e))
            }
        }
    }

    private fun saveData(data: UserLocal){
        viewModelScope.launch {
            val current = repository.getUsersById(data.id)
            if(current.isNullOrEmpty()){
                repository.insertUser(data)
            }
        }
    }
}
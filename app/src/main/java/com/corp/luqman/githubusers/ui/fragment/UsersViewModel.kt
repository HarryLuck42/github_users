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
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    private val _userList = MutableLiveData<MutableList<UserLocal>>(mutableListOf())
    val userList: LiveData<MutableList<UserLocal>>
        get() = _userList

    fun addList(data: List<UserLocal>) {
        _userList.value?.addAll(data)
    }

    fun clearList() {
        _userList.value?.clear()
    }

    private val _keywordValue = MutableLiveData<String?>()
    val keywordValue
        get() = _keywordValue

    fun inputKeyword(value: String) {
        _keywordValue.value = value
    }

    val usersState = MutableLiveData<UiState<List<UserLocal>>>()
    val searchUsersState = MutableLiveData<UiState<List<UserLocal>>>()

    fun getUserList(since: Int? = null) {

        val key = _keywordValue.value
        if (since == null) {
            clearList()
            usersState.value = UiState.Loading()
        }

        viewModelScope.launch {
            try {
                if (key.isNullOrEmpty()) {
                    val results = repository.getUsers(since ?: 0).await()
                    val list = arrayListOf<UserLocal>()
                    for (result in results) {
                        val data = result.convert()
                        saveData(data)
                        list.add(data)
                    }
                    if (since != null) {
                        usersState.postValue(UiState.OnLoad(list))
                    } else {

                        usersState.postValue(UiState.Refresh(list))
                    }
                } else {
                    clearList()
                    val results = repository.getUsers()
                    usersState.postValue(UiState.Refresh(results?.filter {
                        (it.username?.contains(
                            key
                        ) ?: false) || (it.name?.contains(key) ?: false)
                    } ?: mutableListOf()))
                }


            } catch (e: Exception) {
                usersState.postValue(UiState.Error(e))
            }
        }
    }

    private fun saveData(data: UserLocal) {
        viewModelScope.launch {
            val current = repository.getUsersById(data.id)
            if (current.isNullOrEmpty()) {
                repository.insertUser(data)
            }
        }
    }
}
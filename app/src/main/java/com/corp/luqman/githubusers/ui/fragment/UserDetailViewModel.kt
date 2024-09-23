package com.corp.luqman.githubusers.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corp.luqman.githubusers.data.models.response.UserDetail
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.corp.luqman.githubusers.data.repository.UsersRepository
import com.corp.luqman.githubusers.utils.UiState
import com.corp.luqman.githubusers.utils.isMoreThan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {


    private val _detailState = MutableLiveData<UiState<UserLocal>>()

    val detailState : LiveData<UiState<UserLocal>>
        get() = _detailState

    fun getUserList(id: Int) {

        _detailState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val current = repository.getUsersById(id)?.firstOrNull()
                if(current == null){
                    onSuccess(id)
                }else if(current.name.isNullOrEmpty()){
                    onSuccess(id)
                }else{
                    _detailState.postValue(UiState.Success(current))

                }


            } catch (e: Exception) {
                _detailState.postValue(UiState.Error(e))
            }
        }
    }

    private suspend fun onSuccess(id: Int) {
        val result = repository.getUserDetail(id).await()
        saveData(result)
        _detailState.postValue(UiState.Success(result.convert()))
    }

    private fun saveData(result: UserDetail){
        viewModelScope.launch {
            val current = repository.getUsersById(result.id ?: 0)?.firstOrNull()
            if(current != null){
                val new = result.updatedAt ?: ""
                val old = current.updatedAt ?: ""

                if(new.isMoreThan(old)){
                    repository.insertUser(result.convert())
                }
            }else{
                repository.insertUser(result.convert())
            }
        }
    }
}
package com.example.tubemate.fragments.fullScreen

import androidx.lifecycle.ViewModel
import com.example.tubemate.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullScreenViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

//    private val _user: MutableLiveData<Resource<User>> = MutableLiveData()
//    val user: LiveData<Resource<User>>
//        get() = _user
//
//    fun getUser(
//        userId: Int
//    ) = viewModelScope.launch {
//        _user.value = Resource.Loading
//        _user.value = repository.getUser(userId)
//    }
//


}
package com.example.encoratask.featureGetList.viewmodels

import androidx.lifecycle.*
import com.example.encoratask.core.common.Result
import com.example.encoratask.core.model.Character
import com.example.encoratask.featureGetList.usercases.GetListUserCase
import kotlinx.coroutines.launch

class ItemListViewModel(private val userCase: GetListUserCase) :ViewModel() {

    private val _list = MutableLiveData<List<Character>>()
    val list = _list

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private var page = 1

    fun getListCharacters(){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = userCase.invoke(page)){
                is Result.Success ->{
                    page++
                    _dataLoading.postValue(false)
                    _list.postValue(result.data)
                }
                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    class ItemListViewModelFactory(private val userCase: GetListUserCase) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItemListViewModel(userCase) as T
        }
    }
}
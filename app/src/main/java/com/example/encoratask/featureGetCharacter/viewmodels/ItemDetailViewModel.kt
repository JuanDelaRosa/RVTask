package com.example.encoratask.featureGetCharacter.viewmodels

import androidx.lifecycle.*
import com.example.encoratask.core.common.Result
import com.example.encoratask.core.model.Character
import com.example.encoratask.featureGetCharacter.usercases.GetCharacterUserCase
import kotlinx.coroutines.launch

class ItemDetailViewModel(private val userCase: GetCharacterUserCase) :ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getCharacterFromService(){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val id = character.value?.id ?: 0
            when(val result = userCase.invoke(id)){
                is Result.Success ->{
                    _dataLoading.postValue(false)
                    _character.postValue(result.data)
                }
                is Result.Error ->{
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    fun getCharacterFromExtra(c: Character?){
        c?.let {
            _character.postValue(it)
        }
    }

    class ItemDetailViewModelFactory(private val userCase: GetCharacterUserCase) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItemDetailViewModel(userCase) as T
        }
    }
}
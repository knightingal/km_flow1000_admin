package org.nanking.km_flow1000_admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _greetingList = MutableStateFlow<List<String>>(listOf())
    val greetingList: StateFlow<List<String>> get() = _greetingList

    init {
        viewModelScope.launch {
            Greeting().greet().collect { phrase ->
                _greetingList.update { list -> list + phrase }

            }
        }
    }
}

class Flow1000AlbumPageViewModel : ViewModel() {

    val rocketComponent = Flow1000RequestWrap()
    fun downloadSectionById(id: Long) {
        viewModelScope.launch {
            rocketComponent.downloadSectionById(id)
        }
    }

}
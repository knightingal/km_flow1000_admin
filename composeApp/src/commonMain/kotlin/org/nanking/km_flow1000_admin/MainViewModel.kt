package org.nanking.km_flow1000_admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class Flow1000AlbumPageViewModel : ViewModel() {

    val flow1000RequestWrap = Flow1000RequestWrap()
    fun downloadSectionById(id: Long) {
        viewModelScope.launch {
            flow1000RequestWrap.subscribeSectionById(id)
        }
    }

}
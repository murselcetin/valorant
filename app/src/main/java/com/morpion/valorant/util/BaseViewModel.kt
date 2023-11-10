package com.morpion.valorant.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <T> request(
        flow: Flow<RestResult<T>>,
        onSuccess: ((data: T) -> Unit)? = null,
        onError: (suspend (t: Exception) -> Unit)? = null,
        onLoading: ((control: Boolean) -> Unit)? = null
    ) = viewModelScope.launch {
        flow.collect { result ->
            when (result) {
                is RestResult.Loading -> onLoading?.invoke(true)
                is RestResult.Success -> onSuccess?.invoke(result.data)
                is RestResult.Error -> {
                    onError?.invoke(result.exception)
                }
            }
        }
    }

    fun <T> requestLocal(
        flow: Flow<T>,
        onSuccess: ((data: T) -> Unit)? = null
    ) = viewModelScope.launch {
        flow.collect { result ->
            onSuccess?.invoke(result)
        }
    }
}
package com.abhi.workdoneapp.base

import com.abhi.workdoneapp.data.Task

sealed class UIState<out T>{
    data class Success<out T>(val list : T) : UIState<T>()
    data object Loading : UIState<Nothing>()
    data class Error(val message : String) : UIState<Nothing>()
}
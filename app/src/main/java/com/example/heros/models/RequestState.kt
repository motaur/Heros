package com.example.heros.models

sealed class RequestState {
    object NotStarted : RequestState()
    object Loading : RequestState()
    object Success: RequestState()
    object Empty : RequestState()
    object Error : RequestState()

}
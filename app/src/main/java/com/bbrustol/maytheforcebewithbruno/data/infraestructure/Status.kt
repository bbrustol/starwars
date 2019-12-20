package com.bbrustol.maytheforcebewithbruno.data.infraestructure

sealed class NetworkState

class DataNotAvailable : NetworkState()
class UnexpectedError : NetworkState()

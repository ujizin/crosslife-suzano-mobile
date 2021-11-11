package br.com.crosslife.commons.extensions.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ViewModelExtensions {

    operator fun <T> StateFlow<T>.invoke() = this as MutableStateFlow<T>
}

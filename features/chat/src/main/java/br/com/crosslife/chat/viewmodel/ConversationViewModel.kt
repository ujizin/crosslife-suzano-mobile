package br.com.crosslife.chat.viewmodel

import androidx.lifecycle.ViewModel
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.model.ServerError
import br.com.crosslife.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val repository: ConversationRepository
): ViewModel(), ViewModelExtensions {

    val conversation: StateFlow<ConversationViewState> = MutableStateFlow(ConversationViewState.Initial)

    fun getConversations() {

    }
}

sealed class ConversationViewState {
    object Initial: ConversationViewState()
    data class Error(val error: ServerError): ConversationViewState()
    data class Messages(val data: List<Conversation>): ConversationViewState()
}
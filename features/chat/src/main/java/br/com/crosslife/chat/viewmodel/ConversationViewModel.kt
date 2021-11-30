package br.com.crosslife.chat.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.repository.ConversationRepository
import br.com.crosslife.navigation.Screen.Conversation.USERNAME_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val repository: ConversationRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ViewModelExtensions {

    val conversation: StateFlow<ConversationViewState> =
        MutableStateFlow(ConversationViewState.Initial)

    val senderUsername = savedStateHandle.get<String>(USERNAME_ARG).orEmpty()

    init {
        getConversations()
    }

    fun getConversations(senderUsername: String = this.senderUsername) {
        repository.getConversations(senderUsername)
            .onStart { conversation().value = ConversationViewState.Initial }
            .catch { conversation().value = ConversationViewState.Error }
            .onEach { conversation().value = ConversationViewState.Messages(it) }
            .launchIn(viewModelScope)
    }

    fun sendMessage(sentence: String) {
        viewModelScope.launch {
            repository.postConversation(senderUsername, sentence)
        }
    }
}

sealed class ConversationViewState {
    object Initial : ConversationViewState()
    object Error : ConversationViewState()
    data class Messages(val data: List<Conversation>) : ConversationViewState()
}
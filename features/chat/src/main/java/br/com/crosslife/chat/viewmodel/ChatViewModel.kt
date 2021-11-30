package br.com.crosslife.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crosslife.commons.extensions.viewmodel.ViewModelExtensions
import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository,
) : ViewModel(), ViewModelExtensions {

    val chatViewState: StateFlow<ChatViewState> = MutableStateFlow(ChatViewState.Initial)

    init {
        syncChat()
    }

    fun syncChat() {
        repository.isUserAdmin()
            .onStart { chatViewState().value = ChatViewState.Initial }
            .catch { chatViewState().value = ChatViewState.Error }
            .map { isAdmin ->
                when {
                    isAdmin -> {
                        val conversation = repository.getUsersConversations().firstOrNull().orEmpty()
                        chatViewState().value = ChatViewState.Instructor(conversation)
                    }
                    else -> chatViewState().value = ChatViewState.User
                }
            }
            .launchIn(viewModelScope)
    }
}

sealed class ChatViewState {
    object Initial : ChatViewState()
    object Error : ChatViewState()
    data class Instructor(val conversation: List<Conversation>) : ChatViewState()
    object User : ChatViewState()
}

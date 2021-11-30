package br.com.crosslife.domain.repository

import br.com.crosslife.domain.model.Conversation
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun isUserAdmin(): Flow<Boolean>
    fun getUsersConversations(): Flow<List<Conversation>>
}

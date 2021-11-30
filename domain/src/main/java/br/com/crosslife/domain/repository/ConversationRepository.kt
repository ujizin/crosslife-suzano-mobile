package br.com.crosslife.domain.repository

import br.com.crosslife.domain.model.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    fun getConversations(senderUsername: String): Flow<List<Conversation>>
    suspend fun postConversation(senderUsername: String, sentence: String)
}

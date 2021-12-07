package br.com.crosslife.data.repositories.chat

import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.repository.ChatRepository
import br.com.crosslife.local.store.user.UserStore
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class ChatRepositoryImpl(
    private val userStore: UserStore,
    private val firebaseStore: DatabaseReference,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ChatRepository {

    override fun isUserAdmin() = userStore.isAdmin()

    override fun getUsersConversations() = channelFlow {
        val user = userStore.getUsername().first()
        firebaseStore.child(user).get()
            .addOnSuccessListener { snapshot ->
                val entry = snapshot.value as Map<*, *>
                val conversations = entry.entries.mapNotNull { map ->
                    val mapperValue = (map.value as? List<*>)?.lastOrNull() as? Map<*, *>
                    val name = map.key as? String
                    val message = mapperValue?.get(MESSAGE_PARAM) as? String

                    return@mapNotNull when {
                        !name.isNullOrBlank() && !message.isNullOrBlank() -> {
                            Conversation(name, message)
                        }
                        else -> null
                    }
                }

                trySend(conversations)
            }
            .addOnCanceledListener { cancel() }
        awaitClose()
    }.flowOn(dispatcher)

    companion object {
        private const val MESSAGE_PARAM = "message"
    }
}

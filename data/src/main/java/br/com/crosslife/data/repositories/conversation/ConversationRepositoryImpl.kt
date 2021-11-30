package br.com.crosslife.data.repositories.conversation

import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.repository.ConversationRepository
import br.com.crosslife.local.store.user.UserStore
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class ConversationRepositoryImpl(
    private val userStore: UserStore,
    private val firebaseStore: DatabaseReference,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ConversationRepository {

    private val conversations = mutableListOf<Conversation>()

    private var username: String? = null

    private var isAdmin: Boolean = false

    private suspend fun initConversations(senderUsername: String) {
        username = userStore.getUsername().first()
        isAdmin = userStore.isAdmin().first()

        getCollection(senderUsername).get()
            .addOnSuccessListener { snapshot ->
                snapshot.getMessage {
                    conversations.addAll(it)
                }
            }
    }

    override fun getConversations(senderUsername: String) = channelFlow {
        initConversations(senderUsername)
        getCollection(senderUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getMessage { trySend(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                cancel()
            }
        })
        awaitClose()
    }.flowOn(dispatcher)

    override suspend fun postConversation(senderUsername: String, sentence: String) {
        withContext(dispatcher) {
            conversations.add(Conversation(username.orEmpty(), sentence))
            getCollection(senderUsername).setValue(conversations)
        }
    }

    private fun getCollection(senderUsername: String) = firebaseStore.getChildren(senderUsername)

    private fun DatabaseReference.getChildren(senderUsername: String): DatabaseReference {
        val children = getUserChildren(senderUsername)
        return child(children.first).child(children.second)
    }

    private fun getUserChildren(senderUsername: String) = when {
        isAdmin -> Pair(username.orEmpty(), senderUsername)
        else -> Pair(senderUsername, username.orEmpty())
    }

    private fun DataSnapshot.getMessage(block: (List<Conversation>) -> Unit) {
        val messages = (value as? List<*>)?.convertToConversation()
        conversations.clear()
        conversations.addAll(messages.orEmpty())
        block(conversations.toList())
    }
}

private const val USER_PARAM = "user"
private const val MESSAGE_PARAM = "message"

private fun List<*>.convertToConversation(): List<Conversation> {
    val map = filterIsInstance<Map<String, String>>()
    return map.map {
        Conversation(
            user = it[USER_PARAM].orEmpty(),
            message = it[MESSAGE_PARAM].orEmpty(),
        )
    }
}

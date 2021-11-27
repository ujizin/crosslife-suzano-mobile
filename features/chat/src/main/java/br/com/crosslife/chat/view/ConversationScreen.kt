package br.com.crosslife.chat.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.chat.viewmodel.ConversationViewModel
import br.com.crosslife.chat.viewmodel.ConversationViewState
import br.com.crosslife.commons.components.Loading
import br.com.crosslife.commons.components.error.Error
import br.com.crosslife.commons.components.input.TextField
import br.com.crosslife.commons.components.topbar.ScaffoldTopbar
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Conversation

@ExperimentalAnimationApi
@Composable
fun NavController.ConversationScreen(viewModel: ConversationViewModel = hiltViewModel()) {
    val inputState = remember { mutableStateOf("") }
    val conversations by rememberFlowWithLifecycle(flow = viewModel.conversation).collectAsState(
        initial = ConversationViewState.Initial
    )

    ScaffoldTopbar(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = Space.BORDER),
                contentAlignment = Alignment.BottomCenter,
                propagateMinConstraints = true,
            ) {
                when (val state: ConversationViewState = conversations) {
                    ConversationViewState.Initial -> Loading()
                    is ConversationViewState.Error -> Error {
                        viewModel.getConversations()
                    }
                    is ConversationViewState.Messages -> ConversationColumn(state.data)
                }
            }
            TextField(state = inputState, modifier = Modifier
                .fillMaxWidth()
                .padding(Space.BORDER))
        }
    }
}

@Composable
private fun ConversationColumn(conversations: List<Conversation>) {
    LazyColumn {
        items(conversations) { conversation ->
            Conversation(conversation)
        }
    }
}

@Composable
private fun Conversation(conversation: Conversation) {

}
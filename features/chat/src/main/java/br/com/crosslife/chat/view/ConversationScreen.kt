package br.com.crosslife.chat.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.chat.R
import br.com.crosslife.chat.viewmodel.ConversationViewModel
import br.com.crosslife.chat.viewmodel.ConversationViewState
import br.com.crosslife.commons.components.Loading
import br.com.crosslife.commons.components.error.Error
import br.com.crosslife.commons.components.tabbar.Keyboard
import br.com.crosslife.commons.components.tabbar.keyboardAsState
import br.com.crosslife.commons.components.topbar.ScaffoldTopbar
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Gray
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Conversation

@ExperimentalAnimationApi
@Composable
fun NavController.ConversationScreen(
    viewModel: ConversationViewModel = hiltViewModel()
) {
    val inputState = remember { mutableStateOf("") }
    val conversations by rememberFlowWithLifecycle(flow = viewModel.conversation).collectAsState(
        initial = ConversationViewState.Initial
    )

    ScaffoldTopbar(title = viewModel.senderUsername, modifier = Modifier.fillMaxSize()) {
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
                    ConversationViewState.Error -> Error {
                        viewModel.getConversations()
                    }
                    is ConversationViewState.Messages -> ConversationUI(
                        state.data,
                        viewModel.senderUsername
                    )
                }
            }
            Row(
                Modifier.padding(Space.XXS),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = inputState.value,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.type_message).capitalize(),
                            style = MaterialTheme.typography.body1,
                            color = Gray,
                        )
                    },
                    onValueChange = { inputState.value = it },
                    modifier = Modifier
                        .weight(1F)
                        .padding(end = Space.XS)
                        .background(MaterialTheme.colors.surface, RoundedCornerShape(8.dp))
                )
                IconButton(
                    modifier = Modifier.background(MaterialTheme.colors.primary, CircleShape),
                    onClick = {
                        if (inputState.value.isNotBlank()) {
                            viewModel.sendMessage(inputState.value.trim())
                            inputState.value = ""
                        }
                    }) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "send",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Composable
private fun ConversationUI(conversations: List<Conversation>, username: String) {
    val lazyState = rememberLazyListState()
    val keyboardState by keyboardAsState()
    if (keyboardState == Keyboard.Opened) {
        ScrollToLastIndex(lazyState, conversations)
    }

    LazyColumn(
        state = lazyState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        items(conversations) { conversation ->
            Conversation(conversation, username)
        }
    }

    ScrollToLastIndex(lazyState, conversations)
}

@Composable
private fun ScrollToLastIndex(lazyState: LazyListState, conversations: List<Conversation>) {
    if (conversations.isNotEmpty()) {
        val index by rememberUpdatedState(newValue = conversations.lastIndex)
        LaunchedEffect(index) {
            lazyState.scrollToItem(index)
        }
    }
}

@Composable
private fun Conversation(conversation: Conversation, senderUsername: String) {
    val (alignment, background) = when (senderUsername) {
        conversation.user -> Pair(Alignment.CenterStart, MaterialTheme.colors.surface)
        else -> Pair(Alignment.CenterEnd, MaterialTheme.colors.primary)
    }
    Box(
        modifier = Modifier
            .padding(Space.S)
            .fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Text(
            conversation.message, modifier = Modifier
                .background(background, RoundedCornerShape(4.dp))
                .padding(Space.XS)
        )
    }
}
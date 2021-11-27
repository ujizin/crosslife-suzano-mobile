package br.com.crosslife.chat.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import br.com.crosslife.commons.components.topbar.ScaffoldTopbar
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Gray
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.model.Instructor

@ExperimentalAnimationApi
@Composable
fun NavController.ConversationScreen(
    instructor: Instructor?,
    viewModel: ConversationViewModel = hiltViewModel()
) {
    val inputState = remember { mutableStateOf("") }
    val conversations by rememberFlowWithLifecycle(flow = viewModel.conversation).collectAsState(
        initial = ConversationViewState.Initial
    )

    ScaffoldTopbar(title = instructor?.name, modifier = Modifier.fillMaxSize()) {
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
                        viewModel.sendMessage(inputState.value)
                        inputState.value = ""
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
private fun ConversationColumn(conversations: List<Conversation>) {
    val lazyState = rememberLazyListState()
    LazyColumn(
        state = lazyState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
    ) {
        items(conversations) { conversation ->
            Conversation(conversation)
        }
    }

    if (conversations.isNotEmpty()) {
        LaunchedEffect(conversations.lastIndex) {
            lazyState.scrollToItem(conversations.lastIndex)
        }
    }
}

@Composable
private fun Conversation(conversation: Conversation) {
    Text(
        conversation.message,
        modifier = Modifier
            .padding(Space.S)
            .background(MaterialTheme.colors.primary, RoundedCornerShape(4.dp))
            .padding(Space.XS)
    )
}
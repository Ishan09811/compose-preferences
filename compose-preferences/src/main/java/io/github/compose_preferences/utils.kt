package io.github.compose_preferences

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.safeCombinedClickable(
    debounceTime: Long = 500L,
    onClick: () -> Unit,
    onLongClick: () -> Unit
): Modifier = composed {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    this.combinedClickable(
        onClick = {
            val now = System.currentTimeMillis()
            if (now - lastClickTime > debounceTime) {
                lastClickTime = now
                onClick()
            }
        },
        onLongClick = {
            onLongClick()
        }
    )
}
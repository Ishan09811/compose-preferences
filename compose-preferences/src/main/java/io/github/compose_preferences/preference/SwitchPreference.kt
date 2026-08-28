package io.github.compose_preferences.preference

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.github.compose_preferences.R
import io.github.compose_preferences.core.PreferenceIcon
import io.github.compose_preferences.core.PreferenceSubtitle
import io.github.compose_preferences.core.PreferenceTitle
import io.github.compose_preferences.util.ComposePreview
import com.github.ishan09811.materialswitch.MaterialSwitch
import com.github.ishan09811.materialswitch.MaterialSwitchColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SwitchPreference(
    modifier: Modifier = Modifier,
    checked: Boolean,
    title: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    subtitle: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    switchColors: MaterialSwitchColors  = MaterialSwitchColors(
        MaterialTheme.colorScheme,
        SwitchDefaults.colors()
    ),
    onClick: (Boolean) -> Unit,
    onLongClick: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    val onValueUpdated: (Boolean) -> Unit = { newValue -> onClick(newValue) }
    RegularPreference(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingContent = {
            MaterialSwitch(
                checked = checked,
                onCheckedChange = { onValueUpdated(it) },
                enabled = enabled,
                colors = switchColors,
                interactionSource = interactionSource
            )
        },
        enabled = enabled,
        onClick = {
            scope.launch {
                val press = PressInteraction.Press(Offset.Zero)
                interactionSource.emit(press)
                delay(100.milliseconds)
                interactionSource.emit(PressInteraction.Release(press))
                onValueUpdated(!checked)
            }
        },
        onLongClick = onLongClick
    )
}

@Composable
fun SwitchPreference(
    modifier: Modifier = Modifier,
    checked: Boolean,
    title: String,
    leadingIcon: ImageVector? = null,
    subtitle: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    switchColors: MaterialSwitchColors  = MaterialSwitchColors(
        MaterialTheme.colorScheme,
        SwitchDefaults.colors()
    ),
    onClick: (Boolean) -> Unit,
    onLongClick: () -> Unit = {}
) {
    SwitchPreference(
        checked = checked,
        title = { PreferenceTitle(title = title) },
        leadingIcon = { PreferenceIcon(icon = leadingIcon) },
        modifier = modifier,
        subtitle = subtitle,
        enabled = enabled,
        switchColors = switchColors,
        onClick = onClick,
        onLongClick = onLongClick
    )
}

@PreviewLightDark
@Composable
private fun SwitchPreview() {
    ComposePreview {
        var switchState by remember { mutableStateOf(true) }
        SwitchPreference(
            checked = switchState,
            title = { Text("Enable Something") },
            subtitle = {
                PreferenceSubtitle(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                )
            },
            //leadingIcon = { Icon(painterResource(id = R.drawable.ic_build), contentDescription = "Build") },
            onClick = {
                switchState = it
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun SwitchDisabledPreview() {
    ComposePreview {
        var switchState by remember { mutableStateOf(true) }
        SwitchPreference(
            checked = switchState,
            title = { Text("Enable Something") },
            subtitle = {
                PreferenceSubtitle(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                )
            },
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_build),
                    contentDescription = "Build"
                )
            },
            enabled = false,
            onClick = {
                switchState = it
            }
        )
    }
}

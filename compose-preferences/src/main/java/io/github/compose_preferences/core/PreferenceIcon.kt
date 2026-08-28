package io.github.compose_preferences.core

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.compose_preferences.LocalPreferenceState
import io.github.compose_preferences.util.preferenceColor
import io.github.compose_preferences.util.sizeIn

@Composable
fun PreferenceIcon(
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    enabled: Boolean = _root_ide_package_.io.github.compose_preferences.LocalPreferenceState.current,
    contentDescription: String? = null,
    tint: Color = _root_ide_package_.io.github.compose_preferences.util.preferenceColor(
        enabled,
        LocalContentColor.current
    ),
) {
    if (icon != null) {
        Icon(
            imageVector = icon,
            modifier = modifier.sizeIn(minSize = 24.dp, maxSize = 48.dp),
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@Composable
fun PreferenceIcon(
    icon: Painter?,
    modifier: Modifier = Modifier,
    enabled: Boolean = _root_ide_package_.io.github.compose_preferences.LocalPreferenceState.current,
    contentDescription: String? = null,
    tint: Color = _root_ide_package_.io.github.compose_preferences.util.preferenceColor(
        enabled,
        LocalContentColor.current
    ),
) {
    if (icon != null) {
        Icon(
            painter = icon,
            modifier = modifier.sizeIn(minSize = 24.dp, maxSize = 48.dp),
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

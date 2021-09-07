package kiwi.orbit.compose.ui.controls

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.foundation.ContentEmphasis
import kiwi.orbit.compose.ui.foundation.LocalContentColor
import kiwi.orbit.compose.ui.foundation.LocalContentEmphasis
import kiwi.orbit.compose.ui.foundation.LocalTextStyle

@Composable
public fun CheckboxField(
    checked: Boolean,
    onCheckedChange: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    description: (@Composable () -> Unit)? = null,
    label: @Composable ColumnScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val selectable = if (onCheckedChange != null) {
        Modifier
            .triStateToggleable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = enabled,
                onClick = onCheckedChange,
                state = if (checked) ToggleableState.On else ToggleableState.Off,
            )
    } else {
        Modifier
    }
    val layoutDirection = LocalLayoutDirection.current
    Row(
        modifier = modifier
            .then(selectable)
            .padding(
                start = contentPadding.calculateStartPadding(layoutDirection),
                end = contentPadding.calculateEndPadding(layoutDirection),
            ),
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            modifier = Modifier.padding(
                top = (contentPadding.calculateTopPadding() - 3.dp).coerceAtLeast(0.dp),
                end = 10.dp
            ),
            enabled = enabled,
            interactionSource = interactionSource,
        )
        val topPadding = contentPadding.calculateTopPadding().coerceAtLeast(3.dp)
        val bottomPadding = contentPadding.calculateBottomPadding()
        Column(Modifier.padding(top = topPadding, bottom = bottomPadding)) {
            CompositionLocalProvider(
                LocalTextStyle provides OrbitTheme.typography.title4,
                LocalContentColor provides OrbitTheme.colors.content.normal,
                LocalContentEmphasis provides if (enabled) ContentEmphasis.Normal else ContentEmphasis.Disabled,
            ) {
                label()
            }
            if (description != null) {
                CompositionLocalProvider(
                    LocalTextStyle provides OrbitTheme.typography.bodySmall,
                    LocalContentColor provides OrbitTheme.colors.content.normal,
                    LocalContentEmphasis provides if (enabled) ContentEmphasis.Minor else ContentEmphasis.Disabled,
                ) {
                    description()
                }
            }
        }
    }
}

package nl.jaysh.standing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun StandingRow(
    modifier: Modifier = Modifier,
    position: String,
    team: String? = null,
    icon: (@Composable () -> Unit)? = null,
    statistics: List<String>,
    onClick: (() -> Unit)? = null,
) {
    val rowModifier = if (onClick == null) {
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 20.dp))
            .padding(vertical = 8.dp, horizontal = 4.dp)
    } else {
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 20.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 4.dp)
    }

    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RowHeading(
            position = position,
            icon = icon,
            team = team,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp, alignment = Alignment.End),
            content = {
                statistics.forEach { statistic ->
                    RowDetailText(text = statistic)
                }
            }
        )
    }
}

@Composable
private fun RowHeading(
    position: String,
    icon: @Composable() (() -> Unit)?,
    team: String?,
) {
    Row(
        modifier = Modifier.width(220.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = position, style = MaterialTheme.typography.labelMedium)

        icon?.invoke()

        if (team != null) {
            Text(text = team, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
private fun RowDetailText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodySmall,
) {
    Text(modifier = modifier.width(32.dp), text = text, style = style)
}
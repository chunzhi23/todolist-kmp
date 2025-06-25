package org.kharis.todolist.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.kharis.todolist.theme.AppColor
import org.kharis.todolist.theme.LocalAppColors

@Composable
fun RoundedRoleButton(
    modifier: Modifier = Modifier,
    colorRole: AppColor,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val colors = LocalAppColors.current

    Box(
        modifier =
            modifier
                .height(40.dp)
                .clip(RoundedCornerShape(100))
                .background(colors.bg(colorRole))
                .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

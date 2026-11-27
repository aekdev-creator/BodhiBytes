package dev.alex.bodhibytes.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val UnconditionedLightColorScheme = lightColorScheme(
    primary = Color(0xFF47608B),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE7EDF6),
    onPrimaryContainer = Color(0xFF1A2942),
    secondary = Color(0xFF566378),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFEBEEF3),
    onSecondaryContainer = Color(0xFF1F2735),
    background = Color(0xFFF3F5F8),
    onBackground = Color(0xFF161A22),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF161A22),
    surfaceVariant = Color(0xFFF8F9FB),
    onSurfaceVariant = Color(0xFF5A6475),
    outline = Color(0xFFC2CBD8),
    outlineVariant = Color(0xFFD9DFE8)
)

val UnconditionedDarkColorScheme = darkColorScheme(
    primary = Color(0xFF9CB4DE),
    onPrimary = Color(0xFF14243D),
    primaryContainer = Color(0xFF283A57),
    onPrimaryContainer = Color(0xFFDCE6F7),
    secondary = Color(0xFFBAC4D6),
    onSecondary = Color(0xFF242E3D),
    secondaryContainer = Color(0xFF2C3442),
    onSecondaryContainer = Color(0xFFDCE2ED),
    background = Color(0xFF101318),
    onBackground = Color(0xFFE5E9F0),
    surface = Color(0xFF171B22),
    onSurface = Color(0xFFE5E9F0),
    surfaceVariant = Color(0xFF1E242E),
    onSurfaceVariant = Color(0xFF9CA6B8),
    outline = Color(0xFF434D5E),
    outlineVariant = Color(0xFF2D3542)
)

val CardShape = RoundedCornerShape(14.dp)

@Composable
fun cardBorder(): BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)

enum class ReadingFontSize(
    val key: String,
    val label: String,
    val bodyFontSize: TextUnit,
    val titleFontSize: TextUnit
) {
    SMALL("small", "Small", 13.sp, 15.sp),
    DEFAULT("default", "Default", 14.sp, 16.sp),
    LARGE("large", "Large", 16.sp, 18.sp),
    EXTRA_LARGE("extra_large", "Extra Large", 18.sp, 21.sp);

    companion object {
        fun fromKey(key: String?): ReadingFontSize {
            return entries.find { it.key == key } ?: DEFAULT
        }
    }
}

enum class ReadingLineSpacing(
    val key: String,
    val label: String,
    val lineHeight: TextUnit,
    val paragraphSpacing: Dp
) {
    COMPACT("compact", "Compact", 18.sp, 8.dp),
    COMFORTABLE("comfortable", "Comfortable", 21.sp, 12.dp),
    SPACIOUS("spacious", "Spacious", 25.sp, 16.dp);

    companion object {
        fun fromKey(key: String?): ReadingLineSpacing {
            return entries.find { it.key == key } ?: COMFORTABLE
        }
    }
}

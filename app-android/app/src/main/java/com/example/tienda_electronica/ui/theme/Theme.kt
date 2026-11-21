
package com.example.tienda_electronica.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AzulElectrico,
    secondary = AzulCyan,
    tertiary = Violeta,

    background = AzulNoche,
    surface = AzulSuperficie,

    onPrimary = Blanco,
    onSecondary = AzulNoche,
    onTertiary = Blanco,

    onBackground = Blanco,
    onSurface = Blanco
)

@Composable
fun Tienda_electronicaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}


package com.example.tienda_electronica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tienda_electronica.ui.home.HomeScreen
import com.example.tienda_electronica.ui.theme.Tienda_electronicaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Tienda_electronicaTheme {
                HomeScreen()
            }
        }
    }
}

// Vista previa del Home
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Tienda_electronicaTheme {
        HomeScreen()
    }
}

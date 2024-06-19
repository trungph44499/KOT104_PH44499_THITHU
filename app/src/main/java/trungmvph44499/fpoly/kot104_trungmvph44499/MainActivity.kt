package trungmvph44499.fpoly.kot104_trungmvph44499

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import trungmvph44499.fpoly.kot104_trungmvph44499.ui.theme.KOT104_TRUNGMVPH44499Theme
import trungmvph44499.fpoly.kot104_trungmvph44499.ui.theme.screen.ScreenNavigation

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KOT104_TRUNGMVPH44499Theme {
                Scaffold(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                    ScreenNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KOT104_TRUNGMVPH44499Theme {
        Greeting("Android")
    }
}
package com.example.udemy_compose_effect

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.udemy_compose_effect.ui.theme.Udemy_Compose_EffectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Udemy_Compose_EffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    var round by remember { mutableStateOf(1) }
    var total by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {

            }
//            LaunchedEffect(key1 = round) {
//                snackbarHostState.showSnackbar(
//                    message = "Please start counting round $round",
//                    duration = SnackbarDuration.Short
//                )
//            }
        }
    )

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        Text(
            text = "Total is $total",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.DarkGray,
            modifier = modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
            },
            placeholder = {
                Text("Enter value here")
            },
            textStyle = TextStyle(
                color = Color.LightGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            label = {
                Text("New Count")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = modifier
                .fillMaxWidth()
        )

        Button(
            onClick = {
                total += input.toDouble()

                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Count Updated",
                        duration = SnackbarDuration.Short
                    )
                }


                if (total > 300) {
                    total = 0.0
                    input = ""
                    round++
                }
            },
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Count",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
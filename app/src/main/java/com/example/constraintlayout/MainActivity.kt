package com.example.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import FuelCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FuelCalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun FuelCalculatorScreen() {
    var gasolinePrice by remember { mutableStateOf("") }
    var alcoholPrice by remember { mutableStateOf("") }
    var useAlternativeCalc by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }

    LaunchedEffect(gasolinePrice, alcoholPrice, useAlternativeCalc) {
        result = if (gasolinePrice.isNotEmpty() && alcoholPrice.isNotEmpty()) {
            calculateBestFuel(
                gasolinePrice.toFloatOrNull() ?: 0f,
                alcoholPrice.toFloatOrNull() ?: 0f,
                useAlternativeCalc
            )
        } else {
            "Digite os valores para calcular"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Álcool ou Gasolina?",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = gasolinePrice,
            onValueChange = { gasolinePrice = it },
            label = { Text("Preço da gasolina") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = alcoholPrice,
            onValueChange = { alcoholPrice = it },
            label = { Text("Preço do álcool") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Cálculo a ${if (useAlternativeCalc) "75%" else "70%"}",
                color = MaterialTheme.colorScheme.onBackground
            )
            Switch(
                checked = useAlternativeCalc,
                onCheckedChange = { useAlternativeCalc = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private fun calculateBestFuel(gasolinePrice: Float, alcoholPrice: Float, useAlternativeCalc: Boolean): String {
    if (gasolinePrice <= 0 || alcoholPrice <= 0) {
        return "Por favor, insira valores válidos"
    }

    val ratio = alcoholPrice / gasolinePrice
    
    // Se estiver calculando a 70%
    if (!useAlternativeCalc) {
        return when {
            ratio > 0.70f -> "Melhor usar GASOLINA"
            else -> "Melhor usar ÁLCOOL"
        }
    } 
    // Se estiver calculando a 75%
    else {
        return when {
            ratio > 0.75f -> "Melhor usar GASOLINA"
            else -> "Melhor usar ÁLCOOL"
        }
    }
}


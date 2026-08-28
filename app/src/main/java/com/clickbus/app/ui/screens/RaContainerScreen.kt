package com.clickbus.app.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clickbus.app.ui.theme.ClickbusOrange
import com.clickbus.app.ui.theme.ClickbusPurple
import com.clickbus.app.viewmodel.AuthViewModel

@Composable
fun RaContainerScreen(
    viewModel: AuthViewModel,
    onExit: () -> Unit
) {
    var hasCameraPermission by remember { mutableStateOf(false) }
    val fontSizeMultiplier = viewModel.fontSizeMultiplier
    
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Scaffold(
        topBar = {
            Surface(
                color = ClickbusPurple,
                contentColor = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ArrowUpward, 
                        contentDescription = null, 
                        modifier = Modifier.size(32.dp),
                        tint = ClickbusOrange
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "15 metros",
                            fontSize = (24 * fontSizeMultiplier).sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Siga reto até o Portão 08",
                            fontSize = (16 * fontSizeMultiplier).sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /* Simular Ajuda */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.SupportAgent, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ajuda Presencial")
                    }
                    FloatingActionButton(
                        onClick = onExit,
                        containerColor = Color.White,
                        contentColor = ClickbusPurple,
                        shape = CircleShape
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Encerrar")
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.DarkGray) // Simula o Viewfinder
        ) {
            if (hasCameraPermission) {
                // Placeholder para o Módulo RA
                Text(
                    text = "[ CÂMERA ATIVA - MÓDULO RA ]",
                    color = Color.Green,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                // Mini-Mapa 2D (Refinado como Card Flutuante)
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .size(140.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        // Simulação de mapa com círculos
                        Canvas(modifier = Modifier.size(100.dp)) {
                            drawCircle(color = ClickbusPurple.copy(alpha = 0.1f), radius = size.minDimension / 2)
                            drawCircle(color = ClickbusPurple, radius = 20f, center = Offset(size.width * 0.7f, size.height * 0.3f))
                        }
                        
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.MyLocation, contentDescription = null, tint = ClickbusPurple, modifier = Modifier.size(20.dp))
                            Text("Ponto de Interesse", fontSize = 8.sp, fontWeight = FontWeight.Bold)
                            Text("Portão 08", fontSize = 10.sp, color = ClickbusPurple)
                        }
                    }
                }
            } else {
                Text(
                    text = "Permissão de câmera necessária para navegação RA.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

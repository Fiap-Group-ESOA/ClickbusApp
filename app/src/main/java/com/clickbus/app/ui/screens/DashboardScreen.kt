package com.clickbus.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clickbus.app.ui.theme.ClickbusBlack
import com.clickbus.app.ui.theme.ClickbusOrange
import com.clickbus.app.ui.theme.ClickbusPurple
import com.clickbus.app.ui.theme.ClickbusPurpleDark
import com.clickbus.app.ui.theme.ClickbusWhite
import com.clickbus.app.viewmodel.AuthViewModel

@Composable
fun DashboardScreen(
    viewModel: AuthViewModel,
    onStartNavigation: () -> Unit
) {
    val userName = viewModel.currentUser?.nome ?: "Viajante"
    val fontSizeMultiplier = viewModel.fontSizeMultiplier
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Olá, $userName!",
                    fontSize = (24 * fontSizeMultiplier).sp,
                    fontWeight = FontWeight.Bold,
                    color = ClickbusPurple
                )
                Text(
                    text = "Para onde vamos hoje?",
                    fontSize = (14 * fontSizeMultiplier).sp,
                    color = Color.Gray
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ClickbusPurple.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = ClickbusPurple)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Busca de Passagens (Funcionalidade Chave)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ClickbusWhite),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Comprar Passagens",
                    fontWeight = FontWeight.Bold,
                    fontSize = (16 * fontSizeMultiplier).sp,
                    color = ClickbusBlack
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Origem") },
                    leadingIcon = { Icon(Icons.Default.RadioButtonUnchecked, null, tint = ClickbusPurple) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Destino") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, null, tint = ClickbusOrange) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Ida") },
                        leadingIcon = { Icon(Icons.Default.CalendarToday, null) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        readOnly = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Volta") },
                        leadingIcon = { Icon(Icons.Default.CalendarMonth, null) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        readOnly = true
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ClickbusPurple)
                ) {
                    Text("BUSCAR PASSAGENS")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Sua próxima viagem",
            fontWeight = FontWeight.Bold,
            fontSize = (18 * fontSizeMultiplier).sp
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        // Card da Passagem (Estilo Boarding Pass)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ClickbusWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                // Topo do bilhete
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ClickbusPurple)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("CLICKBUS EXPRESS", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
                    Text("BILHETE: #CB9872", color = Color.White, fontSize = 10.sp)
                }

                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("SAO", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                            Text("São Paulo", fontSize = 12.sp, color = Color.Gray)
                        }
                        
                        // Linha decorativa
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Canvas(modifier = Modifier.fillMaxWidth().height(2.dp)) {
                                drawLine(
                                    color = ClickbusPurple,
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, 0f),
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                                )
                            }
                            Icon(Icons.Default.DirectionsBus, null, tint = ClickbusPurple, modifier = Modifier.size(24.dp).background(Color.White))
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("RIO", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
                            Text("Rio de Janeiro", fontSize = 12.sp, color = Color.Gray)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("POLTRONA", fontSize = 10.sp, color = Color.Gray)
                            Text("12A", fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("EMBARQUE", fontSize = 10.sp, color = Color.Gray)
                            Text("14:15", fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("PARTIDA", fontSize = 10.sp, color = Color.Gray)
                            Text("14:30", fontWeight = FontWeight.Bold)
                        }
                    }

                    Divider(modifier = Modifier.padding(vertical = 16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, null, tint = ClickbusOrange, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Terminal Tietê - Portão 08",
                            fontWeight = FontWeight.Medium,
                            fontSize = (14 * fontSizeMultiplier).sp
                        )
                    }
                }
                
                // Botão de Destaque para RA dentro do card
                Button(
                    onClick = onStartNavigation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ClickbusPurple)
                ) {
                    Icon(Icons.Default.ViewInAr, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "VER NO MAPA (RA)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

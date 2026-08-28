package com.clickbus.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clickbus.app.ui.theme.ClickbusOrange
import com.clickbus.app.ui.theme.ClickbusPurple
import com.clickbus.app.viewmodel.AuthViewModel

@Composable
fun SupportScreen(viewModel: AuthViewModel) {
    val fontSizeMultiplier = viewModel.fontSizeMultiplier
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Central de Assistência",
            fontSize = (28 * fontSizeMultiplier).sp,
            fontWeight = FontWeight.Bold,
            color = ClickbusPurple
        )

        Spacer(modifier = Modifier.height(8.dp))
        
        // Badge Wi-Fi
        Surface(
            color = Color(0xFFE8F5E9),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Wifi, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Wi-Fi Disponível: Clickbus_Free_Wifi", color = Color(0xFF4CAF50), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Atalhos Rápidos
        Text("Serviços no Terminal", fontWeight = FontWeight.Bold, fontSize = (18 * fontSizeMultiplier).sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SupportShortcutCard(modifier = Modifier.weight(1f), icon = Icons.Default.Search, label = "Achados", multiplier = fontSizeMultiplier)
            SupportShortcutCard(modifier = Modifier.weight(1f), icon = Icons.Default.Info, label = "Informações", multiplier = fontSizeMultiplier)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SupportShortcutCard(modifier = Modifier.weight(1f), icon = Icons.Default.Accessible, label = "Rampas", multiplier = fontSizeMultiplier)
            SupportShortcutCard(modifier = Modifier.weight(1f), icon = Icons.Default.Wc, label = "Banheiros", multiplier = fontSizeMultiplier)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // FAQ Accordion
        Text("Perguntas Frequentes", fontWeight = FontWeight.Bold, fontSize = (18 * fontSizeMultiplier).sp)
        Spacer(modifier = Modifier.height(12.dp))
        
        FaqItem("Como solicito auxílio para embarque?", "Você pode solicitar pelo botão 'Ajuda Presencial' ou ir ao balcão da ClickBus no setor A.", fontSizeMultiplier)
        FaqItem("Onde encontro o elevador?", "Existem elevadores nos setores B e D, próximos às escadas rolantes principais.", fontSizeMultiplier)
        FaqItem("Perdi minha bagagem, o que fazer?", "Dirija-se ao setor de 'Achados e Perdidos' no subsolo ou use o atalho acima.", fontSizeMultiplier)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Ligar para suporte */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ClickbusPurple)
        ) {
            Icon(Icons.Default.SupportAgent, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("FALAR COM APOIO PRESENCIAL", fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SupportShortcutCard(modifier: Modifier, icon: ImageVector, label: String, multiplier: Float) {
    Card(
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = ClickbusPurple)
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, fontSize = (12 * multiplier).sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun FaqItem(question: String, answer: String, multiplier: Float) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F3F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(question, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = (14 * multiplier).sp)
                Icon(if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, null)
            }
            AnimatedVisibility(visible = expanded) {
                Text(answer, modifier = Modifier.padding(top = 8.dp), fontSize = (13 * multiplier).sp, color = Color.DarkGray)
            }
        }
    }
}

package com.clickbus.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clickbus.app.ui.theme.ClickbusPurple
import com.clickbus.app.ui.theme.ClickbusPurpleDark
import com.clickbus.app.viewmodel.AuthState
import com.clickbus.app.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var emailOrCpf by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    
    val authState = viewModel.authState
    val fontSizeMultiplier = viewModel.fontSizeMultiplier

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onLoginSuccess()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo ClickBus Estilizada
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(ClickbusPurple, ClickbusPurpleDark)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CB",
                fontSize = (40 * fontSizeMultiplier).sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ClickBus",
            fontSize = (32 * fontSizeMultiplier).sp,
            fontWeight = FontWeight.Bold,
            color = if (viewModel.isHighContrast) Color.Black else ClickbusPurple
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Bem-vindo de volta!",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = (MaterialTheme.typography.headlineSmall.fontSize.value * fontSizeMultiplier).sp
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = emailOrCpf,
            onValueChange = { emailOrCpf = it },
            label = { Text("E-mail") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = authState is AuthState.Error
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = authState is AuthState.Error
        )

        if (authState is AuthState.Error) {
            Text(
                text = authState.message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (authState is AuthState.Loading) {
            CircularProgressIndicator(color = ClickbusPurple)
        } else {
            Button(
                onClick = { viewModel.login(emailOrCpf, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.isHighContrast) Color.Black else ClickbusPurple
                )
            ) {
                Text(
                    "Entrar",
                    fontSize = (18 * fontSizeMultiplier).sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        // Acessibilidade
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { viewModel.decreaseFontSize() }) {
                Icon(Icons.Default.TextFormat, contentDescription = "Diminuir fonte", modifier = Modifier.size(18.dp))
            }
            Text("Acessibilidade", fontWeight = FontWeight.Medium)
            IconButton(onClick = { viewModel.increaseFontSize() }) {
                Icon(Icons.Default.TextFormat, contentDescription = "Aumentar fonte", modifier = Modifier.size(28.dp))
            }
            Switch(
                checked = viewModel.isHighContrast,
                onCheckedChange = { viewModel.toggleHighContrast() },
                thumbContent = { Icon(Icons.Default.Contrast, null, Modifier.size(16.dp)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text(
                "Não tem uma conta? Cadastre-se",
                color = if (viewModel.isHighContrast) Color.Black else ClickbusPurple,
                fontSize = (14 * fontSizeMultiplier).sp
            )
        }
    }
}

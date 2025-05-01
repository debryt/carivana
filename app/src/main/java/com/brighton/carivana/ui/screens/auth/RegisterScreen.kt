package com.brighton.carivana.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brighton.carivana.model.User
import com.brighton.carivana.navigation.ROUT_LOGIN
import com.brighton.carivana.repository.UserRepository
import com.brighton.carivana.viewmodel.AuthViewModel
import com.brighton.carivana.viewmodel.AuthViewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.brighton.carivana.data.UserDatabase
import com.brighton.carivana.ui.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: AuthViewModel, // Change from AuthViewModel to NavHostController
    onRegisterSuccess: NavHostController,
    function: () -> Unit
) {
    val userDao = UserDatabase.getDatabase(LocalContext.current).userDao()
    val userRepository = UserRepository(userDao)
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(userRepository))

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var businessName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var profilePictureUri by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Your Account", fontSize = 40.sp, fontFamily = FontFamily.Cursive)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Other fields here...

        Button(
            onClick = {
                when {
                    name.isBlank() || email.isBlank() || phoneNumber.isBlank() || password.isBlank() ||
                            confirmPassword.isBlank() || businessName.isBlank() || profilePictureUri.isBlank() -> {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }

                    password != confirmPassword -> {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }

                    password.length != 4 || !password.all { it.isDigit() } -> {
                        Toast.makeText(context, "Password must be exactly 4 digits", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        val user = User(
                            name = name,
                            email = email,
                            phoneNumber = phoneNumber,
                            password = password,
                            businessName = businessName,
                            profilePictureUri = profilePictureUri
                        )
                        authViewModel.registerUser(user)
                        onRegisterSuccess(navController.navigate(ROUT_LOGIN))
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Register", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate(ROUT_LOGIN) }) {
            Text("Already have an account? Login")
        }
    }
}

private fun ColumnScope.onRegisterSuccess(unit: Any) {}

private fun AuthViewModel.navigate(string: String) {}
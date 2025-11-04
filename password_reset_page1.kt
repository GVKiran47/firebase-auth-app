package com.example.authentication_page

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Password_Reset_Page1(navController: NavController) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color.White)
    ) {
        Button(
            onClick = {},
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.padding(start = 90.dp, top = 80.dp)
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text("Password Reset Page", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.padding(top = 16.dp, start = 50.dp, end = 50.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter Registered Gmail") },
                shape = RoundedCornerShape(10.dp)
            )
        }

        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "If this email is registered, a reset link has been sent.", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            },
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(start = 50.dp, top = 10.dp)
                .height(40.dp),
            shape = RoundedCornerShape(6.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text("Send Reset Link")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(Routes.home_page) // back to login
            },
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(start = 50.dp, top = 10.dp)
                .height(40.dp),
            shape = RoundedCornerShape(6.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text("Back to Login")
        }
    }
}


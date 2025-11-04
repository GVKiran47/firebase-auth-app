package com.example.authentication_page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication_page.ui.theme.Authentication_pageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()
            NavHost(
                navController= navController,
                startDestination= Routes.home_page,
                builder={
                    composable(Routes.home_page){
                        Home_page(navController)
                    }
                    composable(Routes.sign_up_page){
                        Sign_Up_Page(navController)
                    }
                    composable(Routes.password_reset_page1){
                        Password_Reset_Page1(navController)
                    }
                    composable(Routes.auth_successful){
                        auth_successful(navController)
                    }

                }
            )

        }
        }
    }


//@Preview(showBackground = true)
//@Composable
//fun Home_page_Preview() {
//    Authentication_pageTheme {
//        Home_page()
//    }
//}
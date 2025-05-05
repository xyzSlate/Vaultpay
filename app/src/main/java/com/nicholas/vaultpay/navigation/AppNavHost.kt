package com.nicholas.vaultpay.navigation


import AuthViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicholas.vaultpay.data.UserDatabase
import com.nicholas.vaultpay.repository.UserRepository
import com.nicholas.vaultpay.ui.screens.confirmation.ConfirmationScreen
import com.nicholas.vaultpay.ui.screens.about.AboutScreen
import com.nicholas.vaultpay.ui.screens.home.HomeScreen
import com.nicholas.vaultpay.ui.screens.splash.SplashScreen
import com.nicholas.vaultpay.ui.screens.auth.LoginScreen
import com.nicholas.vaultpay.ui.screens.auth.RegisterScreen
import com.nicholas.vaultpay.ui.screens.sendmoney.SendMoneyScreen
import com.nicholas.vaultpay.ui.screens.transactions.TransactionsScreen
import com.nicholas.vaultpay.ui.screens.confirmation.ConfirmationScreen

@Composable
fun AppNavHost(
    modifier: Modifier.Companion = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController = navController, userName = "Jane Doe")
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable (ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_SEND_MONEY) {
            SendMoneyScreen(navController)
        }
        composable (ROUT_TRANSACTIONS){
            TransactionsScreen(navController)
        }

        composable(
            "confirmation_screen/{recipient}/{amount}",
            arguments = listOf(navArgument("recipient") { type = NavType.StringType }, navArgument("amount") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipient = backStackEntry.arguments?.getString("recipient") ?: ""
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            ConfirmationScreen(navController = navController, recipient = recipient, amount = amount)
        }





        //composable("home") { HomeScreen(navController) }

        composable("send_money") { SendMoneyScreen(navController) }
        composable("transactions") { TransactionsScreen(navController) }
        //composable("pay_bills") { PayBillsScreen(navController) }
        //composable("profile") { ProfileScreen(navController) }

        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(ROUT_REGISTER) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_HOME) {
                    popUpTo(ROUT_LOGIN) { inclusive = true }
                }
            }
        }
    }
}









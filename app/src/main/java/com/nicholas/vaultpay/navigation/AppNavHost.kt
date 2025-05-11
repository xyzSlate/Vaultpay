package com.nicholas.vaultpay.navigation


import AuthViewModel
import PayBillsScreen
import SendMoneyScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nicholas.vaultpay.data.UserDatabase
import com.nicholas.vaultpay.repository.UserRepository
import com.nicholas.vaultpay.ui.screens.about.AboutScreen
import com.nicholas.vaultpay.ui.screens.auth.LoginScreen
import com.nicholas.vaultpay.ui.screens.auth.RegisterScreen
import com.nicholas.vaultpay.ui.screens.changepassword.ChangePasswordScreen
import com.nicholas.vaultpay.ui.screens.confirmation.ConfirmationScreen
import com.nicholas.vaultpay.ui.screens.confirmation2.PaymentConfirmationScreen
import com.nicholas.vaultpay.ui.screens.home.HomeScreen
import com.nicholas.vaultpay.ui.screens.notifications.NotificationsScreen
import com.nicholas.vaultpay.ui.screens.profile.ProfileScreen
import com.nicholas.vaultpay.ui.screens.splash.SplashScreen
import com.nicholas.vaultpay.ui.screens.support.SupportScreen
import com.nicholas.vaultpay.ui.screens.transactions.TransactionsScreen
import com.nicholas.vaultpay.viewmodel.HomeViewModel


@Composable
fun AppNavHost(
    modifier: Modifier.Companion = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SUPPORT
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = "home/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "User"
            HomeScreen(navController = navController, username = username)
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
        composable("home") {
            val homeViewModel: HomeViewModel = viewModel() // Create HomeViewModel here
            HomeScreen(navController = navController, username = "User", homeViewModel = homeViewModel)
        }
        composable("pay_bills") {
            val homeViewModel: HomeViewModel = viewModel() // Create HomeViewModel here
            PayBillsScreen(navController = navController, homeViewModel = homeViewModel)
        }

        composable(ROUTE_SUPPORT) {
            SupportScreen()
        }
        composable(ROUTE_NOTIFICATIONS) {
            NotificationsScreen()
        }



        //Send Money Confirmation

        composable(
            "confirm/{recipient}/{amount}",
            arguments = listOf(navArgument("recipient") { type = NavType.StringType }, navArgument("amount") { type = NavType.StringType })
        ) { backStackEntry ->
            val recipient = backStackEntry.arguments?.getString("recipient") ?: ""
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            ConfirmationScreen(navController = navController, recipient = recipient, amount = amount)
        }

        //Payment Confirmation

        composable(
            "(\"$ROUT_CONFIRM2/{billType}/{account}/{amount}",
            arguments = listOf(
                navArgument("billType") { defaultValue = "Bill" },
                navArgument("account") { defaultValue = "-" },
                navArgument("amount") { defaultValue = "0" }
            )
        ) { backStackEntry ->
            val billType = backStackEntry.arguments?.getString("billType") ?: ""
            val account = backStackEntry.arguments?.getString("account") ?: ""
            val amount = backStackEntry.arguments?.getString("amount") ?: ""

            PaymentConfirmationScreen(navController, billType, account, amount)
        }
        composable("change_password") {
            ChangePasswordScreen(navController)
        }
        composable (ROUT_PROFILE){
            ProfileScreen(navController)
        }






        //composable("home") { HomeScreen(navController) }

        composable("send_money") { SendMoneyScreen(navController) }
        composable("transactions") { TransactionsScreen(navController) }
        //composable("pay_bills") { PayBillScreen(navController) }
        composable("profile") { ProfileScreen(navController) }

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












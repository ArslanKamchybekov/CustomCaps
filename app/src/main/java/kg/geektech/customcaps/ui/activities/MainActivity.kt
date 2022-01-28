package kg.geektech.customcaps.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        checkUser()
        initBottomView()
        bottomViewVisibility()
    }

    private fun checkUser() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(R.id.mainFragment)
        } else {
            navController.navigate(R.id.authFragment)
        }
    }

    private fun initBottomView() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileFragmentBottom -> navController.navigate(R.id.profileFragment)
                R.id.mainFragmentBottom -> navController.navigate(R.id.mainFragment)
                R.id.favoritesFragmentBottom -> navController.navigate(R.id.favoritesFragment)
            }
            true
        }
    }

    private fun bottomViewVisibility() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.authFragment ||
                destination.id == R.id.signInFragment ||
                destination.id == R.id.signUpFragment ||
                destination.id == R.id.newPasswordFragment ||
                destination.id == R.id.capFragment
            ) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    }
}
package kg.geektech.customcaps.presentation.fragments.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.tvUser.setOnClickListener {
            navigateTo(R.id.userFragment)
        }
        binding.tvLogout.setOnClickListener {
            setAlertDialog()
        }
        binding.tvOrders.setOnClickListener {
            navigateTo(R.id.ordersFragment)
        }
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }

    private fun setAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Выйти из аккаунта")
            setMessage("Вы точно хотите выйти?")
            setPositiveButton("Да") { _, _ ->
                navigateTo(R.id.authFragment)
            }
            setNegativeButton("Нет", null)
            show()
        }
    }
}
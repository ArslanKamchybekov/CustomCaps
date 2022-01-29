package kg.geektech.customcaps.ui.fragments.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etNumber.text.isEmpty() || binding.etPassword.text.isEmpty() || binding.etPasswordConfirm.text.isEmpty()) {
                Toast.makeText(context, "Fill up all fields", Toast.LENGTH_SHORT).show()
            } else if (binding.etPassword.length() < 8 || binding.etPasswordConfirm.length() < 8) {
                binding.etPassword.error = "Password must have at least 8 characters"
            } else if (binding.etPassword.text.toString() != binding.etPasswordConfirm.text.toString()) {
                Toast.makeText(requireContext(), "Password fields do not match", Toast.LENGTH_SHORT)
                    .show()
            } else {
                signUp()
            }
        }
        binding.tvSignIn.setOnClickListener {
            navigateTo(R.id.signInFragment)
        }
    }

    private fun signUp() {
        navigateTo(R.id.mainFragment)
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
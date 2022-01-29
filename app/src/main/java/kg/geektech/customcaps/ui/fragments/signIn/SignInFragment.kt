package kg.geektech.customcaps.ui.fragments.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        checkText()
        binding.btnGoogle.setOnClickListener {
        }
        binding.btnFacebook.setOnClickListener {
        }
        binding.btnSignIn.setOnClickListener {
            if (binding.etNumber.text.toString().isEmpty() || binding.etPassword.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(context, "Fill up all fields", Toast.LENGTH_SHORT).show()
            } else {
                navigateTo(R.id.mainFragment)
            }
        }
        binding.tvSignUp.setOnClickListener {
            navigateTo(R.id.signUpFragment)
        }
        binding.tvForgot.setOnClickListener {
            navigateTo(R.id.newPasswordFragment)
        }
    }

    private fun checkText() {
        binding.etNumber.addTextChangedListener { number ->
            val password = binding.etPassword.text.toString().trim()
            val number = number.toString().trim()
            binding.btnSignIn.isEnabled = password.isNotEmpty() && number.isNotEmpty()
        }
        binding.etPassword.addTextChangedListener { password ->
            val password = password.toString().trim()
            val number = binding.etNumber.text.toString().trim()
            binding.btnSignIn.isEnabled = password.isNotEmpty() && number.isNotEmpty()
        }
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }

}
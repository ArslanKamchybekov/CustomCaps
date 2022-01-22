package kg.geektech.customcaps.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener{
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.signUpFragment)
        }
        binding.btnSignIn.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.signInFragment)
        }
    }
}
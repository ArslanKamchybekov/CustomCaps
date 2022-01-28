package kg.geektech.customcaps.ui.fragments.signIn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGoogle()
        initListeners()
        googleAuth = FirebaseAuth.getInstance()
    }

    private fun initGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun initListeners() {
        checkText()
        binding.btnGoogle.setOnClickListener {
            googleSignIn()
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

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().signOut()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        googleAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    navigateTo(R.id.mainFragment)
                    Toast.makeText(requireContext(), "You successfully entered", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT)
                        .show()
                }
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

    companion object {
        const val RC_SIGN_IN = -1
    }
}
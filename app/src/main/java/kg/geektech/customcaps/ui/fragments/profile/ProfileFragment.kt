package kg.geektech.customcaps.ui.fragments.profile

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGoogle()
        googleAuth = FirebaseAuth.getInstance()
        initListeners()
    }

    private fun initGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
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

    private fun googleSignOut() {
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(requireContext(), "Вы вышли из аккаунта", LENGTH_SHORT).show()
        navigateTo(R.id.authFragment)
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }

    private fun setAlertDialog() {
        val builder1 = AlertDialog.Builder(requireContext())
        builder1.setMessage("Вы точно хотите выйти ?")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Yes"
        ) { dialog: DialogInterface, _: Int ->
            googleSignInClient.signOut()
            googleSignOut()
            dialog.cancel()
        }
        builder1.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }
}
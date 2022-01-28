package kg.geektech.customcaps.ui.fragments.user

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kg.geektech.customcaps.databinding.FragmentUserBinding
import kg.geektech.customcaps.prefs.Prefs

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null
    private var prefs: Prefs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initProfileData()
    }

    private fun initProfileData() {
        prefs = Prefs(requireContext())

        if (prefs!!.stringName != null) {
            binding.etName.setText(prefs!!.stringName)
        }

        if (prefs!!.stringEmail != null) {
            binding.etEmail.setText(prefs!!.stringEmail)
        }

        if (prefs!!.stringNumber != null) {
            binding.etNumber.setText(prefs!!.stringNumber)
        }

        if (prefs!!.stringLocation != null) {
            binding.etLocation.setText(prefs!!.stringLocation)
        }

        if (prefs!!.stringImg != null) {
            Glide.with(requireContext()).load(Uri.parse(prefs!!.stringImg))
                .into(binding.ivProfile)
        }
    }

    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        activityResultLauncher = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val uri = result.data!!.data
                if (prefs != null) {
                    prefs!!.stringImg = uri.toString()
                }
                binding.ivProfile.setImageURI(uri)
            }
        }

        binding.ivProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResultLauncher!!.launch(intent)
        }
        binding.btnEditProfile.setOnClickListener {
            if (binding.ivProfile.toString().isNotEmpty()) {
                prefs!!.stringName = binding.etName.text.toString()
                prefs!!.stringEmail = binding.etEmail.text.toString()
                prefs!!.stringNumber = binding.etNumber.text.toString()
                prefs!!.stringLocation = binding.etLocation.text.toString()
            }
            Toast.makeText(
                requireContext(),
                "Вы успешно изменили данные профиля",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
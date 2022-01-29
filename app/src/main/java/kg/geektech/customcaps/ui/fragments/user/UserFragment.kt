package kg.geektech.customcaps.ui.fragments.user

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kg.geektech.customcaps.R
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
            showToast("Данные успешно изменены")
        }
    }

    private fun showToast(message: String) {
        val toastLayout = layoutInflater.inflate(
            R.layout.main_toast,
            activity?.findViewById(R.id.main_toast)
        )
        toastLayout.findViewById<TextView>(R.id.tv_main_toast).text = message
        val toast = Toast(requireContext())
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastLayout
        toast.show()
    }

}
package kg.geektech.customcaps.ui.fragments.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.caps.Caps
import kg.geektech.customcaps.databinding.FragmentCartBinding


class CartFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCartBinding

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.bind(inflater.inflate(R.layout.fragment_cart, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViews()
    }

    private fun initViews() {
        val adapter = CartAdapter(Caps().caps)
        binding.rvCart.adapter = adapter
    }

    private fun initListeners() {
        binding.btnBuy.setOnClickListener {
            val alert = View.inflate(requireContext(), R.layout.custom_alert_view, null)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(alert)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val btnToMainPage = alert.findViewById<MaterialButton>(R.id.btn_to_main_page)
            btnToMainPage.setOnClickListener {
                dialog.dismiss()
                dismiss()
                navigateTo(R.id.mainFragment)
            }

        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }


    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
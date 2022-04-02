package kg.geektech.customcaps.presentation.fragments.cap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentCapBinding
import kg.geektech.customcaps.domain.models.ModelCap
import kg.geektech.customcaps.domain.test_list.Caps
import kg.geektech.customcaps.presentation.fragments.mainPage.MainAdapter

@AndroidEntryPoint
class CapFragment : Fragment(R.layout.fragment_cap), MainAdapter.OnItemClick {

    private val binding: FragmentCapBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.ivFavoriteUnselected.setOnClickListener {
            binding.ivFavoriteUnselected.visibility = View.GONE
            binding.ivFavoriteSelected.visibility = View.VISIBLE
        }
        binding.btnAddToShoppingCart.setOnClickListener {
            showToast("Товар добавлен в корзину")
        }
    }

    private fun initViews() {
        val adapterCaps = MainAdapter(Caps().caps)
        binding.rvSimilar.adapter = adapterCaps
        adapterCaps.setOnItemClick(this)

        val adapterImages = ImageViewAdapter(images())
        binding.viewPager.adapter = adapterImages
        TabLayoutMediator(
            binding.tlImages,
            binding.viewPager
        ) { _, _ -> }.attach()
    }

    private fun images(): List<Int> {
        return mutableListOf(
            R.drawable.img_bestseller,
            R.drawable.img_cap
        )
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }

    @Suppress("SameParameterValue", "DEPRECATION")
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

    override fun onClick(cap: ModelCap) {
        navigateTo(R.id.capFragment)
    }
}
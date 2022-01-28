package kg.geektech.customcaps.ui.fragments.cap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayoutMediator
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.caps.Caps
import kg.geektech.customcaps.databinding.FragmentCapBinding
import kg.geektech.customcaps.ui.fragments.mainPage.MainAdapter

class CapFragment : Fragment() {

    private lateinit var binding: FragmentCapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCapBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            if (binding.ivFavoriteUnselected.isVisible) {
                binding.ivFavoriteUnselected.visibility = View.GONE
                binding.ivFavoriteSelected.visibility = View.VISIBLE
            } else {
                binding.ivFavoriteSelected.visibility = View.GONE
                binding.ivFavoriteUnselected.visibility = View.VISIBLE
            }

        }
        binding.btnAddToShoppingCart.setOnClickListener {

        }
    }


    private fun initViews() {
        val adapterCaps = MainAdapter(Caps().caps)
        binding.rvSimilar.adapter = adapterCaps
        adapterCaps.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateTo(R.id.capFragment)
            }
        })

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
}
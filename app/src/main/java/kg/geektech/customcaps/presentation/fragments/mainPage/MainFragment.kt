package kg.geektech.customcaps.presentation.fragments.mainPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentMainBinding
import kg.geektech.customcaps.domain.models.ModelCap
import kg.geektech.customcaps.domain.test_list.Caps
import kg.geektech.customcaps.presentation.fragments.sort.SortFragment

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.OnItemClick {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.tvSeeAllBest.setOnClickListener {
            navigateTo(R.id.seeAllFragment)
        }
        binding.tvSeeAllProm.setOnClickListener {
            navigateTo(R.id.seeAllFragment)
        }
        binding.ivSort.setOnClickListener {
            @Suppress("DEPRECATION")
            fragmentManager?.let { it1 -> SortFragment().show(it1, "tag") }
        }
    }

    private fun initViews() {
        val adapter = MainAdapter(Caps().caps)
        binding.rvBestsellers.adapter = adapter
        adapter.setOnItemClick(this)
        binding.rvPromotions.adapter = adapter
        adapter.setOnItemClick(this)
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }

    override fun onClick(cap: ModelCap) {
        navigateTo(R.id.capFragment)
    }
}
package kg.geektech.customcaps.presentation.fragments.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentFavoritesBinding
import kg.geektech.customcaps.domain.models.ModelCap
import kg.geektech.customcaps.domain.test_list.Caps
import kg.geektech.customcaps.presentation.fragments.mainPage.MainAdapter

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites), MainAdapter.OnItemClick {

    private val binding: FragmentFavoritesBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = MainAdapter(Caps().caps)
        binding.rvFavorites.adapter = adapter
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
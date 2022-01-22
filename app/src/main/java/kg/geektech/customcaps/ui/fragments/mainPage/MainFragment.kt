package kg.geektech.customcaps.ui.fragments.mainPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentMainBinding
import kg.geektech.customcaps.models.ModelCap

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initListeners()
    }

    private fun initListeners() {
        binding.tvSeeAllBest.setOnClickListener {
            navigateTo(R.id.seeAllFragment)
        }
        binding.tvSeeAllProm.setOnClickListener {
            navigateTo(R.id.seeAllFragment)
        }
    }

    private fun initRecyclerViews() {
        val adapter = MainAdapter(bestsellers())

        binding.rvBestsellers.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateTo(R.id.capFragment)
            }
        })
        binding.rvPromotions.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateTo(R.id.capFragment)
            }
        })
    }

    private fun bestsellers(): List<ModelCap> {
        val data = mutableListOf<ModelCap>()
        data.add(
            ModelCap(
                R.drawable.img_bestseller,
                "Adidas",
                "San Francisco Baseball",
                "3400 сом"
            )
        )
        data.add(ModelCap(R.drawable.img_bestseller, "Nike", "San Francisco Baseball", "4500 сом"))
        data.add(ModelCap(R.drawable.img_bestseller, "Vans", "San Francisco Baseball", "5800 сом"))
        data.add(
            ModelCap(
                R.drawable.img_bestseller,
                "Adidas",
                "San Francisco Baseball",
                "3600 сом"
            )
        )
        data.add(
            ModelCap(
                R.drawable.img_bestseller,
                "New Era",
                "San Francisco Baseball",
                "3300 сом"
            )
        )
        return data
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
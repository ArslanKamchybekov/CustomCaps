package kg.geektech.customcaps.ui.fragments.cap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentCapBinding
import kg.geektech.customcaps.models.ModelCap
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
        initRecyclerView()
        initListeners()
    }

    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initRecyclerView() {
        val adapter = MainAdapter(similar())
        binding.rvSimilar.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateTo(R.id.capFragment)
            }
        })
    }

    private fun similar(): List<ModelCap> {
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
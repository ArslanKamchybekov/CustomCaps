package kg.geektech.customcaps.ui.fragments.seeAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.caps.Caps
import kg.geektech.customcaps.databinding.FragmentSeeAllBinding
import kg.geektech.customcaps.ui.fragments.mainPage.MainAdapter

class SeeAllFragment : Fragment() {

    private lateinit var binding: FragmentSeeAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViews()
    }

    private fun initViews() {
        val adapter = MainAdapter(Caps().caps)
        binding.rvType.adapter = adapter
        adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateTo(R.id.capFragment)
            }
        })
    }

    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
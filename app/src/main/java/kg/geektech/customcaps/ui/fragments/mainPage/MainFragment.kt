package kg.geektech.customcaps.ui.fragments.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.caps.Caps
import kg.geektech.customcaps.databinding.FragmentMainBinding

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
            val bottomSheetDialog = BottomSheetDialog(
                requireContext(), R.style.BottomSheetDialogTheme
            )
            val bottomSheetView = LayoutInflater.from(requireContext())
                .inflate(
                    R.layout.bottom_sheet_sort,
                    binding.root.findViewById(R.id.bottomSheet)
                ) as LinearLayout?
            bottomSheetView?.findViewById<View>(R.id.iv_close)?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetView?.let { it1 -> bottomSheetDialog.setContentView(it1) }
            bottomSheetDialog.show()
        }
    }

    private fun initViews() {
        val adapter = MainAdapter(Caps().caps)
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

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
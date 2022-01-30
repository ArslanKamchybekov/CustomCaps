package kg.geektech.customcaps.ui.fragments.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentSortBinding

class SortFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSortBinding


    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.bind(inflater.inflate(R.layout.fragment_sort, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnCheap.setOnClickListener {
            binding.btnCheap.setBackgroundColor(resources.getColor(R.color.orange))
        }
        binding.btnExpensive.setOnClickListener {
            binding.btnExpensive.setBackgroundColor(resources.getColor(R.color.orange))
        }
        binding.btnNews.setOnClickListener {
            binding.btnNews.setBackgroundColor(resources.getColor(R.color.orange))
        }
        binding.btnPopular.setOnClickListener {
            binding.btnPopular.setBackgroundColor(resources.getColor(R.color.orange))
        }
        binding.btnApply.setOnClickListener {
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
}
package kg.geektech.customcaps.presentation.fragments.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.customcaps.R
import kg.geektech.customcaps.databinding.FragmentOrdersBinding
import kg.geektech.customcaps.domain.models.ModelOrder

class OrdersFragment : Fragment(R.layout.fragment_orders), OrderAdapter.OnItemClick {

    private val binding: FragmentOrdersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initViews() {
        val adapter = OrderAdapter(orderList())
        adapter.setOnItemClick(this)
        binding.rvOrders.adapter = adapter
    }

    private fun orderList(): ArrayList<ModelOrder> {
        val list = arrayListOf<ModelOrder>()
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        list.add(ModelOrder("85646493", "21.11.2022", "Доставлено"))
        return list
    }

    override fun onClick(order: ModelOrder) {
        navigateTo(R.id.orderDetailFragment)
    }

    private fun navigateTo(resId: Int) {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(resId)
    }
}
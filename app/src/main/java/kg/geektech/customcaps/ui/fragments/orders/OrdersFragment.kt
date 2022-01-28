package kg.geektech.customcaps.ui.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.models.ModelOrder
import kg.geektech.customcaps.databinding.FragmentOrdersBinding
import java.util.*

class OrdersFragment : Fragment(), OrderAdapter.OnItemClick {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = OrderAdapter(orderList())
        adapter.setOnItemClick(this)
        binding.rvOrders.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
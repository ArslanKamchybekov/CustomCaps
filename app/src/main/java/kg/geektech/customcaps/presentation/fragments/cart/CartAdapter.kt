package kg.geektech.customcaps.presentation.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.databinding.ListCartBinding
import kg.geektech.customcaps.domain.models.ModelCap

class CartAdapter(private val caps: ArrayList<ModelCap>) :
    RecyclerView.Adapter<CartAdapter.MineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineViewHolder {
        val binding =
            ListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MineViewHolder, position: Int) {
        holder.onBind(caps[position])
        holder.initListeners()
    }

    override fun getItemCount(): Int {
        return caps.size
    }

    inner class MineViewHolder(val binding: ListCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var count = 0
        fun onBind(cap: ModelCap) {
            cap.img?.let { binding.ivCap.setImageResource(it) }
            binding.tvBrand.text = cap.brand
            binding.tvModel.text = cap.model
//            binding.tvAmount.text = cap.amount.toString()
            binding.tvSize.text = cap.size
            val price = cap.price.toString() + " сом"
            binding.tvPrice.text = price
        }

        @Suppress("DEPRECATION")
        fun initListeners() {
            binding.ivRemove.setOnClickListener {
                caps.removeAt(position)
                notifyItemRemoved(position)
            }
            binding.ivAdd.setOnClickListener {
                count += 1
                binding.tvAmount.text = count.toString()
            }
            binding.ivDelete.setOnClickListener {
                if (count <= 0) {
                    binding.tvAmount.text = 0.toString()
                } else {
                    count -= 1
                    binding.tvAmount.text = count.toString()
                }
            }
        }
    }
}
package kg.geektech.customcaps.presentation.fragments.mainPage

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.databinding.ListCapsBinding
import kg.geektech.customcaps.domain.models.ModelCap

class MainAdapter(private val caps: List<ModelCap>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var onItemClick: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListCapsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(caps[position])
        holder.onItemClickListeners(caps[position])
    }

    override fun getItemCount(): Int = caps.size

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(private val binding: ListCapsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: ModelCap) {
            product.img?.let { binding.ivBestseller.setImageResource(it) }
            binding.tvBrand.text = product.brand
            binding.tvModel.text = product.model
            val price = product.price.toString() + " сом"
            binding.tvPrice.text = price
        }

        fun onItemClickListeners(cap: ModelCap) {
            if (cap.oldPrice != null) {
                val oldPrice = cap.oldPrice.toString() + " сом"
                binding.tvPriceOld.text = oldPrice
                binding.tvPriceOld.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvPriceOld.visibility = View.VISIBLE
            } else {
                binding.tvPriceOld.visibility = View.GONE
            }

            binding.ivFavoriteUnselected.setOnClickListener {
                if (binding.ivFavoriteSelected.visibility == View.GONE) {
                    binding.ivFavoriteUnselected.visibility = View.INVISIBLE
                    binding.ivFavoriteSelected.visibility = View.VISIBLE
                } else {
                    binding.ivFavoriteSelected.visibility = View.INVISIBLE
                    binding.ivFavoriteUnselected.visibility = View.VISIBLE
                }
            }
            itemView.setOnClickListener {
                onItemClick.onClick(cap)
            }
        }
    }

    interface OnItemClick {
        fun onClick(cap: ModelCap)
    }
}
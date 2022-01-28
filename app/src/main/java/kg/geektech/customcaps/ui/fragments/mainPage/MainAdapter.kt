package kg.geektech.customcaps.ui.fragments.mainPage

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.customcaps.data.models.ModelCap
import kg.geektech.customcaps.databinding.ListCapsBinding

class MainAdapter(private val caps: MutableList<ModelCap>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var click: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListCapsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, click)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        caps[position].img?.let { holder.img.setImageResource(it) }
        holder.brand.text = caps[position].brand
        holder.model.text = caps[position].model
        holder.price.text = caps[position].price
        holder.itemView.setOnLongClickListener {
            caps.removeAt(position)
            notifyItemRemoved(position)
            true
        }
        holder.favoriteUnselected.setOnClickListener {
            if (holder.favoriteUnselected.isVisible){
                holder.favoriteSelected.visibility = View.VISIBLE
                holder.favoriteUnselected.visibility = View.INVISIBLE
            }else{
                holder.favoriteSelected.visibility = View.INVISIBLE
                holder.favoriteUnselected.visibility = View.VISIBLE
            }

        }
        if (caps[position].oldPrice != null) {
            holder.priceOld.text = caps[position].oldPrice
            holder.priceOld.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    fun getItem(position: Int): ModelCap {
        return caps[position]
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int) {

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        click = listener

    }

    override fun getItemCount(): Int {
        return caps.size
    }

    class ViewHolder(
        binding: ListCapsBinding,
        listener: OnItemClickListener?,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val img: ImageView = binding.ivBestseller
        val brand: TextView = binding.tvBrand
        val model: TextView = binding.tvModel
        val price: TextView = binding.tvPrice
        val priceOld: TextView = binding.tvPriceOld
        val favoriteUnselected: ImageView = binding.ivFavoriteUnselected
        val favoriteSelected: ImageView = binding.ivFavoriteSelected

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }
}
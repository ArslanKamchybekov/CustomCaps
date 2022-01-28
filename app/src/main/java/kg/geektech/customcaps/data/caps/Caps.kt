package kg.geektech.customcaps.data.caps

import kg.geektech.customcaps.R
import kg.geektech.customcaps.data.models.ModelCap

class Caps {

    val caps: MutableList<ModelCap>
        get() {
            val data = mutableListOf<ModelCap>()
            data.add(
                ModelCap(
                    R.drawable.img_bestseller,
                    "Adidas",
                    "San Francisco Baseball",
                    "3400 сом"
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_cap,
                    "Nike",
                    "San Francisco Baseball",
                    "4500 сом"
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_bestseller,
                    "Vans",
                    "San Francisco Baseball",
                    "5800 сом"
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_cap,
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
}

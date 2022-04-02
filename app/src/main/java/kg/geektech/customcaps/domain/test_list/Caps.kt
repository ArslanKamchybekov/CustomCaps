package kg.geektech.customcaps.domain.test_list

import kg.geektech.customcaps.R
import kg.geektech.customcaps.domain.models.ModelCap

class Caps {

    val caps: ArrayList<ModelCap>
        get() {
            val data = arrayListOf<ModelCap>()
            data.add(
                ModelCap(
                    R.drawable.img_bestseller,
                    "Adidas",
                    "San Francisco Baseball",
                    3400
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_cap,
                    "Nike",
                    "San Francisco Baseball",
                    4000
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_bestseller,
                    "Vans",
                    "San Francisco Baseball",
                    5800
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_cap,
                    "Adidas",
                    "San Francisco Baseball",
                    3600, 4000
                )
            )
            data.add(
                ModelCap(
                    R.drawable.img_bestseller,
                    "New Era",
                    "San Francisco Baseball",
                    3300
                )
            )
            return data
        }
}

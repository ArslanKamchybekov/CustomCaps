package kg.geektech.customcaps.data.models

import java.io.Serializable

data class ModelCap(
    var img: Int? = null,
    var brand: String? = null,
    var model: String? = null,
    var price: String? = null,
    var oldPrice: String? = null,
) : Serializable

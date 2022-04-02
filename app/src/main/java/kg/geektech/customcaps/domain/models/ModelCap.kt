package kg.geektech.customcaps.domain.models

import java.io.Serializable

data class ModelCap(
    var img: Int? = null,
    var brand: String? = null,
    var model: String? = null,
    var price: Int? = null,
    var oldPrice: Int? = null,
    var amount: Int? = 0,
    var size: String? = null
) : Serializable

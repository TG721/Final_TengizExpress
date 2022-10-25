package com.tengizMKCorp.tengizExpress.ui.element.model

import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem


class CategoryUIItem(
    val id: Int,
    val name: String
)

fun convertDataCategoryToUI(category: CategoryItem): CategoryUIItem{
    return CategoryUIItem(category.api_category_id, category.category_name)
}
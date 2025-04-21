package com.example.coffee_shop.data

import com.example.coffee_shop.R
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.ItemCategory

const val BASE_URL = "https://www.amasmobile.com/caner/"

val itemsList = listOf(
    Item("1", R.string.item_espresso, R.string.desc_espresso, 5.99, BASE_URL + "espresso.png", ItemCategory.HOT_DRINKS),
    Item("2", R.string.item_cappuccino, R.string.desc_cappuccino, 6.49, BASE_URL + "cappuccino.png", ItemCategory.HOT_DRINKS),
    Item("3", R.string.item_latte, R.string.desc_latte, 6.99, BASE_URL + "latte.png", ItemCategory.HOT_DRINKS),
    Item("4", R.string.item_americano, R.string.desc_americano, 5.49, BASE_URL + "americano.png", ItemCategory.HOT_DRINKS),
    Item("5", R.string.item_mocha, R.string.desc_mocha, 7.29, BASE_URL + "mocha.png", ItemCategory.HOT_DRINKS),
    Item("6", R.string.item_hot_chocolate, R.string.desc_hot_chocolate, 5.79, BASE_URL + "hot_chocolate.png", ItemCategory.HOT_DRINKS),
    Item("7", R.string.item_chai_latte, R.string.desc_chai_latte, 6.59, BASE_URL + "chai_latte.png", ItemCategory.HOT_DRINKS),
    Item("8", R.string.item_matcha_latte, R.string.desc_matcha_latte, 6.89, BASE_URL + "matcha_latte.png", ItemCategory.HOT_DRINKS),
    Item("9", R.string.item_turkish_coffee, R.string.desc_turkish_coffee, 5.99, BASE_URL + "turkish_coffee.png", ItemCategory.HOT_DRINKS),
    Item("10", R.string.item_herbal_tea, R.string.desc_herbal_tea, 4.99, BASE_URL + "herbal_tea.png", ItemCategory.HOT_DRINKS),

    Item("11", R.string.item_iced_coffee, R.string.desc_iced_coffee, 5.99, BASE_URL + "iced_coffee.png", ItemCategory.COLD_DRINKS),
    Item("12", R.string.item_iced_latte, R.string.desc_iced_latte, 6.49, BASE_URL + "iced_latte.png", ItemCategory.COLD_DRINKS),
    Item("13", R.string.item_iced_americano, R.string.desc_iced_americano, 5.49, BASE_URL + "iced_americano.png", ItemCategory.COLD_DRINKS),
    Item("14", R.string.item_iced_mocha, R.string.desc_iced_mocha, 7.29, BASE_URL + "iced_mocha.png", ItemCategory.COLD_DRINKS),
    Item("15", R.string.item_cold_brew, R.string.desc_cold_brew, 6.99, BASE_URL + "cold_brew.png", ItemCategory.COLD_DRINKS),
    Item("16", R.string.item_iced_matcha_latte, R.string.desc_iced_matcha_latte, 6.89, BASE_URL + "iced_matcha_latte.png", ItemCategory.COLD_DRINKS),
    Item("17", R.string.item_iced_chai, R.string.desc_iced_chai, 6.59, BASE_URL + "iced_chai.png", ItemCategory.COLD_DRINKS),
    Item("18", R.string.item_lemonade, R.string.desc_lemonade, 4.99, BASE_URL + "lemonade.png", ItemCategory.COLD_DRINKS),
    Item("19", R.string.item_iced_peach_tea, R.string.desc_iced_peach_tea, 5.49, BASE_URL + "iced_peach_tea.png", ItemCategory.COLD_DRINKS),
    Item("20", R.string.item_berry_smoothie, R.string.desc_berry_smoothie, 6.79, BASE_URL + "berry_smothie.png", ItemCategory.COLD_DRINKS),

    Item("21", R.string.item_croissant, R.string.desc_croissant, 3.99, BASE_URL + "croissant.png", ItemCategory.FOODS),
    Item("22", R.string.item_bagel, R.string.desc_bagel, 2.99, BASE_URL + "bagel.png", ItemCategory.FOODS),
    Item("23", R.string.item_muffin, R.string.desc_muffin, 3.49, BASE_URL + "muffin.png", ItemCategory.FOODS),
    Item("24", R.string.item_toast, R.string.desc_toast, 2.49, BASE_URL + "toast.png", ItemCategory.FOODS),
    Item("25", R.string.item_cheesecake, R.string.desc_cheesecake, 5.99, BASE_URL + "cheesecake.png", ItemCategory.FOODS),
    Item("26", R.string.item_brownie, R.string.desc_brownie, 4.29, BASE_URL + "brownie.png", ItemCategory.FOODS),
    Item("27", R.string.item_sandwich, R.string.desc_sandwich, 6.49, BASE_URL + "sandwich.png", ItemCategory.FOODS),
    Item("28", R.string.item_quiche, R.string.desc_quiche, 5.79, BASE_URL + "quiche.png", ItemCategory.FOODS),
    Item("29", R.string.item_scone, R.string.desc_scone, 3.69, BASE_URL + "scone.png", ItemCategory.FOODS),
    Item("30", R.string.item_waffle, R.string.desc_waffle, 4.99, BASE_URL + "waffle.png", ItemCategory.FOODS),
    Item("31", R.string.item_pancakes, R.string.desc_pancakes, 5.49, BASE_URL + "pancake.png", ItemCategory.FOODS),
    Item("32", R.string.item_salad, R.string.desc_salad, 5.89, BASE_URL + "salad.png", ItemCategory.FOODS),
    Item("33", R.string.item_fruit_bowl, R.string.desc_fruit_bowl, 4.59, BASE_URL + "fruit_bowl.png", ItemCategory.FOODS),
    Item("34", R.string.item_donut, R.string.desc_donut, 2.99, BASE_URL + "donut.png", ItemCategory.FOODS),
    Item("35", R.string.item_avocado_toast, R.string.desc_avocado_toast, 6.29, BASE_URL + "avocado_toast.png", ItemCategory.FOODS)
)

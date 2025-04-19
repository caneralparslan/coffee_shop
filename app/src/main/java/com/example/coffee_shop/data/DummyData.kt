package com.example.coffee_shop.data

import com.example.coffee_shop.R
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.ItemCategory

val items = listOf(
    Item("1", "Espresso", "Strong and concentrated coffee shot", 5.99, R.drawable.espresso, ItemCategory.HOT_DRINKS),
    Item("2", "Cappuccino", "Espresso with steamed milk and milk foam", 6.49, R.drawable.cappuccino, ItemCategory.HOT_DRINKS),
    Item("3", "Latte", "Creamy coffee with steamed milk", 6.99, R.drawable.latte, ItemCategory.HOT_DRINKS),
    Item("4", "Americano", "Espresso diluted with hot water", 5.49, R.drawable.americano, ItemCategory.HOT_DRINKS),
    Item("5", "Mocha", "Espresso with chocolate and steamed milk", 7.29, R.drawable.mocha, ItemCategory.HOT_DRINKS),
    Item("6", "Hot Chocolate", "Rich cocoa drink with steamed milk", 5.79, R.drawable.hot_chocolate, ItemCategory.HOT_DRINKS),
    Item("7", "Chai Latte", "Spiced tea with milk", 6.59, R.drawable.chai_latte, ItemCategory.HOT_DRINKS),
    Item("8", "Matcha Latte", "Green tea powder with milk", 6.89, R.drawable.matcha_latte, ItemCategory.HOT_DRINKS),
    Item("9", "Turkish Coffee", "Traditional finely ground coffee", 5.99, R.drawable.turkish_coffee, ItemCategory.HOT_DRINKS),
    Item("10", "Herbal Tea", "Caffeine-free relaxing tea", 4.99, R.drawable.herbal_tea, ItemCategory.HOT_DRINKS),
    Item("11", "Iced Coffee", "Chilled brewed coffee over ice", 5.99, R.drawable.iced_coffee, ItemCategory.COLD_DRINKS),
    Item("12", "Iced Latte", "Chilled espresso with milk", 6.49, R.drawable.iced_latte, ItemCategory.COLD_DRINKS),
    Item("13", "Iced Americano", "Espresso with cold water over ice", 5.49, R.drawable.iced_americano, ItemCategory.COLD_DRINKS),
    Item("14", "Iced Mocha", "Espresso, chocolate, and milk over ice", 7.29, R.drawable.iced_mocha, ItemCategory.COLD_DRINKS),
    Item("15", "Cold Brew", "Slow-steeped cold brewed coffee", 6.99, R.drawable.cold_brew, ItemCategory.COLD_DRINKS),
    Item("16", "Iced Matcha Latte", "Chilled green tea with milk", 6.89, R.drawable.iced_matcha_latte, ItemCategory.COLD_DRINKS),
    Item("17", "Iced Chai", "Chai tea with ice and milk", 6.59, R.drawable.iced_chai, ItemCategory.COLD_DRINKS),
    Item("18", "Lemonade", "Refreshing sweet and tangy lemon drink", 4.99, R.drawable.lemonade, ItemCategory.COLD_DRINKS),
    Item("19", "Iced Peach Tea", "Cool and fruity peach flavored tea", 5.49, R.drawable.iced_peach_tea, ItemCategory.COLD_DRINKS),
    Item("20", "Berry Smoothie", "Blended mix of fresh berries and yogurt", 6.79, R.drawable.berry_smothie, ItemCategory.COLD_DRINKS),
    Item("21", "Croissant", "Crescent-shaped French pastry", 3.99, R.drawable.croissant, ItemCategory.FOODS),
    Item("22", "Bagel", "Boiled and baked round bread with a hole", 2.99, R.drawable.bagel, ItemCategory.FOODS),
    Item("23", "Muffin", "Soft baked cake-like treat", 3.49, R.drawable.muffin, ItemCategory.FOODS),
    Item("24", "Toast", "Crispy golden brown bread slices", 2.49, R.drawable.toast, ItemCategory.FOODS),
    Item("25", "Cheesecake", "Creamy dessert with biscuit base", 5.99, R.drawable.cheesecake, ItemCategory.FOODS),
    Item("26", "Brownie", "Rich chocolate square dessert", 4.29, R.drawable.brownie, ItemCategory.FOODS),
    Item("27", "Sandwich", "Bread filled with deli meat, veggies, or spreads", 6.49, R.drawable.sandwich, ItemCategory.FOODS),
    Item("28", "Quiche", "Savory pie filled with egg, cheese, and vegetables", 5.79, R.drawable.quiche, ItemCategory.FOODS),
    Item("29", "Scone", "Buttery British pastry, often served with jam", 3.69, R.drawable.scone, ItemCategory.FOODS),
    Item("30", "Waffle", "Golden grid-patterned pastry with toppings", 4.99, R.drawable.waffle, ItemCategory.FOODS),
    Item("31", "Pancakes", "Fluffy breakfast cakes, often stacked", 5.49, R.drawable.pancake, ItemCategory.FOODS),
    Item("32", "Salad", "Fresh mix of greens, veggies, and dressings", 5.89, R.drawable.salad, ItemCategory.FOODS),
    Item("33", "Fruit Bowl", "Mixed seasonal fruits, freshly served", 4.59, R.drawable.fruit_bowl, ItemCategory.FOODS),
    Item("34", "Donut", "Fried dough ring, glazed or filled", 2.99, R.drawable.donut, ItemCategory.FOODS),
    Item("35", "Avocado Toast", "Toasted bread with mashed avocado topping", 6.29, R.drawable.avocado_toast, ItemCategory.FOODS)
)

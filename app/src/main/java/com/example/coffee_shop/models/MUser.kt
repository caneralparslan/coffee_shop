package com.example.coffee_shop.models



data class MUser(
    val userName: String){

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_name" to this.userName,
        )
    }
}

package com.example.coffee_shop.models



data class MUser(
    val userId : String,
    val userName: String){

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "user_name" to this.userName,
        )
    }
}

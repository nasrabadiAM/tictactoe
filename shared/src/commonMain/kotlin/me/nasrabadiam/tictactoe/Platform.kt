package me.nasrabadiam.tictactoe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
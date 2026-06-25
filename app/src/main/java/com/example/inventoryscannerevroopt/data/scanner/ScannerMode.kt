package com.example.inventoryscannerevroopt.data.scanner

enum class ScannerMode(
    val title: String
) {

    BROADCAST("Быстрый"),
    KEYSTROKE("Клавиатурный"),
    EDIT_TEXT("Редактирование текста")
}
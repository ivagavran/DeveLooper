package hr.ivagavran.develooper.quiz;

import hr.ivagavran.develooper.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.light,
        R.drawable.splash,
        R.drawable.blue_splash,
    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}
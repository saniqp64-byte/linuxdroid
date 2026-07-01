package com.saniqp.linuxdroid

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

open class Window(protected val context: Context, protected val mainLayout: ConstraintLayout) {

    val screenWidth = context.resources.displayMetrics.widthPixels
    val screenHeight = context.resources.displayMetrics.heightPixels
    var resize = false
    var move = false

    var click_offset = 70f

    var default_width = 400f
    var default_height = 200f


    fun create_window(new_width : Float, new_height : Float) : ConstraintLayout {
        val spawnedWindow = ConstraintLayout(context).apply {
            // Устанавливаем размеры окна в пикселях
            layoutParams = ConstraintLayout.LayoutParams(
                (new_width).toInt(),
                (new_height).toInt()
            )

            // Полупрозрачный тёмный фон окна
            setBackgroundColor(android.graphics.Color.parseColor("#80000000"))

            // Вычисляем высоту экрана, чтобы разместить окно в нижней части экрана
            val screenHeight = resources.displayMetrics.heightPixels

            // Смещаем окно вправо и поднимаем чуть выше нижнего края
            translationX = 10f
            translationY = screenHeight - 377f - 216f
        }

        return spawnedWindow
    }

    fun window_logic(spawnedWindow : View){
        spawnedWindow.setOnTouchListener { view, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    if (event.x > (view.width - click_offset) && event.y > (view.height - click_offset)) {
                        resize = true
                    }

                    if (event.x < click_offset && event.y < click_offset) {
                        move = true
                    }
                }

                android.view.MotionEvent.ACTION_MOVE -> {
                    if (resize == true && move == false){
                        view.layoutParams = ConstraintLayout.LayoutParams(
                            (event.rawX - view.translationX).toInt(),
                            (event.rawY - view.translationY).toInt()
                        )
                    }
                    if (move == true && resize == false){
                        view.translationX = event.rawX
                        view.translationY = event.rawY
                    }
                }

                android.view.MotionEvent.ACTION_UP -> {
                    // Палец оторвался — сбрасываем режим перемещения и изменения размера
                    resize = false
                    move = false
                }
            }


            true
        }
    }


    fun start_window(new_width: Float = default_width, new_height: Float = default_height) : ConstraintLayout {
        var new_window = create_window(new_width, new_height)
        window_logic(new_window)
        mainLayout.addView(new_window)

        return new_window
    }
}
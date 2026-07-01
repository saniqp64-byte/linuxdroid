package com.saniqp.linuxdroid

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.widget.ImageView
import com.saniqp.linuxdroid.windows.Fastfetch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER)

        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<ConstraintLayout>(R.id.main)

        val term_input = findViewById<TextView>(R.id.terminal_input)

        // Слушаем нажатие клавиши на виртуальной клавиатуре
        term_input.setOnEditorActionListener { view, actionId, event ->
            // Проверяем: пользователь нажал Enter на клавиатуре
            val isEnter = event?.action == android.view.KeyEvent.ACTION_DOWN &&
                    event.keyCode == android.view.KeyEvent.KEYCODE_ENTER

            // Обрабатываем команду если нажали «Готово» или Enter
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE || isEnter) {

                // Читаем введённый текст и убираем лишние пробелы
                val command = term_input.text.toString().trim()

                // Если команда — fastfetch, запускаем окно с инфо об устройстве
                if (command.contains("fastfetch", ignoreCase = true)) {
                    Fastfetch(this, mainLayout).start_fastfetch()
                }

                // Очищаем поле ввода после выполнения команды
                term_input.text = ""
                true // Возвращаем true, чтобы скрыть клавиатуру
            } else {
                false // Не наша комбинация — пропускаем
            }
        }





    }
}
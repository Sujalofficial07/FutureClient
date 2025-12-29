package com.futureclient.api.module

abstract class Setting<T>(val name: String, var value: T)

class BooleanSetting(name: String, value: Boolean) : Setting<Boolean>(name, value) {
    fun toggle() { value = !value }
}

class NumberSetting(name: String, value: Double, val min: Double, val max: Double, val increment: Double) : Setting<Double>(name, value)

class ModeSetting(name: String, value: String, val modes: List<String>) : Setting<String>(name, value) {
    fun cycle() {
        val index = modes.indexOf(value)
        value = modes[(index + 1) % modes.size]
    }
}

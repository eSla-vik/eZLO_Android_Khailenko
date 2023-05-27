package com.example.pk_device_android.presentation.mappers

interface Mapper<InputType, OutputType> {
    fun map(input: InputType): List<OutputType>
}
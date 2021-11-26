package com.example.gstest.domain

import com.example.gstest.data.repository.ServiceRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val serviceRepository: ServiceRepository) {

    suspend operator fun invoke(): Any{
        return serviceRepository.getCharacter()
    }
}
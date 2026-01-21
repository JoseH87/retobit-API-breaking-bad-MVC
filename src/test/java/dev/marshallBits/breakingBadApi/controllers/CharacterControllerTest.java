package dev.marshallBits.breakingBadApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marshallBits.breakingBadApi.dto.CreateCharacterDTO;
import dev.marshallBits.breakingBadApi.models.CharacterStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTest {
    // MockMVC (Modelo Vista Controlador)
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("GET: Recibimos 10 characters en api/caracters")
    void getAllCharacters() throws Exception {
       mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    @DisplayName("POST: un nuevo character funciona correctamente en api/characters")
    void postNewCharacter() throws Exception{
        CreateCharacterDTO saul = CreateCharacterDTO
                .builder()
                .name("Saul Goodman")
                .occupation("Lawyer")
                .status(CharacterStatus.ALIVE)
                .build();

        mockMvc.perform(post("/api/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saul))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Saul Goodman"))
                .andExpect(jsonPath("$.occupation").value("Lawyer"));
    }

    @Test
    @DisplayName("GET: Test de integracion para obtener un character por ID")
    void getCharacterById() throws Exception {
        mockMvc.perform(get("/api/characters/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hank Schrader"))
                .andExpect(jsonPath("$.occupation").value("DEA Agent"));
    }

    @Test
    @DisplayName("PATCH: Test de integracion para actualizar el estado de un character")
    void updateCharacterStatus() throws Exception {
        Long characterId = 4L; // Suponiendo que este ID existe en la base de datos
        mockMvc.perform(patch("/api/characters/{id}/status", characterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(characterId))
                .andExpect(jsonPath("$.status").value("DEAD"));
    }
    @Test
    @DisplayName("PUT: Hank ahora es Wilson")
    void putHank() throws Exception{
        CreateCharacterDTO wilson = CreateCharacterDTO
                .builder()
                .name("Wilson Thompson")
                .occupation("Farmer")
                .status(CharacterStatus.ALIVE)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/characters/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wilson))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Wilson Thompson"))
                .andExpect(jsonPath("$.occupation").value("Farmer"))
                .andExpect(jsonPath("$.status").value("ALIVE"));
    }
}

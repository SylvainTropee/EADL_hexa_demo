package eni.ecole.eadl_hexa_demo.adapter.in.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test fonctionnel complet : création livre -> emprunt -> retour")
    void fullFunctionalTest() throws Exception {
        String createJson = """
                {
                  "title": "Le Guide du Voyageur Galactique"
                }
                """;

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Le Guide du Voyageur Galactique"))
                .andExpect(jsonPath("$.available").value(true));

        Long bookId = 1L;

        String borrowJson = """
                {
                  "email": "etudiant@eni.fr"
                }
                """;

        mockMvc.perform(post("/api/books/" + bookId + "/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(borrowJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));

        mockMvc.perform(post("/api/books/" + bookId + "/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(borrowJson))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());

        mockMvc.perform(post("/api/books/" + bookId + "/return"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true));
    }
}

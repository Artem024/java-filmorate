package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmValidationTests extends FilmController {
    private Film film;

    @Test
    void createFilmNameIsBlank() {
        film = Film.builder()
                .id(1)
                .name("")
                .description("description")
                .releaseDate(LocalDate.of(2000, 1, 1))
                .duration(120)
                .build();
        assertThrows(ValidationException.class, () -> FilmController.validation(film));
    }

    @Test
    void createFilmDescriptionMore200() {
        String descr = "d".repeat(201);
        film = Film.builder()
                .id(1)
                .name("name")
                .description(descr)
                .releaseDate(LocalDate.of(2000, 1, 1))
                .duration(120)
                .build();
        assertThrows(ValidationException.class, () -> FilmController.validation(film));
    }

    @Test
    void createFilmBefore28December1895() {
        film = Film.builder()
                .id(1)
                .name("name")
                .description("description")
                .releaseDate(LocalDate.of(1895, 12, 27))
                .duration(120)
                .build();
        assertThrows(ValidationException.class, () -> FilmController.validation(film));
    }

    @Test
    void createFilmWithNegativeDuration() {
        film = Film.builder()
                .id(1)
                .name("name")
                .description("description")
                .releaseDate(LocalDate.of(2000, 1, 1))
                .duration(-1)
                .build();
        assertThrows(ValidationException.class, () -> FilmController.validation(film));
    }

}

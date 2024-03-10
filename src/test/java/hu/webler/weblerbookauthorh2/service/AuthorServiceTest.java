package hu.webler.weblerbookauthorh2.service;

import hu.webler.weblerbookauthorh2.entity.Author;
import hu.webler.weblerbookauthorh2.repository.AuthorRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        // No specific setup needed in this case!
    }

    @Test
    @DisplayName("Given no author exists, when getAllAuthors is called, then an empty list should be returned")
    void whenGetAllAuthors_ThenReturnEmptyList() {
        // Given
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Author> authors = authorService.getAllAuthors();

        // Then
        BDDAssertions.assertThat(authors).isEmpty();
    }
}
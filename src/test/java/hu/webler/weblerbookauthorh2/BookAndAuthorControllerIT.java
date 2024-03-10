package hu.webler.weblerbookauthorh2;

import hu.webler.weblerbookauthorh2.entity.Author;
import hu.webler.weblerbookauthorh2.entity.Book;
import hu.webler.weblerbookauthorh2.repository.AuthorRepository;
import hu.webler.weblerbookauthorh2.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookAndAuthorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("When list all books and authors then expect success response with model attribute empty")
    public void whenListAllBooksAndAuthorsRequested_thenExpectSuccessResponseWithModelAttributesEmpty() throws Exception {
        // Given
        // Mock expected model attributes
        List<Author> expectedAuthors = new ArrayList<>();
        List<Book> expectedBooks = new ArrayList<>();

        // When and Then
        mockMvc
                .perform(get("/list-all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authors", expectedAuthors))
                .andExpect(model().attribute("books", expectedBooks))
                .andDo(result -> {
                    // Compare the result with the expected model attributes
                    var model = Objects.requireNonNull(result.getModelAndView()).getModel();
                    BDDAssertions.assertThat(model.get("authors")).usingRecursiveComparison().isEqualTo(expectedAuthors);
                    BDDAssertions.assertThat(model.get("books")).usingRecursiveComparison().isEqualTo(expectedBooks);
                });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DirtiesContext
    @DisplayName("When list all books and authors then expect success response with model attribute non empty")
    public void whenListAllBooksAndAuthorsRequested_thenExpectSuccessResponseWithModelAttributesNonEmpty() throws Exception {
        // Given
        // Mock expected model attributes
        List<Author> expectedAuthors = new ArrayList<>();

        // Create sample data
        Author agathaCristie = new Author();
        agathaCristie.setId(1L);
        agathaCristie.setName("Agatha Cristie");
        agathaCristie = authorRepository.save(agathaCristie);

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Cat Among the Pigeons");
        book1.setAuthor(agathaCristie);
        book1 = bookRepository.save(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Murder on the Orient Express");
        book2.setAuthor(agathaCristie);
        book2 = bookRepository.save(book2);

        agathaCristie.setBooks(Arrays.asList(book1, book2));

        expectedAuthors.add(agathaCristie);
        List<Book> expectedBooks = new ArrayList<>(Arrays.asList(book1, book2));

        // When and Then
        mockMvc
                .perform(get("/list-all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authors", expectedAuthors))
                .andExpect(model().attribute("books", expectedBooks))
                .andDo(result -> {
                    // Compare the result with the expected model attributes
                    var model = Objects.requireNonNull(result.getModelAndView()).getModel();
                    BDDAssertions.assertThat(model.get("authors")).usingRecursiveComparison().isEqualTo(expectedAuthors);
                    BDDAssertions.assertThat(model.get("books")).usingRecursiveComparison().isEqualTo(expectedBooks);
                });
    }
}

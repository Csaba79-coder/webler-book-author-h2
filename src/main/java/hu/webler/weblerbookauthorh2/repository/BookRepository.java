package hu.webler.weblerbookauthorh2.repository;

import hu.webler.weblerbookauthorh2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

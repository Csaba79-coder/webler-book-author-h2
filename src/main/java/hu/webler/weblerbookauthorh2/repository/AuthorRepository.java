package hu.webler.weblerbookauthorh2.repository;

import hu.webler.weblerbookauthorh2.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

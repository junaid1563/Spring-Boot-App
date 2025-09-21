package spring.project.interview_prep.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.project.interview_prep.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("Select count(bookName) from Book where bookName = ?1 and authorName = ?2 and country = ?3 and cost = ?4")
    public Integer findDuplicate(String bookName, String authorName, String country, int cost);

    @Query("Select b from Book b where b.authorName=?1")
    public Book findBookByAuthor(String authorName);

    @Query("Select b from Book b where b.country=?1")
    public Book findBookByCountry(String country);

    @Modifying
    @Transactional
    @Query("Update Book b set b.bookName=?1, b.authorName=?2, b.country=?3, b.cost=?4 where b.id=?5")
    public void updateBook(String bookName, String authorName, String country, int cost, int id);
}

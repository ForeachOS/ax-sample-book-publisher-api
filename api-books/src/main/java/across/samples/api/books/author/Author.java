package across.samples.api.books.author;

import across.samples.api.books.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Arne Vandamme
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Author implements Persistable<Long>, Serializable
{
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "author")
	private Set<Book> books;

	@JsonIgnore
	@Override
	public boolean isNew() {
		return id == null || id == 0;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Author book = (Author) o;
		return id != null && Objects.equals( id, book.id );
	}

	@Override
	public int hashCode() {
		return Objects.hash( id );
	}
}
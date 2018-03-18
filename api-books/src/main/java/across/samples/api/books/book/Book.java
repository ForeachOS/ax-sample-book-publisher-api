package across.samples.api.books.book;

import across.samples.api.books.author.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Arne Vandamme
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Book implements Persistable<Long>, Serializable
{
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "title")
	private String title;

	@NotNull
	@ManyToOne
	private Author author;

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
		Book book = (Book) o;
		return id != null && Objects.equals( id, book.id );
	}

	@Override
	public int hashCode() {
		return Objects.hash( id );
	}
}
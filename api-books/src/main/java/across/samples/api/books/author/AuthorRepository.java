package across.samples.api.books.author;

import com.foreach.across.core.annotations.Exposed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Arne Vandamme
 */
@Exposed
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors", excerptProjection = Author.class)
public interface AuthorRepository extends JpaRepository<Author, Long>
{
	Author findByNameContaining( String partOfName );

	Author findByName( String name );
}

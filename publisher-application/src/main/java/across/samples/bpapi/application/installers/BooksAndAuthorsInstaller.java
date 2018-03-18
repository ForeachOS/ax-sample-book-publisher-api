package across.samples.bpapi.application.installers;

import across.samples.api.books.author.Author;
import across.samples.api.books.author.AuthorRepository;
import across.samples.api.books.book.Book;
import across.samples.api.books.book.BookRepository;
import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.annotations.InstallerMethod;
import com.foreach.across.core.installers.InstallerPhase;
import com.foreach.across.core.installers.InstallerRunCondition;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.stream.Stream;

/**
 * @author Arne Vandamme
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Installer(description = "Insert some known books", runCondition = InstallerRunCondition.AlwaysRun, phase = InstallerPhase.AfterContextBootstrap)
public class BooksAndAuthorsInstaller
{
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	@InstallerMethod
	@SneakyThrows
	public void insertBooks() {
		Yaml yaml = new Yaml();
		try (InputStream bookData = new ClassPathResource( "gutenberg.yaml" ).getInputStream()) {
			BookItem[] items = yaml.loadAs( bookData, BookItem[].class );

			Stream.of( items )
			      .filter( item -> bookRepository.findByTitle( item.getBookTitle() ) == null )
			      .forEach( item -> {
				      Author author = authorRepository.findByName( item.getAuthor() );

				      if ( author == null ) {
					      author = new Author();
					      author.setName( item.getAuthor() );
					      author = authorRepository.save( author );
				      }

				      Book book = new Book();
				      book.setAuthor( author );
				      book.setTitle( item.getBookTitle() );
				      bookRepository.save( book );
			      } );
		}
	}

	@Data
	@NoArgsConstructor
	public static class BookItem
	{
		private String title;
		private String link;
		private String description;

		public String getBookTitle() {
			return title.split( " by " )[0];
		}

		public String getAuthor() {
			return title.split( " by " )[1];
		}
	}
}

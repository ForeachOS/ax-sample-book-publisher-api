package across.samples.bpapi;

import across.samples.api.books.BooksApiModule;
import com.foreach.across.config.AcrossApplication;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.modules.web.AcrossWebModule;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@AcrossApplication(
		modules = {
				AcrossWebModule.NAME,
				AcrossHibernateJpaModule.NAME
		}
)
public class BookPublisherApplication
{
	@Bean
	BooksApiModule booksApiModule() {
		return new BooksApiModule();
	}

	public static void main( String[] args ) {
		SpringApplication springApplication = new SpringApplication( BookPublisherApplication.class );
		springApplication.setDefaultProperties(
				Collections.singletonMap( "spring.config.location", "${user.home}/dev-configs/bookPublisher-application.yml" ) );
		springApplication.run( args );
	}
}
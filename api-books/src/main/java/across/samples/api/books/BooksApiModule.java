package across.samples.api.books;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.core.annotations.AcrossDepends;
import com.foreach.across.core.context.configurer.ApplicationContextConfigurer;
import com.foreach.across.core.context.configurer.ComponentScanConfigurer;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;

import java.util.Set;

/**
 * @author Arne Vandamme
 * @since 1.0.0
 */
@AcrossDepends(required = AcrossHibernateJpaModule.NAME)
public class BooksApiModule extends AcrossModule
{
	public static final String NAME = "BooksApiModule";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected void registerDefaultApplicationContextConfigurers( Set<ApplicationContextConfigurer> contextConfigurers ) {
		contextConfigurers.add( ComponentScanConfigurer.forAcrossModule( BooksApiModule.class ) );
	}
}

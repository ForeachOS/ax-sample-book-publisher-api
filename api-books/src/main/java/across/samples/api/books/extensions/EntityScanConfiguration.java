package across.samples.api.books.extensions;

import across.samples.api.books.BooksApiModule;
import com.foreach.across.core.annotations.ModuleConfiguration;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Attach to the shared entity manager.
 *
 * @author Arne Vandamme
 * @since 1.0.0
 */
@ModuleConfiguration(AcrossHibernateJpaModule.NAME)
@EntityScan(basePackageClasses = BooksApiModule.class)
class EntityScanConfiguration
{
}

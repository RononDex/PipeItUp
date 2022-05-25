package ch.fhnw.ip12.pipeitup.data;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.google.inject.AbstractModule;

/**
* DependencyInjectionConfig
*/
@ExcludeTypeFromJacocoGeneratedReport
public class DependencyInjectionConfigData extends AbstractModule {

	@Override
    protected void configure() {
        bind(Database.class).to(DatabaseImpl.class);
    }
	
}

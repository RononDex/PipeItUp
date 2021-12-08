package ch.fhnw.ip12.pipeitup.data;

import com.google.inject.AbstractModule;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

/**
* DependencyInjectionConfig
*/
@ExcludeTypeFromJacocoGeneratedReport
public class DependencyInjectionConfigData extends AbstractModule {

	@Override
    protected void configure() {
        bind(GraphLayoutDataLoader.class).to(GraphLayoutDataFromDbLoaderImpl.class);
    }
	
}

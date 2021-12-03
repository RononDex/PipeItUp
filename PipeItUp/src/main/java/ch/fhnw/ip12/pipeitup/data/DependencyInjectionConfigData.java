package ch.fhnw.ip12.pipeitup.data;

import com.google.inject.AbstractModule;

/**
* DependencyInjectionConfig
*/
public class DependencyInjectionConfigData extends AbstractModule {

	@Override
    protected void configure() {
        bind(GraphLayoutDataLoader.class).to(GraphLayoutDataFromDbLoaderImpl.class);
    }
	
}

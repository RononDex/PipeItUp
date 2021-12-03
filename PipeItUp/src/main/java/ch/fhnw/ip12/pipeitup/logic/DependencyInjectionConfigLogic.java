package ch.fhnw.ip12.pipeitup.logic;

import com.google.inject.AbstractModule;

/**
* DependencyInjectionConfig
*/
public class DependencyInjectionConfigLogic extends AbstractModule {

	@Override
    protected void configure() {
        bind(GraphLayoutLoader.class).to(GraphLayoutLoaderImpl.class);
    }
	
}

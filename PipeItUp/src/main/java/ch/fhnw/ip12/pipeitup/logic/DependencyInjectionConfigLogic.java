package ch.fhnw.ip12.pipeitup.logic;

import com.google.inject.AbstractModule;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

/**
* DependencyInjectionConfig
*/
@ExcludeTypeFromJacocoGeneratedReport
public class DependencyInjectionConfigLogic extends AbstractModule {

	@Override
    protected void configure() {
        bind(GraphLayoutLoader.class).to(GraphLayoutLoaderImpl.class);
        bind(MinimumSpanningTreeService.class).to(MinimumSpanningTreeServiceImpl.class);
        bind(PrimAlgorithm.class).to(PrimAlgorithmImpl.class);
        bind(KruskalAlgorithm.class).to(KruskalAlgorithmImpl.class);
    }
	
}

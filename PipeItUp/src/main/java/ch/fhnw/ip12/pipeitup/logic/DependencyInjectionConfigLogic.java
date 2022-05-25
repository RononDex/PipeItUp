package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.google.inject.AbstractModule;

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
        bind(HighscoreService.class).to(HighscoreServiceImpl.class);
    }
	
}

package orlian.metMuseum;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MetFrameModule extends AbstractModule {

    @Provides
    static MetMuseumService providesMetMuseumService() {
        return new MetMuseumServiceFactory().getInstance();
    }
}

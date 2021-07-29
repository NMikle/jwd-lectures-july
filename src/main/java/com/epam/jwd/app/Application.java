package com.epam.jwd.app;

import com.epam.jwd.model.Mammal;
import com.epam.jwd.model.MammalContext;
import com.epam.jwd.model.MammalFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);
    private static final MammalFactory factory = MammalFactory.newInstance();

    public static void main(String[] args) {
        MammalContext barsikContext = MammalContext.of("CAT", "Barsik", 4, null, null);
        final Mammal barsik = factory.createMammal(barsikContext);
        System.out.println(barsik);
    }

}
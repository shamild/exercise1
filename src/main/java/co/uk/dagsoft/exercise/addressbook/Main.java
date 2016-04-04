package co.uk.dagsoft.exercise.addressbook;

import co.uk.dagsoft.exercise.addressbook.configuration.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by shamil on 04/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = getApplicationContext();

        ExampleQueriesImpl exampleQueries = ctx.getBean(ExampleQueriesImpl.class);
        exampleQueries.queries();
    }

    private static AnnotationConfigApplicationContext getApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfiguration.class);
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();
        return ctx;
    }
}

/**
 * Created by arthurgeron on 09/05/17.
 */

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App implements Runnable {
    private Server jettyServer;

    @Inject
    public App(Server server) {
        this.jettyServer = server;
    }

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AppModule());

        App  app = injector.getInstance(App.class);

        app.jettyServer = app.getServerInstance();
        try {
            app.jettyServer.start();
            app.jettyServer.join();
        } catch (Exception e) {
            System.out.println("Exception! Stopping jetty server ");
            app.jettyServer.stop();
            e.printStackTrace();
        } finally {
            app.jettyServer.destroy();
        }
    }

    private Server getServerInstance() {
        if (jettyServer == null)
            newServerInstance();
        return jettyServer;
    }

    private void newServerInstance() {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                Voip.class.getCanonicalName());

    }

    public void run() {

    }
}


import com.google.inject.AbstractModule;

/**
 * Created by arthurgeron on 09/05/17.
 */
public class RabbitMQModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(RabbitMQAdapter.class);
    }
}

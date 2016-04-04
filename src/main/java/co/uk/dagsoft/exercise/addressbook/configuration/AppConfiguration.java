package co.uk.dagsoft.exercise.addressbook.configuration;

import co.uk.dagsoft.exercise.addressbook.ExampleQueriesImpl;
import co.uk.dagsoft.exercise.addressbook.repository.AddressBookAssembler;
import co.uk.dagsoft.exercise.addressbook.repository.AddressBookAssemblerImpl;
import co.uk.dagsoft.exercise.addressbook.repository.AddressBookRepository;
import co.uk.dagsoft.exercise.addressbook.repository.AddressBookRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shamil on 04/04/2016.
 */
@Configuration
public class AppConfiguration {

    private static final String FILENAME = "src/main/resources/AddressBook";

    @Bean
    public AddressBookAssembler addressBookAssembler() {
        return new AddressBookAssemblerImpl();
    }

    @Bean
    public AddressBookRepository addressBookRepository() {
        return new AddressBookRepositoryImpl(addressBookAssembler(), FILENAME);
    }

    @Bean
    public ExampleQueriesImpl exampleQueries() {
        return new ExampleQueriesImpl(addressBookRepository());
    }
}

package co.uk.dagsoft.exercise.addressbook;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;
import co.uk.dagsoft.exercise.addressbook.domain.Gender;
import co.uk.dagsoft.exercise.addressbook.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by shamil on 04/04/2016.
 */
@Slf4j
public class ExampleQueriesImpl {

    @Autowired
    private AddressBookRepository addressBookRepository;

    public ExampleQueriesImpl(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    private static class FirstNamePredicate implements Predicate<AddressBook> {
        private String firstName ;

        public FirstNamePredicate(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public boolean test(AddressBook addressBook) {
            return firstName.equalsIgnoreCase(addressBook.getFirstName());
        }
    }

    public void queries() {
        long maleCount = getStream().filter(a -> Gender.MALE.equals(a.getGender())).count();
        Optional<AddressBook> oldest = getStream().min(Comparator.comparing(AddressBook::getDob));
        Optional<AddressBook> bill = getStream().filter(new FirstNamePredicate("Bill")).findAny();
        Optional<AddressBook> paul = getStream().filter(new FirstNamePredicate("Paul")).findAny();
        LocalDate billDob = bill.get().getDob();
        LocalDate paulDob = paul.get().getDob();
        long daysBetween = ChronoUnit.DAYS.between(billDob, paulDob);

        log.info(String.format("How many males are in the address book? => %d\n", maleCount));
        log.info(String.format("Who is the oldest person in the address book? => %s\n", oldest.get()));
        log.info(String.format("How many days older is Bill than Paul? => %d\n", daysBetween));
    }

    private Stream<AddressBook> getStream() {
        return addressBookRepository.getStream();
    }

}

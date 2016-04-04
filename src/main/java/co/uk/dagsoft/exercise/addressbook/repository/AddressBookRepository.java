package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;

import java.util.stream.Stream;

/**
 * Created by shamil on 04/04/2016.
 */
public interface AddressBookRepository {
    Stream<AddressBook> getStream();
}

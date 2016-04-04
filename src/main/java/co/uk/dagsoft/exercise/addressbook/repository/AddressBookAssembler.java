package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;

/**
 * Created by shamil on 04/04/2016.
 */
public interface AddressBookAssembler {
    AddressBook assemble(String line);
}

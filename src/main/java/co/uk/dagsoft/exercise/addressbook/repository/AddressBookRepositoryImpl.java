package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;
import co.uk.dagsoft.exercise.addressbook.repository.exception.DataException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by shamil on 04/04/2016.
 */
@Slf4j
public class AddressBookRepositoryImpl implements AddressBookRepository {

    private final AddressBookAssembler addressBookAssembler;
    private final String fileName;


    public AddressBookRepositoryImpl(AddressBookAssembler addressBookAssembler, String fileName) {
        this.addressBookAssembler = addressBookAssembler;
        this.fileName = fileName;
        log.info("Based on address book %s\n", fileName);
    }

    @Override
    public Stream<AddressBook> getStream() {

        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(fileName));
        } catch (IOException e) {
            throw new DataException("Error while reading " +  fileName, e);
        }

        return stream.map(line -> addressBookAssembler.assemble(line));
    }

}

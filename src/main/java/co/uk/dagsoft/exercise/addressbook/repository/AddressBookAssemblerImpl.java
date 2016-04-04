package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;
import co.uk.dagsoft.exercise.addressbook.domain.Gender;
import co.uk.dagsoft.exercise.addressbook.repository.exception.ParsingException;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Created by shamil on 04/04/2016.
 */
public class AddressBookAssemblerImpl implements AddressBookAssembler {
    private static final char SPACE = ' ';
    private static final String COMMA = ",";

    private DateTimeFormatter dateFormatter;

    public AddressBookAssemblerImpl() {
        dateFormatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .appendValueReduced(ChronoField.YEAR,2,2, Year.now().getValue()-99)
                .toFormatter();
    }

    @Override
    public AddressBook assemble(String line) {
        try {
            String[]splitted = line.split(COMMA);
            String name = splitted[0].trim();
            Gender gender = Gender.valueOf(splitted[1].trim().toUpperCase());
            LocalDate date = LocalDate.parse(splitted[2].trim(), dateFormatter);
            int nameSep = name.indexOf(SPACE);
            String firstName = name.substring(0, nameSep);
            String lastName = name.substring(nameSep + 1);
            return new AddressBook(firstName, lastName, date, gender);
        } catch (Exception e) {
            throw new ParsingException("Cannot parse line "+line, e);
        }
    }
}

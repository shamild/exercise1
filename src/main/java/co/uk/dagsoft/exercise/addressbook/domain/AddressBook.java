package co.uk.dagsoft.exercise.addressbook.domain;

import lombok.Value;

import java.time.LocalDate;

/**
 * Created by shamil on 04/04/2016.
 */
@Value
public class AddressBook {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
}

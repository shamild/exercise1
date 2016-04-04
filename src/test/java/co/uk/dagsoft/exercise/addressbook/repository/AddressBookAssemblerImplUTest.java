package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;
import co.uk.dagsoft.exercise.addressbook.domain.Gender;
import co.uk.dagsoft.exercise.addressbook.repository.exception.ParsingException;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by shamil on 04/04/2016.
 */
public class AddressBookAssemblerImplUTest {

    private AddressBookAssembler testObj = new AddressBookAssemblerImpl();

    @Test
    public void whenDobIn20thCenturyThenAssemble() {
        AddressBook addressBook = testObj.assemble("Bill McKnight, Male, 16/03/77");
        assertThat(addressBook, is(new AddressBook("Bill", "McKnight", LocalDate.of(1977, 3, 16), Gender.MALE)));
    }

    @Test
    public void whenDobIn21stCenturyThenAssemble() {
        AddressBook addressBook = testObj.assemble("Bill McKnight, Male, 16/03/00");
        assertThat(addressBook, is(new AddressBook("Bill", "McKnight", LocalDate.of(2000, 3, 16), Gender.MALE)));
    }

    @Test
    public void whenFemaleThenAssemble() {
        AddressBook addressBook = testObj.assemble("Gemma Lane, Female, 20/11/91");
        assertThat(addressBook, is(new AddressBook("Gemma", "Lane", LocalDate.of(1991, 11, 20), Gender.FEMALE)));
    }


    @Test(expected = ParsingException.class)
    public void whenIncompleteThenThrowsException() {
        AddressBook addressBook = testObj.assemble("Bill McKnight, Male");
    }

    @Test(expected = ParsingException.class)
    public void whenNoLastNameThenThrowsException() {
        AddressBook addressBook = testObj.assemble("Bill, Male, 16/03/77");
    }
}
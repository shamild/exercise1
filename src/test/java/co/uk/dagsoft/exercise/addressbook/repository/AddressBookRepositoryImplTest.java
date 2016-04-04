package co.uk.dagsoft.exercise.addressbook.repository;

import co.uk.dagsoft.exercise.addressbook.domain.AddressBook;
import co.uk.dagsoft.exercise.addressbook.domain.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shamil on 04/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressBookRepositoryImplTest {
    private static final String FILENAME = "src/test/resources/AddressBookTest";
    private static final String LINE1 = "Bill McKnight, Male, 16/03/77";
    private static final AddressBook ADDRESS_BOOK1 = new AddressBook("Bill", "McKnight", LocalDate.of(2000, 3, 16), Gender.MALE);
    private static final String LINE2 = "Paul Robinson, Male, 15/01/85";
    private static final AddressBook ADDRESS_BOOK_2 = new AddressBook("Paul", "Robinson", LocalDate.of(1985, 1, 15), Gender.MALE);

    @Mock
    private AddressBookAssembler addressBookAssembler;

    private AddressBookRepository testObj;

    @Before
    public void setup() {
        testObj = new AddressBookRepositoryImpl(addressBookAssembler, FILENAME);
    }

    @Test
    public void testGetStream()  {
        when(addressBookAssembler.assemble(LINE1)).thenReturn(ADDRESS_BOOK1);
        when(addressBookAssembler.assemble(LINE2)).thenReturn(ADDRESS_BOOK_2);
        List<AddressBook> collect = testObj.getStream().collect(Collectors.toList());
        assertThat(collect.size(), is(2));
        assertThat(collect, hasItems(ADDRESS_BOOK1, ADDRESS_BOOK_2));
        verify(addressBookAssembler).assemble(LINE1);
        verify(addressBookAssembler).assemble(LINE2);
    }
}
package by.slowar.rentchecker;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Context mockContext;

    @Before
    public void setUp() throws Exception {
        mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.getString(R.string.nothing_found)).thenReturn("Nothing found");
    }

    @Test
    public void validate() {
        System.out.println("mockContext test " + mockContext.getString(R.string.nothing_found));
    }
}
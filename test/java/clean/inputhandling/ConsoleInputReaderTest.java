package clean.inputhandling;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.testng.Assert.assertEquals;

public class ConsoleInputReaderTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();


    @InjectMocks
    ConsoleInputReader consoleInputReader;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testIfGoodOutput() throws Exception {
        systemInMock.provideLines("Hello, World!");
        assertEquals(consoleInputReader.readUserInput(), "Hello, World!");
    }


}

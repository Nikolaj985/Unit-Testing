package clean.userinteraction.resulthandling;

import clean.entities.CalculationOperations;
import clean.entities.CalculationStatement;
import clean.exceptions.InvalidInformationEnteredException;
import clean.exceptions.ResultNotPresentException;
import clean.userinteraction.UserTextPrinter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class CalculationResultPrinterTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    UserTextPrinter userTextPrinter;

    @Mock
    CalculationStatement calculationStatement;

    @InjectMocks
    CalculationResultsPrinter calculationResultsPrinter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkIfResultNotPresent() throws Exception {
        expectedException.expect(ResultNotPresentException.class);

        when(calculationStatement.getResult()).thenReturn(Optional.empty());
        calculationResultsPrinter.presentResults(calculationStatement);
    }

    @Test
    public void checkIfMessagePassed() throws Exception {
        CalculationStatement calculationStatement = new CalculationStatement(new BigDecimal("10"), new BigDecimal("10"), CalculationOperations.ADDITION);
        calculationStatement.setResult(Optional.of(new BigDecimal("20")));
        calculationResultsPrinter.presentResults(calculationStatement);
        verify(userTextPrinter).write("10 + 10 is 20");
    }

}

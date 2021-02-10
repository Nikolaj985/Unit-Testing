package clean.execution;

import clean.entities.CalculationOperations;
import clean.entities.CalculationStatement;
import clean.exceptions.InvalidInformationEnteredException;
import clean.operationconstrution.OperationConstructor;
import clean.userinteraction.UserInteractor;
import clean.userinteraction.UserTextPrinter;
import clean.userinteraction.resulthandling.CalculationResultsPrinter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class LoopCalculationExecutorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private OperationConstructor operationConstructor;
    @Mock
    private UserInteractor userInteractor;
    @Mock
    private UserTextPrinter userTextPrinter;
    @Mock
    private CalculationResultsPrinter calculationResultsPrinter;
    @Mock
    private CalculationStatement calculationStatement;

    @InjectMocks
    private LoopCalculationExecutor loopCalculationExecutor;

    @Before
    public void setUp() throws InvalidInformationEnteredException {
        MockitoAnnotations.initMocks(this);
        when(calculationStatement.getOperation()).thenReturn(CalculationOperations.ADDITION);
        when(calculationStatement.getFirstOperand()).thenReturn(new BigDecimal("10"));
        when(calculationStatement.getSecondOperand()).thenReturn(new BigDecimal("10"));
        when(operationConstructor.fetchCalculationDetails()).thenReturn(calculationStatement);

    }


    @Test
    public void executeTest() throws Exception {
        //  CalculationStatement calculationStatement = new CalculationStatement(new BigDecimal("10"), new BigDecimal("10"), CalculationOperations.ADDITION);
        loopCalculationExecutor.execute();
        verify(calculationResultsPrinter).presentResults(calculationStatement);
    }

    @Test
    public void executeVerifySetResult() throws Exception {
        //  CalculationStatement calculationStatement = new CalculationStatement(new BigDecimal("10"), new BigDecimal("10"), CalculationOperations.ADDITION);
        loopCalculationExecutor.execute();
        verify(calculationStatement).setResult(Optional.of(new BigDecimal("20")));
    }
    @Test
    public void fails_WhenWrong() throws Exception {
        when(operationConstructor.fetchCalculationDetails()).thenThrow(new InvalidInformationEnteredException(".class"));
        loopCalculationExecutor.execute();
        verify(userTextPrinter).write(".class");
    }

    @Test
    public void checkReturnOfUserWantsToContinue() throws Exception {
        when(userInteractor.writeAndGetResponse("Continue? Type TRUE for yes, FALSE - otherwise.")).thenReturn("true").thenReturn("false");
        loopCalculationExecutor.execute();
        verify(operationConstructor, times(2)).fetchCalculationDetails();

    }


}

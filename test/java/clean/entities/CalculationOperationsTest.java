package clean.entities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class CalculationOperationsTest {

    @Test
    public void returnsSum_whenAdditionSelected() {
        BigDecimal result = CalculationOperations.ADDITION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("25"));
    }
    @Test
    public void returnsSubtraction_whenSubtractionSelected() {
        BigDecimal result = CalculationOperations.SUBTRACTION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("-5"));
    }
    @Test
    public void returnsMultiplication_whenMultiplicationSelected() {
        BigDecimal result = CalculationOperations.MULTIPLICATION.calculate(new BigDecimal("10"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("150"));
    }
    @Test
    public void returnsDivision_whenDivisionSelected() {
        BigDecimal result = CalculationOperations.DIVISION.calculate(new BigDecimal("1.50"), new BigDecimal("15"));

        assertEquals(result, new BigDecimal("0.10"));
    }

}

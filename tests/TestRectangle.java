import static org.junit.Assert.*;
import org.junit.Test;

public class TestRectangle {

    @Test
    public void testSuperficie() {
        Rectangle r = new Rectangle(5, 3);
        assertEquals(15.0, r.calcularSuperficie(), 0.0001);
    }

    @Test
    public void testPerimetro() {
        Rectangle r = new Rectangle(5, 3);
        assertEquals(16.0, r.calcularPerimetro(), 0.0001);
    }

    @Test
    public void testValoresDecimales() {
        Rectangle r = new Rectangle(2.5, 4.2);
        assertEquals(10.5, r.calcularSuperficie(), 0.0001);
        assertEquals(13.4, r.calcularPerimetro(), 0.0001);
    }
}

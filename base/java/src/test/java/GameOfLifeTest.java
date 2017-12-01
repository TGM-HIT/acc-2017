
import org.junit.Test;
import gol.GameOfLife;
import static org.junit.Assert.*;

public class GameOfLifeTest {
	@Test
	public void testInitPattern() {
		GameOfLife classUnderTest = new GameOfLife("2;2;0;1000$");
		assertEquals("2;2;0;1000$\n",classUnderTest.getPattern(0));
	}
}

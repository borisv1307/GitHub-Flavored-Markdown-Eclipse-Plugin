package injector;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class CSSInjectorTest {
	CSSInjector cssInjector;

	@Before
	public void setUp() {
		cssInjector = new CSSInjector();
	}

	// @Test
	public void css_length() {
		assertEquals(cssInjector.getCSS().length(), 13378);
	}

}

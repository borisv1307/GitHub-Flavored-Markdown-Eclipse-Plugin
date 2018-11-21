package injector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import wrapper.BufferedReaderWrapper;

public class CSSInjectorTest {

	String RELATIVE_CSS_LOCATION = "src/main/java/github_markdown_css/github-markdown.css";
	CSSInjector cssInjector;
	BufferedReaderWrapper bufferedReaderWrapper;

	@Before
	public void setUp() {
		bufferedReaderWrapper = Mockito.mock(BufferedReaderWrapper.class);
	}

	@Test
	public void css_length() {
		try {
			assertEquals(10863, getMockedInjector(RELATIVE_CSS_LOCATION).getCSS().length());
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void second_init() {
		try {
			cssInjector = getMockedInjector(RELATIVE_CSS_LOCATION);
			cssInjector = getMockedInjector(RELATIVE_CSS_LOCATION);
		} catch (IOException e) {
			fail();
		}
	}

	@Test(expected = IOException.class)
	public void file_not_found() throws IOException {
		cssInjector = getMockedInjector(RELATIVE_CSS_LOCATION + "fail");
	}

	private CSSInjector getMockedInjector(String location) throws IOException {
		BufferedReader br = null;
		InputStream is = new FileInputStream(location);
		br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		Mockito.when(bufferedReaderWrapper.getFileFromLocation(CSSInjector.CSS_LOCATION)).thenReturn(br);
		cssInjector = new CSSInjector(bufferedReaderWrapper);

		return cssInjector;
	}
}

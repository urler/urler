
import com.urler.utils.AppUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class AppUtilsTest {

    @Test
    public void isUrlValidFalseTest() {
        String url = "asdasd";
        boolean result = AppUtils.isUrlValid(url);
        assertEquals(result, false);
    }
    
    @Test
    public void isUrlValidTrueTest() {
        String url = "www.google.com";
        boolean result = AppUtils.isUrlValid(url);
        assertEquals(result, true);
    }
    
    @Test
    public void isUrlValidDotInParamsTest() {
        String url = "www.google.com/asd.";
        boolean result = AppUtils.isUrlValid(url);
        assertEquals(result, false);
    }
    
    @Test 
    public void isDateValidFalse() {
        String date = "2000/30/30";
        boolean result = AppUtils.isDateValid(date);
        assertEquals(result, false);
    }
    
    @Test 
    public void isDateValidTrue() {
        String date = "2018-01-01";
        boolean result = AppUtils.isDateValid(date);
        assertEquals(result, true);
    }
    
    @Test
    public void isProtocolSpecifiedFalse() {
        String url = "www.localhost.net";
        boolean result = AppUtils.isProtocolSpecified(url);
        assertEquals(result, false);        
    }
    
    @Test
    public void isProtocolSpecifiedTrue() {
        String url = "https://www.localhost.net";
        boolean result = AppUtils.isProtocolSpecified(url);
        assertEquals(result, true);        
    }
    
}

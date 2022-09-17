import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Disabled
    @Timeout(value = 21)

    public void timeout() throws Exception {
        Main.main(null);
    }
}

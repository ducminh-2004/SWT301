import PhamDucMinh.AccountService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    AccountService service = new AccountService();
    static PrintWriter writer;

    @BeforeAll
    static void setup() throws IOException {
        writer = new PrintWriter(new FileWriter("UnitTest.txt"));
    }

    @AfterAll
    static void tearDown() {
        writer.close();
    }

    @ParameterizedTest(name = "{index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test registerAccount with data from CSV")
    void testRegisterAccountFromCSV(String username, String password, String email, boolean expected) {
        boolean result = service.registerAccount(username, password, email);
        assertEquals(expected, result, "Expected result for " + username + ", " + password + ", " + email);

        // Ghi kết quả vào file
        writer.println("Input: " + username + "," + password + "," + email +
                " | Expected: " + expected + " | Actual: " + result +
                " | Result: " + (expected == result ? "PASS" : "FAIL"));
    }
}

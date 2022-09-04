import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LombokTest {

    private String name;
    private int age;
    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest();
        lombokTest.getAge();
    }
}

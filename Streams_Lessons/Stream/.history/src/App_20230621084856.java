import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // string arguments before filtering
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        // using a tream to filter off empty strings
        List<String> filtered = new ArrayList<String>();
        System.out.println("Hello, World!");
    }
}
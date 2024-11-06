import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        // string arguments before filtering
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println(strings);

        // using a tream to filter off empty strings
        List<String> filtered = strings.stream().filter(s -> ).collect(Collectors.toList());
        System.out.println(filtered);
    }
}

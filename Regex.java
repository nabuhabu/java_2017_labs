
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public final static String NAME_REGEX = "name: " + ItemBuilder.NAME_PATTERN + ";";
    public final static String PRODUCTION_REGEX = "production: (\\d{4}-\\d{2}-\\d{2});";
    public final static String EXPIRATION_REGEX = "expiration: (\\d{4}-\\d{2}-\\d{2});";
    public final static String TYPE_REGEX = "type: " + ItemBuilder.NAME_PATTERN + ";";
    public final static String PRICE_REGEX = "price: (\\d*[\\.,]\\d*)";
    public final static String PRODUCTS_REGEX = "products: (.+)";


    public static String getRegexGroup(String input, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        if (!match.find())
            throw new IllegalArgumentException();
        return match.group(1);
    }

}
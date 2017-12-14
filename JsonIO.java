import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
//import  java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class JsonIO<T> implements IO<T> {
    @Override
    public void toFile(T entity, String path) {

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), "UTF-8"))) {
            out.write(gson.toJson(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T fromFile(Class<T> tClass, String path) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String result = readFromInputStream(fileInputStream);
            return gson.fromJson(result, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }






























    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            return br.lines().map(line -> line + "\n").collect(Collectors.joining());
        }
    }
}

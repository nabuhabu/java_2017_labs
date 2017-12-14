import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


class LocalDateAdapter implements IO<LocalDate>  {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }

    @Override
    public void toFile(LocalDate entity, String path) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), "UTF-8"))) {
            out.write(gson.toJson(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LocalDate fromFile(Class<LocalDate> localDateClass, String path) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String result = readFromInputStream(fileInputStream);
            return gson.fromJson(result, localDateClass);
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
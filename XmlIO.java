import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.*;
import java.util.stream.Collectors;

public class XmlIO<T> implements IO<T> {

    private XStream initXStream() {
        XStream xStream = new XStream();
        xStream.addPermission(NoTypePermission.NONE);
        xStream.addPermission(AnyTypePermission.ANY);
        return xStream;
    }


    @Override
    public void toFile(T entity, String path) {
        XStream xStream = initXStream();
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), "UTF-8"))) {
            out.write(xStream.toXML(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T fromFile(Class<T> tClass, String path) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            XStream xStream = initXStream();
            String result = readFromInputStream(fileInputStream);
            return (T) xStream.fromXML(result);
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

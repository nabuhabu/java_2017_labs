public interface IO<T> {
    void toFile(T entity, String path);
    T fromFile(Class<T> tClass, String path);
}

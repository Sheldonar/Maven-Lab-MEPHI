import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Singleton {
    private static Singleton instance;
    public static Set<String> setId;

    Singleton(){}
    public static Singleton getInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    public static void addId(String id) {
        setId = new HashSet<String>();
        setId.add(id);
    }
    public static boolean containsSessionId(String name){
        return setId.contains(name);
    }
}

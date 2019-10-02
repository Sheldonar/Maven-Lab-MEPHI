import java.util.Queue;
import java.util.Set;

public class Singleton {
    private static Singleton instance;
    public static Set<String> setId;

    Singleton(){}
    public static Singleton GetInstance() {
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public static void addId(String id) {
        setId.add(id);
    }

    public static boolean containsSessionId(String name){
        if (setId.contains(name))
            return true;
        else
            return false;
    }

}

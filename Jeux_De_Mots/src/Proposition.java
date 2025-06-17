import java.util.ArrayList;
import java.util.List;

public class Proposition {

    public static List<String> pool = List.of(
        "a", "a", "a", "a", "e", "e", "e", "e", "r", "r", "r",
        "o", "o", "o", "u", "u", "u", "u", "i", "i", "i", "w", "z",
        "b", "b", "c", "c", "d", "d", "f", "f", "g", "g", "h", "h",
        "j", "j", "k", "k", "l", "l", "m", "m", "n", "n", "p", "p",
        "q", "q", "s", "s", "t", "t", "v", "v", "x", "x", "y", "y"
    );


    public List<String> generer(){

    int indice;
    List<String> l = new ArrayList<>();
    
    for (int i = 0; i < 8; i++) {
        indice = (int) (Math.random() * pool.size());
        l.add(pool.get(indice));
    }

    return l;
    }


}

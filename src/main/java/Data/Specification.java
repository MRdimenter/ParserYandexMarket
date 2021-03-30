package Data;

import java.util.ArrayList;
import java.util.List;

public class Specification {
    String name;
    List<Subspecs> subspecs;

    public List<Subspecs> getSubspecs() {
        return subspecs;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Specifications{" +
                "name='" + name + '\'' +
                '}' ;
    }
}

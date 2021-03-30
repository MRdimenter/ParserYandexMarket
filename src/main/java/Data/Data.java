package Data;

import java.util.List;

public class Data {
    String id;
    List<Specification> specifications;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }
}

package domain;

import java.util.List;

public class Province {

    private int id;
    private String pname;
    private List<City> citis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<City> getCitis() {
        return citis;
    }

    public void setCitis(List<City> citis) {
        this.citis = citis;
    }
}

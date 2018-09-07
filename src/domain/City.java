package domain;

public class City {

    private int cid;
    private String cname;
    private Province pid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Province getPid() {
        return pid;
    }

    public void setPid(Province pid) {
        this.pid = pid;
    }
}

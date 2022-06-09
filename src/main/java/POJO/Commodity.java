package POJO;

public class Commodity {
   private String name;//商品名称
    private String price;//商品价格
    private String cnt;//商品总评价数
    private String positive;//商品好评度
    private String presentation;//商品介绍
    private String URL;//商品URL

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", cnt=" + cnt +
                ", positive=" + positive +
                ", URL='" + URL + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = String.valueOf(price);
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = String.valueOf(cnt);
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(float positive) {
        this.positive = String.valueOf(positive);
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Commodity() {
    }

    public Commodity(String name, String price, String cnt, String positive,  String URL) {
        this.name = name;
        this.price = price;
        this.cnt = cnt;
        this.positive = positive;
        this.URL = URL;
    }
}

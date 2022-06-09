import spider.JDSpider;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // https://list.jd.com/list.html?cat=9987,653,655
        // https://list.jd.com/list.html?cat=9987%2C653%2C655&page=5&s=120&click=0
        for(int page=1;page<=5;page++){
            JDSpider.spider("https://list.jd.com/list.html?cat=9987,653,655&page="+page);
        }
    }
}

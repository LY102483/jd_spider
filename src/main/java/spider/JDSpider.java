package spider;

import POJO.Commodity;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class JDSpider {
    public static void spider(String url) throws InterruptedException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false); // CSS 支持 ✔
        webClient.getOptions().setJavaScriptEnabled(false); // JavaScript支持 ✔
        webClient.getOptions().setThrowExceptionOnScriptError(false);//js运行错误时不抛出异常
        webClient.getOptions().setTimeout(1000);//设置连接超时时间
        webClient.getOptions().setDoNotTrackEnabled(false);
        try {
            HtmlPage page = webClient.getPage(url); // 解析获取页面
            List<HtmlElement> divList=page.getByXPath("//div[@class='gl-i-wrap']");//将符合要求的元素存入List集合
            for (HtmlElement element :divList) {

                String price=element.getElementsByTagName("div").get(4).asText();//价格
                String name=element.getElementsByTagName("div").get(5).getElementsByTagName("a").get(0).getElementsByTagName("em").get(0).asText();//名称
                String URL=element.getElementsByTagName("div").get(5).getElementsByTagName("a").get(0).getAttribute("href");//购买链接
                if(URL.contains("https:")){
                    continue;//有一些商品URL不规范，不方便后期数据清洗
                }else{
                    URL="https:"+ URL;
                }
                // https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId=100026667910&score=0&sortType=5&page=0&pageSize=10&isShadowSku=0&fold=1
                //获取商品ID，请求评价信息
                String positive="";
                String cnt="";
                String[] urlSplit=URL.split("/");
                String id=urlSplit[3].replaceAll("[^0-9]","");
                //获取评价请求地址
                String positiveUrl="https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId="+id+"&score=0&sortType=5&page=0&pageSize=10&isShadowSku=0&fold=1";
                HtmlPage clientPage = webClient.getPage(positiveUrl);
                String s = clientPage.asText();
                //清洗数据(截取JSON文件中有用的信息)
                s=s.replaceAll("fetchJSON_comment98\\u0028","");
                s=s.replaceAll("\\u0029;","");
                //先转JsonObject
                try {
                    JsonObject jsonObject=new JsonParser().parse(s).getAsJsonObject();
                    JsonObject productCommentSummary = jsonObject.getAsJsonObject("productCommentSummary");
                    positive=productCommentSummary.get("commentCountStr").toString();//好评
                    cnt=productCommentSummary.get("goodRateShow").toString();//好评
                }catch (Exception e){

                };
                Commodity commodity=new Commodity(name,price,cnt,positive,URL);
                System.out.println(commodity);
            }
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.close(); // 关闭客户端，释放内存
        }
    }
}

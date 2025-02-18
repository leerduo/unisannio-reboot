package solutions.alterego.android.unisannio.sea;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import solutions.alterego.android.unisannio.interfaces.IParser;
import solutions.alterego.android.unisannio.models.Article;

public class SeaParser implements IParser {

    public List<Article> parse(Document document) {
        Elements elements = document.select("p.simplenewsflash_item");
        List<Article> articles = new ArrayList<>();

        for (Element e : elements) {
            String title = e.text();

            String link = e.select("a").attr("href");

            articles.add(Article.builder().title(title).url(link).build());
        }
        return articles;
    }
}
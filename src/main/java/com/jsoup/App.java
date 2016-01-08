package com.jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		String root = "http://www.bttiantang.com/";
		String select = "a[href^=/subject/]";
		Map<String, Element> map = getAllHref(root, select);
		for (Element s : map.values()) {
			select = "a[href^=/download.php]";
			Map<String, Element> linksHref = getAllHref(root + s.attr("href"), select);
			for (Element sc : linksHref.values()) {
				System.out.println(sc);
			}
		}

	}

	/**
	 * 得到所有的href
	 * 
	 * @param root
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Element> getAllHref(String root, String select) throws IOException {
		Document doc = Jsoup.connect(root).get();
		Elements links = doc.select(select); //
		Map<String, Element> map = new LinkedHashMap<String, Element>();
		for (Element e : links) {
			map.put(e.attr("href"), e);
		}
		return map;
	}
}

package article.service;

import article.model.Article;
import article.model.ArticleContent;

public class ArticleData {
	//Article객체와 ArticleContent객체를 한 객체에 담기 위한 용도
	private Article article;
	private ArticleContent content;
	
	public ArticleData(Article article, ArticleContent content) {
		this.article = article;
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public ArticleContent getContent() {
		return content;
	}
	
}

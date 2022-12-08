package chap6.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDAO {
	
	List<ArticleVO> getAllArticles();
	void addArticle(ArticleVO articleVO);
	int getArticleNo(String title);
	ArticleVO getArticleById(int aid);
	void updateReadcnt(int aid);
	void updateArticle(Map map);
	void deleteArticle(int aid);
}
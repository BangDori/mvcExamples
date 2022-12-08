package chap5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
	int aid;
	int writer;
	String name;
	String title;
	String regdate;
	String moddate;
	int readcnt;
}

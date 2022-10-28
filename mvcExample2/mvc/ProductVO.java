package product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	private int id;
	private String name;
	private String model;
	private String madein;
	private int price;
	private int readcnt;
}

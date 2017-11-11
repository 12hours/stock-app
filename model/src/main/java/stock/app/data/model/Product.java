package stock.app.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private int id;
	private String productName;
	private float price;
	private ProductType type;

}

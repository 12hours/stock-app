package stock.app.data.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private int id;
	private String name;
	private String password;
	private boolean isAdmin;
	private Date registrationDate;

}

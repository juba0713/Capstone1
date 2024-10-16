package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class MonthlyTotalApplicationEntity {
	
	public MonthlyTotalApplicationEntity(Object[] objects) {
		this(
				(Integer) objects[0],
				(Integer) objects[1]
				);
	}
	
	private int month;
	
	private int total;
}

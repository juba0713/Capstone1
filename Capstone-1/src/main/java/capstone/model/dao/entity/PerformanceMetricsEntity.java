package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class PerformanceMetricsEntity {

	public PerformanceMetricsEntity(Object[] objects) {
		this(
				(String) objects[0],
				(Integer) objects[1]
				);
	}
	
	private String fullName;
	
	private int reviewedCount;
}

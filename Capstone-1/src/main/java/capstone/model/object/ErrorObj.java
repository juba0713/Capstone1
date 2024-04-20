package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ErrorObj {
	
	public List<String> newPasswordError;

	public List<String> currentPasswordError;
	
	public List<String> confirmPasswordError;
}

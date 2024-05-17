package capstone.model.dto;

import java.util.List;

import capstone.model.object.ApplicantObj;
import lombok.Data;

@Data
public class TbiBoardInOutDto {

	public List<ApplicantObj> listOfApplicants;
}

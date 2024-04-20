package capstone.controller.webdto;

import java.util.List;

import capstone.model.object.ApplicantObj;
import lombok.Data;

@Data
public class OfficerWebDto {

	List<ApplicantObj> listOfApplicants;
}

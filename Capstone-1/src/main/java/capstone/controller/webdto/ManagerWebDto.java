package capstone.controller.webdto;

import java.util.List;

import capstone.model.dao.entity.MonthlyHighestScoresEntity;
import capstone.model.dao.entity.MonthlyTotalApplicationEntity;
import capstone.model.dao.entity.PerformanceMetricsEntity;
import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantMonthlyObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import capstone.model.object.ManagerDashboardObj;
import lombok.Data;

@Data
public class ManagerWebDto {
	
	private ManagerDashboardObj managerDashboardObj;
	
	private AdminDashboardObj adminDashboardObj;

	private List<ApplicantObj> listOfApplicants;

	private int applicantIdPk;
	
	private String encryptedApplicantIdPk;

	private List<String> chosenApplicant;

	private ApplicantDetailsObj applicantDetailsObj;
	
	private ApplicantOfficerFeedbackObj applicantOffFeedbackObj;
	
	private ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	private List<ApplicantMonthlyObj> applicantMonthlyObj;
	
	private List<ApplicantMonthlyObj> applicantRankingMonthlyObj;
	
	private int month;
	
	private int year;
	
	private int projectIdPk;
	
	private List<Integer> projectIdPks;
	
	private int rejectedCount;
	
	private Boolean forQualification = false;
	
	private List<MonthlyHighestScoresEntity> monthlyHighestScores;
	
	private List<PerformanceMetricsEntity> officerPerformanceMetrics;
	
	private List<PerformanceMetricsEntity> tbiBoardPerformanceMetrics;
	
	private List<MonthlyTotalApplicationEntity> monthlyAcceptedApplications;
	
	private List<MonthlyTotalApplicationEntity> monthlyRejectedApplications;
	
	/*
	 * Evaluation DTO
	 */
	private int ctOneRating;

	private String ctOneComments;

	private int ctTwoRating;

	private String ctTwoComments;

	private int ctThreeRating;

	private String ctThreeComments;
	
	private int ctFourRating;
	
	private String ctFourComments;
	
	private int ctFiveRating;
	
	private String ctFiveComments;
	
	private int ctSixRating;
	
	private String ctSixComments;
	
	private int ctSevenRating;
	
	private String ctSevenComments;

	private int ctEightRating;

	private String ctEightComments;

	private String managerFeedback;
}

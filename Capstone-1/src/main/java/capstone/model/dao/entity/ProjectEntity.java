package capstone.model.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="t_project")
public class ProjectEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPk;
	
	private int applicantIdPk;
	
	private String projectTitle;
	
	private String[] teams;
	
	private String problemStatement;
	
	private String targetMarket;
	
	private String solutionDescription;
	
	private String[] historicalTimeline;
	
	private String[] productServiceOffering;
	
	private String[] pricingStrategy;
	
	private String intPropertyStatus;
	
	private String objectives;
	
	private String scopeProposal;
	
	private String methodology;
	
	private String vitaeFile;
	
	private String supportLink;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}

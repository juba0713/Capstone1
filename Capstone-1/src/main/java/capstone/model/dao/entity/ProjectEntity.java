package capstone.model.dao.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
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
	
	@Column(columnDefinition = "text")
	private String projectTitle;
	
	@Column(columnDefinition = "text")
	private String projectDescription;
	
	private List<String> teams;
	
	@Column(columnDefinition = "text")
	private String problemStatement;
	
	@Column(columnDefinition = "text")
	private String targetMarket;
	
	@Column(columnDefinition = "text")
	private String solutionDescription;
	
	private List<String> historicalTimeline;
	
	private List<String> productServiceOffering;
	
	private List<String> pricingStrategy;
	
	@Column(columnDefinition = "text")
	private String intPropertyStatus;
	
	@Column(columnDefinition = "text")
	private String objectives;
	
	@Column(columnDefinition = "text")
	private String scopeProposal;
	
	@Column(columnDefinition = "text")
	private String methodology;
	
	private String vitaeFile;
	
	@Column(columnDefinition = "text")
	private String supportLink;
	
	private Timestamp createdDate;
	
	private Boolean deleteFlg;
}

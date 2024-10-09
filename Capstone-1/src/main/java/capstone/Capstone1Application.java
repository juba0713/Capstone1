package capstone;



import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.model.dao.UserInformationDao;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.logic.UserLogic;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Capstone1Application {

	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(Capstone1Application.class, args);

	}
	
	@PostConstruct
    public void init() {
		int count = userLogic.countAdminUsers();
		
		String firstName = env.getProperty("admin.first.name");
		String lastName = env.getProperty("admin.last.name");
		String mobileNumber = env.getProperty("admin.mobile.number");
		String email = env.getProperty("admin.email");
		String password = env.getProperty("admin.password");
		
		if(count == 0) {
			UserInformationEntity user = new UserInformationEntity();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setMobileNumber(mobileNumber);
			user.setInitialChangePass(true);
			user.setRole("ADMIN");
			user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			user.setDeleteFlg(false);
			int idPk = userLogic.saveUser(user);
			UserInfoAccountEntity userAcc = new UserInfoAccountEntity();
			userAcc.setUserIdPk(idPk);
			userAcc.setPassword(encoder.encode(password));
			userAcc.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			userAcc.setDeleteFlg(false);
			userLogic.saveUserAccount(userAcc);
		}
    }

}

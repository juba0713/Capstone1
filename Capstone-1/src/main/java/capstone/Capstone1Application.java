package capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Capstone1Application {

	public static void main(String[] args) {
		SpringApplication.run(Capstone1Application.class, args);
		// @GetMapping("/test")
		// // @GetMapping()
		// public String showTest() throws IOException {

		// String folderPath = env.getProperty("certificate.path").toString();

		// File imageFile = new File(folderPath + "base_certificate.png");
		// BufferedImage image = ImageIO.read(imageFile);

		// Graphics g = image.getGraphics();

		// g.setFont(new Font("Arial", Font.BOLD, 130));
		// g.setColor(Color.BLACK);

		// String fullName = "Nino Bacaoco";
		// int x = 350;
		// int y = 700;
		// g.drawString(fullName, x, y);

		// g.dispose();

		// File outputFile = new File(folderPath + "test.png");
		// ImageIO.write(image, "png", outputFile);

		// return "initial";
		// }
	}

}

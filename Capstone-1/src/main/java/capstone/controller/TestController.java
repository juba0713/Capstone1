package capstone.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private Environment env;

    @GetMapping("/test")
    // @GetMapping()
    public String showTest() throws IOException {

        String folderPath = env.getProperty("certificate.path").toString();

        File imageFile = new File(folderPath + "base_certificate.png");
        BufferedImage image = ImageIO.read(imageFile);

        Graphics g = image.getGraphics();

        g.setFont(new Font("Arial", Font.BOLD, 130));
        g.setColor(Color.BLACK);

        String fullName = "Nino Bacaoco";
        int x = 350;
        int y = 700;
        g.drawString(fullName, x, y);

        g.dispose();

        File outputFile = new File(folderPath + "test.png");
        ImageIO.write(image, "png", outputFile);

        return "initial";
    }
}

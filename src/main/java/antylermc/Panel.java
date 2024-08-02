package antylermc;

import antylermc.utils.MicrosoftThread;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static antylermc.Frame.*;

public class Panel extends JPanel implements SwingerEventListener {

    private Image background = getImage("launcherbackground.png");
    private STexturedButton play = new STexturedButton(getBuffuredImage("button1.png"), getBuffuredImage("button2.png"));
    private STexturedButton microsoft = new STexturedButton(getBuffuredImage("microsofticon.png"),getBuffuredImage("microsofticon.png"));

    public Panel() throws IOException {
        this.setLayout(null);

        play.setBounds(180,90);
        play.setLocation(390,320);
        play.addEventListener(this);
        this.add(play);

        microsoft.setBounds(90,90);
        microsoft.setLocation(850,380);
        microsoft.addEventListener(this);
        this.add(microsoft);


    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.drawImage(background ,0,0, this.getWidth(), this.getHeight(),this);
    }


    @Override
    public void onEvent(SwingerEvent swingerEvent) {
        if (swingerEvent.getSource() == microsoft) {
            Thread t = new Thread(new MicrosoftThread());
            t.start();
        } else if (swingerEvent.getSource()== play) {
            try {
                launcher.update();
            } catch (Exception e) {
                launcher.getReporter().catchError(e , "Impossible de mettre à jour le launcher!");
            }

            try {
                launcher.launch();
            } catch (Exception e) {
                launcher.getReporter().catchError(e, "Impossible de démarrer le jeu");
        }

        }
    }
}

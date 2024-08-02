package antylermc.utils;

import antylermc.launcher;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;

public class MicrosoftThread implements Runnable{
    @Override
    public void run() {
        try {
            launcher.auth();
        } catch (MicrosoftAuthenticationException e) {
            launcher.getReporter().catchError(e,"Impossible de se connecter!");
        }
    }
}

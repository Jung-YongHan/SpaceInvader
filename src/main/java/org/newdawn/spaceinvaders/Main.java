package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.database.FirebaseAdminSDK;
import org.newdawn.spaceinvaders.frame.LoginFrame;

public class Main {
    public static void main(String argv[]) {
        new FirebaseAdminSDK().initFirebase();
        new LoginFrame();
    }
}

package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.dataBase.FirebaseAdminSDK;
import org.newdawn.spaceinvaders.frame.LoginPage;

public class Main {
    public static void main(String argv[]) {
        new FirebaseAdminSDK().initFirebase();
        LoginPage loginPage = new LoginPage();
    }
}
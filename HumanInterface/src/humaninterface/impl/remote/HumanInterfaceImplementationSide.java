/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl.remote;

import humaninterface.TextualInterface;
import humaninterface.remote.HumanInterfaceConnection;
import java.io.IOException;

/**
 *
 * @author thomasthimothee
 */
public class HumanInterfaceImplementationSide implements Runnable {

    private HumanInterfaceConnection conn;
    private TextualInterface ti;

    public HumanInterfaceImplementationSide(HumanInterfaceConnection conn, TextualInterface ti) {
        this.conn = conn;
        this.ti = ti;
    }

    @Override
    public void run() {
        String question = "";
        try {
            while (true) {
                String methodName = conn.readString();
                switch (methodName) {
                    case "sendMessage":
                        ti.sendMessage(conn.readString());
                        break;
                    case "askForString":
                        question = conn.readString();
                        conn.writeString(ti.askForString(question));
                        break;
                    case "askForPassword":
                        question = conn.readString();
                        conn.writeString(ti.askForPassword(question));
                        break;
                    case "askForEmail":
                        question = conn.readString();
                        conn.writeString(ti.askForEmail(question));
                        break;
                    case "askForInteger":
                        question = conn.readString();
                        conn.writeInt(ti.askForInteger(question));
                        break;
                    case "askForIntegerRange":
                        question = conn.readString();
                        int min = conn.readInt();
                        int max = conn.readInt();
                        conn.writeInt(ti.askForInteger(question, min, max));
                        break;
                    case "makeSingleChoice":
                        question = conn.readString();
                        int len = conn.readInt();
                        String[] choices = new String[len];
                        for (int i = 0; i < len; i++) {
                            choices[i] = conn.readString();
                        }
                        conn.writeInt(ti.makeSingleChoice(question, choices));
                        break;
                    case "close":
                        ti.close();
                        conn.close();
                        return;
                    default:
                        throw new RuntimeException("Unknown method: " + methodName);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

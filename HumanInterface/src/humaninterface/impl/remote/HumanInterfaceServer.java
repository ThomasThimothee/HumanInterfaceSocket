/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl.remote;

import dk.tobiasgrundtvig.util.socket.ConnectionHandler;
import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.Server;
import humaninterface.remote.HumanInterfaceConnectionImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomasthimothee
 */
public class HumanInterfaceServer implements ConnectionHandler {

    private HumanInterfaceCallSide hi;

    public static void main(String[] args) {
        Server server = new Server(new HumanInterfaceServer(), 3456);
        server.run();
    }

    public HumanInterfaceServer() {
        hi = null;
    }

    @Override
    public void handleConnection(SocketConnection conn) {
        String email = "";
        int num;
        String add;
        String pass;
        if (hi == null) {
            System.out.println("Handling a new connection");
            hi = new HumanInterfaceCallSide(new HumanInterfaceConnectionImpl(conn));
            email = hi.askForEmail("What is your email?: ");
            num = hi.askForInteger("give me an int: ");
            add = hi.askForString("What is your address: ");
            pass = hi.askForPassword("Password: ");
            hi.sendMessage("What we know about you, email: " + email + " num: " + num + " address: " + " password: " + pass + "\n");
            try {
                hi.close();
            } catch (IOException ex) {
                Logger.getLogger(HumanInterfaceServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

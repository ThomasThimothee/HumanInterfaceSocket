/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import dk.tobiasgrundtvig.util.socket.impl.SocketConnectionImpl;
import humaninterface.TextualInterface;
import humaninterface.impl.ConsoleTextualInterface;
import humaninterface.remote.HumanInterfaceConnectionImpl;
import java.io.IOException;

/**
 *
 * @author thomasthimothee
 */
public class RemoteClientTest {
    
        public static void main(String[] args) throws IOException
    {
        System.out.println("Starting client...");
        SocketConnection conn = new SocketConnectionImpl("10.50.137.120", 5665);
        TextualInterface ti = new ConsoleTextualInterface();
        HumanInterfaceImplementationSide his = new HumanInterfaceImplementationSide(new HumanInterfaceConnectionImpl(conn), ti);

        his.run();
    }
    
}

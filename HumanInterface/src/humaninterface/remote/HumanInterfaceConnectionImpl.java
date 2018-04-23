/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

/**
 *
 * @author thomasthimothee
 */
import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;

/**
 *
 * @author thomasthimothee
 */
public class HumanInterfaceConnectionImpl implements HumanInterfaceConnection {

    private SocketConnection conn;

    public HumanInterfaceConnectionImpl(SocketConnection conn) {
        this.conn = conn;
    }

    @Override
    public void writeInt(int i) throws IOException {
        conn.writeInt(i);
    }

    @Override
    public void writeString(String str) throws IOException {
        conn.writeUTF(str);
    }

    @Override
    public int readInt() throws IOException {
        return conn.readInt();
    }

    @Override
    public String readString() throws IOException {
        return conn.readUTF();
    }

    @Override
    public void close() throws IOException {
        conn.close();
    }

    @Override
    public void writeStringArray(String[] array) throws IOException {
        conn.writeInt(array.length);
        for (int i = 0; i < array.length; ++i) {
            conn.writeUTF(array[i]);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import java.io.Closeable;
import java.io.IOException;
/**
 *
 * @author thomasthimothee
 */
public interface HumanInterfaceConnection extends Closeable {
        
    public void writeInt(int i) throws IOException;
    public void writeStringArray(String[] array) throws IOException;
    public void writeString(String str) throws IOException;
 
    public int readInt() throws IOException;
    public String readString() throws IOException;

    
}

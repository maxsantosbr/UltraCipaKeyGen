package util;

import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import javax.swing.JOptionPane;

public class HWID {

    public static String get() {
        try {
            StringBuilder sb = new StringBuilder();

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

            while (nets.hasMoreElements()) {
                NetworkInterface ni = nets.nextElement();
                byte[] mac = ni.getHardwareAddress();

                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                }
            }

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sb.toString().getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();

            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }

            return hex.toString();

        } catch (UnsupportedEncodingException | SocketException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null,"HWID DESCONHECIDA: " + e);
            return "UNKNOWN_HWID";            
        }
    }
}
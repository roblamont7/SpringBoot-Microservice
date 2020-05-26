package com.springmicroservice.loginservice.utils;

import com.springmicroservice.loginservice.constants.PatternConstants;
import com.springmicroservice.loginservice.dto.NetworkResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.NetworkChannel;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkUtils {

    // set of functions to get the network information - ip and mac address of client and server

    public static Function<String, String> getClientMacAddress = (ip) -> {
        Pattern macpt = null;
        // find os and set command according to os
        String os = System.getProperty("os.name").toLowerCase();
        String[] cmd;

        if (os.contains("win")) {
            // windows
            macpt = Pattern.compile(PatternConstants.NetworkConstants.PATTERN_REGEX_FOR_WINDOWS);
            String[] a = {PatternConstants.NetworkConstants.ARP, PatternConstants.NetworkConstants.ARP_A, ip};
            cmd = a;
        } else {
            // not windows os
            macpt = Pattern.compile(PatternConstants.NetworkConstants.PATTERN_REGEX_FOR_MAC_OR_LINUX);
            String[] a = {PatternConstants.NetworkConstants.ARP, ip};
            cmd = a;
        }

        BufferedReader reader = null;

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            System.out.println(p.getInputStream());
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                Matcher m = macpt.matcher(line);
                if (m.find()) {
                    return m.group();
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // return empty if no mac address found
        return "";
    };

    public static Function<NetworkResponseDTO, NetworkResponseDTO> getLocalHostAddress = (network) -> {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (interfaces.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (InetAddress.class == addr.getClass()) {
                        network.setIpAddress(addr.getHostAddress());
                    }
                    if (Objects.isNull(network.getMacAddress())) {
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(addr);
                        byte[] mac = networkInterface.getHardwareAddress();

                        // convert mac decimal to hex format
                        for (int i = 0; i< mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            network.setMacAddress(sb.toString());
                        }
                    }
                }
            }
            return network;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    };

    public static Function<HttpServletRequest, NetworkResponseDTO> getDeviceAddress = (request) -> {
        NetworkResponseDTO network = new NetworkResponseDTO();
        String ipAddress = request.getRemoteAddr();

        if (ipAddress.equals(PatternConstants.NetworkConstants.LOCALHOST_IPV6_ADDRESS)) {
            getLocalHostAddress.apply(network);
        } else {
            network.setIpAddress(ipAddress);
            network.setMacAddress(getClientMacAddress.apply(network.getIpAddress()));
        }
        return network;
    };
}

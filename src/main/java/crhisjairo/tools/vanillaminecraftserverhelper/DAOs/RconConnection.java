package crhisjairo.tools.vanillaminecraftserverhelper.DAOs;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;

import java.time.Duration;

public final class RconConnection {
    private static MinecraftRcon minecraftRcon;

    public static RconConnectionStatus connect(String ip, String port, String pwd) {
        if(RconConnection.minecraftRcon != null) {
            System.err.println("Error: Already connected to a RCON server.");
            return RconConnectionStatus.ALREADY_CONNECTED;
        }

        RconDetails details = createRconDetails(ip, port, pwd);

        if(details == null) {
            System.err.println("Error: Connection details not valid.");
            return RconConnectionStatus.CONNECTION_DETAILS_INVALID;
        }

        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                details,
                ConnectOptions.defaults());

        try {
            minecraftRconService.connectBlocking(Duration.ofSeconds(5));

            RconConnection.minecraftRcon = minecraftRconService.minecraftRcon()
                    .orElseThrow(IllegalStateException::new);
        } catch (IllegalStateException e) {
            System.err.println("Error connecting. Check ip or password.");
            return RconConnectionStatus.CONNECTION_ERROR;
        }

        System.out.println("Connected!");
        return RconConnectionStatus.CONNECTED;
    }

    private static RconDetails createRconDetails(String ip, String port, String pwd) {
        if(pwd.isEmpty()){
            System.err.println("Error: Password cannot be empty!");

            return null;
        }

        if(ip.isEmpty()) {
            System.out.println("IP missing. Using 'localhost'.");
            ip = "localhost";
        }

        int portInt = 25575;
        try {
            if(!port.isEmpty())
                portInt = Integer.parseInt(port);

        } catch (NumberFormatException e) {
            System.err.println("Error: Port must be a number!");
            System.err.println("Using default port 25575.");
        }

        return new RconDetails(ip, portInt, pwd);
    }

    public static MinecraftRcon getMinecraftRcon() {
        return minecraftRcon;
    }

    public enum RconConnectionStatus {
        CONNECTED,
        ALREADY_CONNECTED,
        CONNECTION_DETAILS_INVALID,
        CONNECTION_ERROR
    }
}

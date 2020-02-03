package com.kuraserver.serverlist.information;

public class ServerDetails {

    private String name;

    private String text;
    private String ip;
    private int port = 19132;
    private String version;
    private int player_max;
    private int player_current;
    private int ping;
    private String owner;

    public ServerDetails(String name, String text, String ip, int port, String version, int player_max, int player_current, int ping, String owner){
        this.name = name;
        this.text = text;
        this.ip = ip;
        this.port = port;
        this.version = version;
        this.player_max = player_max;
        this.player_current = player_current;
        this.ping = ping;
        this.owner = owner;
    }

    public String getName(){
        return this.name;
    }

    public String getText() {
        return text;
    }

    public String getIp(){
        return this.ip;
    }

    public int getPort() {
        return port;
    }

    public String getVersion() {
        return version;
    }

    public int getMaxPlayers() {
        return player_max;
    }

    public int getCurrentPlayers() {
        return player_current;
    }

    public int getPing() {
        return ping;
    }

    public String getOwner() {
        return owner;
    }

}

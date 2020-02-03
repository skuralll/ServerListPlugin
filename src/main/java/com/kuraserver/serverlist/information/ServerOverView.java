package com.kuraserver.serverlist.information;

public class ServerOverView {

    private String name;
    private String tip = "";
    private String bodyLink;

    public ServerOverView(String name, String bodyLink){
        this.name = name;
        this.bodyLink = bodyLink;
    }

    public String getName(){
        return this.name;
    }

    public void setTip(String tip){
        this.tip = tip;
    }

    public String getTip(){
        return this.tip;
    }

    public String getBodyLink(){
        return this.bodyLink;
    }

}

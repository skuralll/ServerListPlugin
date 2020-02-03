package com.kuraserver.serverlist.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import com.kuraserver.serverlist.ServerListAPI;
import com.kuraserver.serverlist.information.ServerDetails;

import java.net.InetSocketAddress;

public class ServerDetailsForm extends FormWindowModal implements FormInterface {

    private transient String name;

    private transient String ip;
    private transient int port;

    public ServerDetailsForm(String bodyLink) {
        super("サーバーリスト", "", "このサーバーへ行く", "戻る");
        ServerDetails details = ServerListAPI.getDetail(bodyLink);

        this.name = details.getName();

        this.ip = details.getIp();
        this.port = details.getPort();

        if(this.ip.equals("(非公開)")){
            this.setButton1("§7アドレスが非公開です");
        }

        this.setTitle(details.getName());

        String text = "";

        text += "§l§a【サーバー説明】§r§f\n";
        text += details.getText() + "...\n\n";

        text += "§l§a【情報】§r§f\n";
        text += "§lIP§r§f : " + details.getIp() + "\n";
        text += "§lPort§r§f : " + details.getPort() + "\n";
        text += "§l接続人数§r§f : " + details.getCurrentPlayers() + " / " + details.getMaxPlayers() + "\n";
        text += "§lPing§r§f : " + details.getPing() + "ms\n";
        text += "§lオーナー§r§f : " + details.getOwner() + "\n";

        this.setContent(text);
    }

    @Override
    public void handleResponse(Player player, FormResponse response) {
        if(((FormResponseModal)response).getClickedButtonId() == 0){
            if(this.ip.equals("(非公開)")){
                player.showFormWindow(this);
            } else{
                player.transfer(new InetSocketAddress(this.ip, this.port));
                Server.getInstance().getLogger().info(player.getName() +  "が" + this.name + "へ移動しました");
            }
        }
        else{
            ServerListAPI.showServerList(player);
        }
    }

}

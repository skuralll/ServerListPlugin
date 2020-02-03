package com.kuraserver.serverlist.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;

public interface FormInterface {

    abstract public void handleResponse(Player player, FormResponse response);

}

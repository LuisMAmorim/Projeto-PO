package woo.app.clients;

import java.util.List;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Client;
import woo.core.Notification;

import woo.core.exception.UnknownClientException;
import woo.app.exception.UnknownClientKeyException;

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  private Input<String> _clientKey;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
    	Client cl = _receiver.getClient(_clientKey.value());
      List<Notification> notifs = cl.getNotifications();

    	_display.addLine(cl.toString());
      for (Notification n : notifs) _display.addLine(n.toString());
      _display.display();
    }
    catch (UnknownClientException x) {
    	throw new UnknownClientKeyException(_clientKey.value());
    }
  }

}

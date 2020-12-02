package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.Client;
import woo.core.exception.UnknownClientException;
import woo.app.exception.UnknownClientKeyException;
import java.util.List;

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

    	String cl = _receiver.getClientInfo(_clientKey.value());
      List<String> notifs = _receiver.getClientNotifInfo(_clientKey.value());

    	_display.addLine(cl);
      for (String n : notifs) _display.addLine(n);
      _display.display();
    }
    catch (UnknownClientException x) {
    	throw new UnknownClientKeyException(_clientKey.value());
    }
  }

}

package woo.app.clients;

import java.util.List;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException; 
import pt.tecnico.po.ui.Input; 
import woo.core.StoreManager;  
import woo.core.Client;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<StoreManager> {

  public DoShowAllClients(StoreManager storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    List<Client> clients = _receiver.getAllClients();

    for (Client cl : clients) _display.addLine(cl.toString());
    _display.display();
  }
}

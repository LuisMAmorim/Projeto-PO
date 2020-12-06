package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.Client;
import woo.core.Transaction;

import woo.core.exception.UnknownClientException;
import woo.app.exception.UnknownClientKeyException;
import java.util.List;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<StoreManager> {

  private Input<String> _clientKey;

  public DoShowClientTransactions(StoreManager storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      Client cl = _receiver.getClient(_clientKey.value());
      List<Transaction> transactions = cl.getTransactions();

      for (Transaction t : transactions) _display.addLine(t.toString());
      _display.display();
    }
      catch (UnknownClientException x) {
      throw new UnknownClientKeyException(_clientKey.value());
    }
  }

}

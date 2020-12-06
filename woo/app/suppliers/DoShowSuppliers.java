package woo.app.suppliers;

import java.util.List;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.Supplier;
import woo.app.suppliers.Message;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {

  public DoShowSuppliers(StoreManager receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    List<Supplier> suppliers = _receiver.getSuppliers();

    for (Supplier s : suppliers) {
      String enabled = s.isEnabled() ? Message.yes() : Message.no();
    	_display.addLine(s.toString() + "|" + enabled);
    }

    _display.display();
  }
}

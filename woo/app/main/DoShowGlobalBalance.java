package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
//FIXME import other classes

/**
 * Show global balance.
 */
public class DoShowGlobalBalance extends Command<StoreManager> {

  public DoShowGlobalBalance(StoreManager receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() {
    _display.popup(Message.currentBalance(
    	Math.round(_receiver.getAvailableBalance()),
    	Math.round(_receiver.getAccountingBalance())    	
    ));
  }
}

package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.app.exception.InvalidDateException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  
  Input<Integer> numDays = _form.addIntegerInput(Message.requestDaysToAdvance());

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    //FIXME init input fields
  }

  @Override
  public final void execute() throws DialogException {
    //FIXME implement command
  }
}

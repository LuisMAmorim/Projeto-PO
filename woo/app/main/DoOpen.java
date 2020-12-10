package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import java.io.IOException;
import woo.core.StoreManager;

import woo.core.exception.UnavailableFileException;
import woo.app.exception.FileOpenFailedException;

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<StoreManager> {

  private Input<String> _fileName;

  /** @param receiver */
  public DoOpen(StoreManager receiver) {
    super(Label.OPEN, receiver);
    _fileName = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    
    try {
      _receiver.load(_fileName.value());
    }
    catch (UnavailableFileException x) {
      throw new FileOpenFailedException(_fileName.value());
    }
  }

}

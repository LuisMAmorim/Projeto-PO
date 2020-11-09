package woo.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import woo.core.exception.BadEntryException;
import woo.core.Store;
import woo.core.Box;
import woo.core.Container;


public class MyParser {
  private Store _store;  // ou outra entidade

  MyParser(Store s) {
    _store = s;
  }

  void parseFile(String fileName) throws IOException, BadEntryException /* may have other exceptions */ {

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      
      while ((line = reader.readLine()) != null)
        parseLine(line);
    } 
  }

  private void parseLine(String line) throws BadEntryException {
    String[] components = line.split("\\|");

    switch(components[0]) {
      case "SUPPLIER":
        parseSupplier(line, components);
        break;

      case "CLIENT":
        parseClient(line, components);
        break;

      case "BOX":
        parseBox(line, components);
        break;

      case "CONTAINER":
        parseContainer(line, components);
        break;

      case "BOOK":
        parseBook(line, components);
        break;

      default:
        throw new BadEntryException("Type of line not supported: " + line);
    }
  }

  // Format: SUPPLIER|id|nome|endereço
  private void parseSupplier(String line, String[] components)  throws BadEntryException {
    if (components.length != 4)
      throw new BadEntryException("Invalid number of fields in supplier description (4) " + line);

    String id = components[1];
    String name = components[2];
    String address = components[3];

    _store.registerSupplier(id, name, address);    
  }

  // Format: CLIENT|id|nome|endereço
  private void parseClient(String line, String[] components) throws BadEntryException {
    if (components.length != 4)
      throw new BadEntryException("Invalid number of fields (4) in client description: " + line);

    String id = components[1];
    String name = components[2];
    String address = components[3];

    _store.registerClient(id, name, address);
  }

  public ServiceLevel parseServiceLevel(String s) {
    if (s.equals("NORMAL"))
      return ServiceLevel.NORMAL;

    if (s.equals("AIR"))
      return ServiceLevel.AIR;

    if (s.equals("EXPRESS"))
      return ServiceLevel.EXPRESS;

    if (s.equals("BY_HAND"))
      return ServiceLevel.BY_HAND;

    throw new BadEntryException("Invalid service type");
  }

  public ServiceQuality parseServiceQuality(String s) {
    if (s.equals("B4"))
      return ServiceQuality.B4;

    if (s.equals("C4"))
      return ServiceQuality.C4;

    if (s.equals("C5"))
      return ServiceQuality.C5;

    if (s.equals("DL"))
      return ServiceQuality.DL;

    throw new BadEntryException("Invalid service level");
  }

  // Format: BOX|id|tipo-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
  private void parseBox(String line, String[] components) throws BadEntryException {
    if (components.length != 7)
      throw new BadEntryException("wrongr number of fields in box description  (7) " + line);

    String id = components[1];
    ServiceLevel serviceLevel = parseServiceLevel(components[2]);
    String supplierId = components[3];
    int price = Integer.parseInt(components[4]);
    int crit = Integer.parseInt(components[5]);
    int q = Integer.parseInt(components[6]);

    _store.registerBox(id, serviceLevel, supplierId, price, crit, q);
  }

  // Format: BOOK|id|título|autor|isbn|id-fornecedor|preço|valor-crítico|exemplares
  private void parseBook(String line, String[] components) throws BadEntryException {
    if (components.length != 9)
      throw new BadEntryException("Invalid number of fields (9) in box description: " + line);

    String id = components[1];
    String title = components[2];
    String author = components[3];
    String isbn = components[4];
    String supplierId = components[5];
    int price = Integer.parseInt(components[6]);
    int crit = Integer.parseInt(components[7]);
    int q = Integer.parseInt(components[8]);

    _store.registerBook(id, title, author, isbn, supplierId, price, crit, q);
  }

  // Format: CONTAINER|id|tipo-de-serviço|nível-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
  private void parseContainer(String line, String[] components) throws BadEntryException {
    if (components.length != 8)
      throw new BadEntryException("Invalid number of fields (8) in container description: " + line);

    String id = components[1];
    ServiceLevel serviceLevel = parseServiceLevel(components[2]);
    ServiceQuality serviceQuality = parseServiceQuality(components[3]);
    String supplierId = components[4];
    int price = Integer.parseInt(components[5]);
    int crit = Integer.parseInt(components[6]);
    int q = Integer.parseInt(components[7]);

    _store.registerContainer(id, serviceLevel, serviceQuality, supplierId, price, crit, q);
  }
}
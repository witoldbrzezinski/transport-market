package pl.witoldbrzezinski.transportmarket.load;

public class LoadNotFoundException extends RuntimeException {

  public LoadNotFoundException(Long id) {
    super(String.format("Load with id %d not found", id));
  }
}

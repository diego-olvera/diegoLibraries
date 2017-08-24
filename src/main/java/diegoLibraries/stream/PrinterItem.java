package diegoLibraries.stream;

public class PrinterItem {
	private Printer printer;

	public PrinterItem() {
		this(System.out::println);
	}
	public PrinterItem(Printer printer) {
		this.printer = printer;
	}

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	public void println(Object o) {
		if(printer!=null)printer.println(o);
	}
	public void print(Object o) {
		if(printer!=null)printer.println(o);
	}
}

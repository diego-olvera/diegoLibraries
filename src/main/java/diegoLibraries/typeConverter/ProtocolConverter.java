package diegoLibraries.typeConverter;

import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;

public class ProtocolConverter {
	
	public static String fromJsonToXml(String json) {
		return XML.toString(new JSONObject(json));
	}
	public static String fromXmlToJson(String xml) {
		return XML.toJSONObject(xml).toString();
	}
	public static void usingJsonOrg() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	             "<errors>\n" +
	             "  <error>\n" +
	             "    <status>400</status>\n" +
	             "    <message>The field 'quantity' is invalid.</message>\n" +
	             "    <details>\n" +
	             "      <invalid_reason>The quantity specified is greater than the quantity of the product that is available to ship.</invalid_reason>\n" +
	             "      <available_quantity>0</available_quantity>\n" +
	             "      <order_product_id>12525</order_product_id>\n" +
	             "    </details>\n" +
	             "  </error>\n" +
	             "</errors>";

		String json = fromXmlToJson(xml);
		System.out.println(json);
		System.out.println(fromJsonToXml(json));
	}
	public static void main(String[] args) throws IOException {
		usingJsonOrg();
	}
}

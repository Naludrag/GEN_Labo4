package ch.heigvd.gen2019.order;

import ch.heigvd.gen2019.Formattable;
import ch.heigvd.gen2019.product.*;

public class OrdersWriter {
    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuffer sb = new StringBuffer("{\"orders\": [");

        for (int i = 0; i < orders.getOrdersCount(); i++) {
            sb.append(formatOrder(orders.getOrder(i)));
        }

        if (orders.getOrdersCount() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append("]}").toString();
    }

    private String formatOrder(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append(Formattable.formatField("id", order.getOrderId()));
        sb.append(", ");
        sb.append("\"products\": [");
        for (int j = 0; j < order.getProductsCount(); j++) {
            sb.append(getFormatProduct(order.getProduct(j)));
        }

        if (order.getProductsCount() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]");
        sb.append("}, ");
        return sb.toString();
    }

    private String getFormatProduct(Product product) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append(Formattable.formatField("code", product.getCode()));
        sb.append(", ");
        sb.append(Formattable.formatField("color", product.getColor().toString()));
        sb.append(", ");

        if (product.getSize() != ProductSize.NOT_APPLICABLE) {
            sb.append(Formattable.formatField("size", product.getSize().toString()));
            sb.append(", ");
        }

        sb.append(Formattable.formatField("price", product.getPrice()));
        sb.append(", ");
        sb.append(Formattable.formatField("currency", product.getCurrency()));
        sb.append("}, ");
        return sb.toString();
    }
}
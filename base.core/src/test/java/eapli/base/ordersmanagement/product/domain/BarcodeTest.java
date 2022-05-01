package eapli.base.ordersmanagement.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarcodeTest {

    @Test
    void getBarcode() {
        Barcode barcode = new Barcode("1234567890123");
        Assertions.assertEquals("1234567890123", barcode.getBarcode());
    }

    @Test
    void valueOf() {
        Barcode barcode = new Barcode("1234567890123");
        Assertions.assertEquals("1234567890123", barcode.getBarcode());
    }
}
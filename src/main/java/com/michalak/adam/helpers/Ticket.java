package com.michalak.adam.helpers;

/**
 * This enum contains all tickets available for sale. Those are official public transportation tickets taken from:
 * http://www.mpk.krakow.pl/pl/bilety2/rodzaje-biletow/
 * Not all tickets are available for the project's simplicity sake.
 * Tickets have different validation and price. They can be reduced or normal and can grant permission to travel
 * to further suburbs (zone II).
 */
public enum Ticket {
    TWENTYREDUCEDZONEI(20, 1.40, 1), TWENTYZONEI(20, 2.80, 1), FOURTYREDUCEDZONEI(40, 1.90, 1), FOURTYZONEI(40, 3.80, 1),
    SIXTYREDUCEDZONEI(60, 2.50, 1), SIXTYZONEI(60, 5.00, 1), NINETYREDUCEDZONEI(90, 3.00, 1),
    NINETYZONEI(90, 6.00, 1), RIDEREDUCEDZONEII(1, 2.00, 2), RIDEZONEII(1, 4.00, 2),
    SIXTYREDUCEDZONEII(60, 2.50, 2), SIXTYZONEII(60, 5.00, 2), NINETYREDUCEDZONEII(90, 3.00, 2),
    NINETYZONEII(90, 6.00, 2);

    private int validation;
    private double price;//in polish zlotych
    private int zone; //1 is first zone, 2 is second zone

    Ticket(int validation, double price, int zone){
        this.validation = validation;
        this.price = price;
        this.zone = zone;
    }

    public String toString(){
        String out = "+---------------------------+\n";
        out += "  Strefa "+(zone == 1 ? "I Miasto Kraków" : "I + II Aglomeracja");
        out += "\n  ";
        if (validation == 1)
            out += "1-przejazdowy";
        else if (validation == 40)
            out += this.validation + " minut lub 1-przejazdowy";
        else
            out += this.validation + " minut";
        out += "\n  cena: ";
        out += this.price + "zł";
        out += "\n+---------------------------+\n";
        return out;
    }

    public int getValidation(){
        return this.validation;
    }
    public double getPrice(){
        return this.price;
    }
    public int getZone(){
        return this.zone;
    }

}
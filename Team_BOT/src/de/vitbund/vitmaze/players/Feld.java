package de.vitbund.vitmaze.players;

public class Feld {

     private String typ;
     private String status;
     private boolean gesehen;
     private boolean betreten;
     private int zaehlerBetreten;
     
     public Feld(String typ, String status, boolean gesehen, boolean betreten, int zaehlerBetreten) {
           this.typ = typ;
           this.status = status;
           this.gesehen = gesehen;
           this.betreten = betreten;
           this.zaehlerBetreten = zaehlerBetreten;
     }
     
     
     public String getTyp() {
           return typ;
     }
     public void setTyp(String typ) {
           this.typ = typ;
     }
     
     
     public String getStatus() {
           return status;
     }


     public void setStatus(String status) {
           this.status = status;
     }


     public boolean isGesehen() {
           return gesehen;
     }
     public void setGesehen(boolean gesehen) {
           this.gesehen = gesehen;
     }
     public boolean isBetreten() {
           return betreten;
     }
     public void setBetreten(boolean betreten) {
           this.betreten = betreten;
     }
     public int getZaehlerBetreten() {
           return zaehlerBetreten;
     }
     public void setZaehlerBetreten(int zaehlerBetreten) {
           this.zaehlerBetreten = zaehlerBetreten;
     }

     
     public void feldBetreten() {
           /**
           * TODO
           */
//         if 
           
     }
     
}


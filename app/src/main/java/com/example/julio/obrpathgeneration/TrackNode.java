package com.example.julio.obrpathgeneration;

/**
 * Created by Julio on 14/08/2015.
 */
public class TrackNode {
    public double posx;
    public double posy;

    public TrackNode(double posx, double posy){
        this.posx = posx;
        this.posy = posy;

    }

    public String toString(){
        return "Track Node <posx: " + posx + ", posy: " + posy + ">";
    }
}

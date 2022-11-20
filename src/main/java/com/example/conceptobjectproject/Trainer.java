package com.example.conceptobjectproject;

import Enums.ZoneTypes;

import java.util.ArrayList;

public class Trainer extends SimulationObject {

    public String Name;
    protected final Map _map;
    protected final ZoneTypes _team;
    protected Tile _actualTile;

    public ArrayList<String> pokemons;

    public Trainer(ZoneTypes zoneType) {
        _map = Map.GetInstance();
        _team = zoneType;
        pokemons = new ArrayList<>();
    }

    public boolean isEnnemy(Trainer otherTrainer) {
        switch (objectType) {
            case SafeZoneTeam1:
            case SafeZoneTeam2:
                return otherTrainer.objectType != ZoneTypes.SafeZoneTeam2
                        && otherTrainer.objectType != ZoneTypes.SafeZoneTeam1;

            case SafeZoneTeam3:
            case SafeZoneTeam4:
                return otherTrainer.objectType != ZoneTypes.SafeZoneTeam3
                        && otherTrainer.objectType != ZoneTypes.SafeZoneTeam4;

            default:
                return false;
        }

    }

    protected void TakePokemon(Trainer other, int amount, boolean delete) {
        // print message in red color
        if (this instanceof MasterTrainer) {
            System.out.println("\u001B[1m");
        }
        // if name contains "fire" then print in red color
        if (this.Name.contains("Fire")) {
            System.out.println("\u001B[31m" + this.Name + " has taken " + amount + " pokemons from "
                    + other.Name);
        }
        // if name contains "water" then print in blue color
        else if (this.Name.contains("Water")) {
            System.out.println("\u001B[34m" + this.Name + " has taken " + amount + " pokemons from "
                    + other.Name);
        }
        // if name contains "plant" then print in green color
        else if (this.Name.contains("Plant")) {
            System.out.println("\u001B[32m" + this.Name + " has taken " + amount + " pokemons from "
                    + other.Name);
        }
        // if name contains "electric" then print in yellow color
        else if (this.Name.contains("Electric")) {
            System.out.println("\u001B[33m" + this.Name + " has taken " + amount + " pokemons from "
                    + other.Name);
        } else {
            System.out.println(this.Name + " has taken " + amount + " pokemons from " + other.Name);
        }

        // // if amount > 10 and Traner is a master trainer
        // if (amount > 30 && this instanceof MasterTrainer) {
        //     var message = this.Name + " win the game";
        //     System.out.println(
        //             "**************************************************************************************************");
        //     // print message and add stars at the start and end of the message to have a 30
        //     // caracters line
        //     var messageLength = message.length();
        //     var stars = "";
        //     for (int i = 0; i < 60 - messageLength; i++) {
        //         stars += "*";
        //     }
        //     System.out.println(stars + " " + message + " " + stars);
        //     System.out.println(
        //             "**************************************************************************************************");

        //     System.exit(0);
        // }

        if (this instanceof MasterTrainer) {
            System.out.println("\u001B[0m");
        }

        int count = 0;
        ArrayList<String> pokemons = new ArrayList<>(other.pokemons);

        for (var msg : pokemons) {
            if (!this.pokemons.contains(msg)) {
                this.pokemons.add(msg);

                if (delete)
                    other.pokemons.remove(msg);

                count++;
            }

            if (count >= amount)
                break;
        }
    }
}

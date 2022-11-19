package com.example.conceptobjectproject;

import Enums.ZoneTypes;

import java.util.ArrayList;

public class Trainer extends SimulationObject {

    public String Name;
    protected final Map _map;
    protected final ZoneTypes _team;
    protected Tile _actualTile;

    public ArrayList<String> pokemons;

    public Trainer(ZoneTypes zoneType){
        _map = Map.GetInstance();
        _team = zoneType;
        pokemons = new ArrayList<>();
    }

    public boolean isEnnemy(Trainer otherTrainer)
    {
        switch (objectType)
        {
            case SafeZoneTeam1:
            case SafeZoneTeam2:
                return otherTrainer.objectType != ZoneTypes.SafeZoneTeam2 && otherTrainer.objectType != ZoneTypes.SafeZoneTeam1;

            case SafeZoneTeam3:
            case SafeZoneTeam4:
                return otherTrainer.objectType != ZoneTypes.SafeZoneTeam3 && otherTrainer.objectType != ZoneTypes.SafeZoneTeam4;

            default:
                return false;
        }

    }
    protected void TakePokemon(Trainer other,int amount, boolean delete)
    {
        System.out.println(this.Name +" take "+ amount+ " pokemons from "+ other.Name);
        int count =0;
        ArrayList<String> pokemons = new ArrayList<>(other.pokemons);

        for (var msg: pokemons) {
            if(!this.pokemons.contains(msg))
            {
                this.pokemons.add(msg);

                if(delete)
                    other.pokemons.remove(msg);

                count++;
            }

            if(count >= amount)
                break;
        }
    }


}

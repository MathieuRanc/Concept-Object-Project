package com.example.conceptobjectproject;
import Enums.Direction;

import Enums.ZoneTypes;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Collections;
import java.util.Random;

public class CommonTrainer extends Trainer
{
    private final MasterTrainer _master;
    private final float _maxEnergyPoints = 100;
    private final float _energyPointspLosingStep = 1;
    private float _energyPoints ;

    public CommonTrainer(String name, MasterTrainer master , ZoneTypes zoneType, String[] initpokemons)
    {
        super(zoneType);

        Collections.addAll(pokemons, initpokemons);

        Text t = new Text("I");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.objectColor);
        graphObj = t;
        objectType = zoneType;

        Name=name;
        _energyPoints = _maxEnergyPoints;
        _master = master;
        _actualTile = Map.GetInstance().GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this,zoneType);
    }
    void move()  {
        Random random = new Random();
        _actualTile.RemoveTileObject(this);
        Tile movementTile = _actualTile;
        Tile lastTile = _actualTile;


        //LetsMove
        int moveDist =random.nextInt(5)+1;
        boolean movementCanceled = false;
        for (int i = 0; i < moveDist; i++)
        {
            int overrideTest= 0;
            while(overrideTest<50)
            {
                overrideTest++;

                Direction rndDirection = _energyPoints/_maxEnergyPoints < 0.2f ? Direction.GetOrientedDirection(_map.GetFreeRandomMapTileOfType(objectType),movementTile):Direction.GetRandomDirection();
                Tile tileToTest = _map.GetDirectNeighbour(movementTile,rndDirection.relativPos);

                if(lastTile == tileToTest || tileToTest == null) {
                    continue;
                }

                if(tileToTest.GetTileObject() == null)
                {
                    if(tileToTest.zoneType == ZoneTypes.Neutral || tileToTest.zoneType == objectType)
                    {
                        lastTile = movementTile;

                        movementTile = tileToTest;
                    }
                    else
                    {
                        movementCanceled = true;
                    }

                }
                else
                {
                    movementCanceled = true ;
                    
                    if (tileToTest.GetTileObject().objectType != ZoneTypes.Obstacle) 
                    {
                        var meetedTrainer = (Trainer) tileToTest.GetTileObject();
                        if (meetedTrainer.isEnnemy(this))//EnnemyTeam
                        {
                            //Combat si ennemie
                            if (random.nextBoolean() && meetedTrainer.pokemons.size() > 0) // test if win battle
                            {   // if win : you win a random number of pokemons from your opponent
                                int amount = meetedTrainer.pokemons.size() == 1 ? 1 : random.nextInt(meetedTrainer.pokemons.size() - 1) + 1;
                                TakePokemon(meetedTrainer, amount, true);
                            } else if (this.pokemons.size() > 0)// if loose : your opponent win a random number of pokemons
                            {
                                int amount = this.pokemons.size() == 1 ? 1 : random.nextInt(this.pokemons.size() - 1) + 1;
                                meetedTrainer.TakePokemon(this, amount, true);
                            }
                        } else //equipe alliée
                        {
                            if (meetedTrainer instanceof MasterTrainer) { //isMaster
                                meetedTrainer.TakePokemon(this, pokemons.size(), false);
                            } else //isCommon
                            {
                                int randomNbrOfpoke = random.nextInt(Math.min(pokemons.size(), meetedTrainer.pokemons.size())+1);
                                meetedTrainer.TakePokemon(this, meetedTrainer.objectType == this.objectType ? pokemons.size() : randomNbrOfpoke, false);
                                TakePokemon(meetedTrainer, meetedTrainer.objectType == this.objectType ? meetedTrainer.pokemons.size() : randomNbrOfpoke, false);
                            }
                        }
                    }
                }
                break;
            }

            if(movementTile.zoneType == objectType)
                IncreaseEP();

            if(movementCanceled)
            {
                DecreaseEP(moveDist - i);
                if(noMoreEnergy(movementTile))
                {
                    return;
                }
                break;
            }

            DecreaseEP(1);
            if(noMoreEnergy(movementTile))
            {
                return;
            }
        }

        //movement done
        _actualTile = movementTile;
        _actualTile.SetTileObject(this, objectType);
    }

    private void IncreaseEP()
    {
        if(_energyPoints <= (_maxEnergyPoints - (5)*_energyPointspLosingStep))
            _energyPoints += 5 *_energyPointspLosingStep;
    }

    private void DecreaseEP(int amount)
    {
        _energyPoints -= amount*_energyPointspLosingStep;
    }

    private boolean noMoreEnergy(Tile actualtile)
    {
        if(_energyPoints < 0)
        {
            _master.ChangeCommonTrainerToObstacle(this, actualtile);
            return true;
        }
        return false;
    }

}

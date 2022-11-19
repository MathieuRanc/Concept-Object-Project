package com.example.conceptobjectproject;
import Enums.Direction;

import Enums.ZoneTypes;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class CommonBeing extends Being {

    private int energyPoints = 100;
    private int maxNumberOfMessages = 20;
    private int numberOfMessages = 0;
    private int x = 0;
    private int y = 0;
    private Direction direction;
    private MasterBeing _master;

    private float _maxEnergyPoints = 100;
    private float _energyPointspLosingStep = 1;
    private float _energyPoints ;

    public CommonBeing(String name, MasterBeing master , Map map, ZoneTypes zoneType, String initMessage)
    {
        super(map,zoneType);
        messages.add(initMessage);

        Text t = new Text("I");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.objectColor);
        graphObj = t;
        objectType = zoneType;

        Name=name;
        _energyPoints = _maxEnergyPoints;
        _master = master;
        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
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
                        DecreaseEP(moveDist - i);
                        movementCanceled = true;
                    }

                }
                else
                {
                    movementCanceled = true ;
                    switch(tileToTest.GetTileObject().objectType)
                    {
                        case Obstacle:
                            DecreaseEP(moveDist - i);
                            break;
                        default:
                            var meetedBeing = (Being)tileToTest.GetTileObject();
                            if(meetedBeing.isEnnemy(this))//EnnemyTeam
                            {
                                //Combat si ennemie

                                if(random.nextBoolean())
                                {   // you win a random number of messages from your opponent
                                    for( int j = 0; j < new Random().nextInt(meetedBeing.messages.size())+1; j++)
                                    {
                                        if(messages.size() < maxNumberOfMessages)
                                        {
                                            messages.add(meetedBeing.messages.get(j));
                                        }
                                    }
                                    removeDuplicates(messages);
                                    DecreaseEP(moveDist - i);
                                }
                                else
                                {
                                    lastTile = movementTile;
                                    movementTile = tileToTest;
                                }
                            }
                            else //equipe alliée
                                {
                                    if (meetedBeing instanceof MasterBeing) { //isMaster
                                        MasterBeing masterBeing = (MasterBeing) meetedBeing;
                                                                        //isMaster
                                        masterBeing.messages.addAll(messages);
                                        removeDuplicates(masterBeing.messages);
                                        messages.clear();
                                    }
                                    else //isCommon
                                    {
                                        CommonBeing commonBeing = (CommonBeing) meetedBeing;
                                        messages = commonBeing.ExchangeMessages(this, meetedBeing.objectType == this.objectType? 0 : random.nextInt(2));
                                    }
                                }
                            break;
                    }
                }
                break;
            }

            if(movementTile.zoneType == objectType)
                IncreaseEP(5);

            if(movementCanceled)
            {
                if(!stillEnergy(movementTile))
                {
                    return;
                }
                break;
            }

            DecreaseEP(1);
            if(!stillEnergy(movementTile))
            {
                return;
            }
        }

        //movement done
        _actualTile = movementTile;
        _actualTile.SetTileObject(this, objectType);
    }

    private void removeDuplicates(ArrayList<String> messages) {
        for (int i = 0; i < messages.size(); i++) {
            for (int j = i+1; j < messages.size(); j++) {
                if(messages.get(i).equals(messages.get(j)))
                {
                    messages.remove(j);
                }
            }
        }
    }

    private void IncreaseEP(int amount)
    {
        if(_energyPoints <= (_maxEnergyPoints - (amount)*_energyPointspLosingStep))
            _energyPoints += amount*_energyPointspLosingStep;
    }

    private void DecreaseEP(int amount)
    {
        _energyPoints -= amount*_energyPointspLosingStep;
    }

    private boolean stillEnergy(Tile actualtile)
    {
        if(_energyPoints < 0)
        {
            _master.ChangeCommonBeingToObstacle(this, actualtile);
            return false;
        }
        return true;
    }
    private ArrayList<String> ExchangeMessages(CommonBeing other, int amount)
    {
        Random random = new Random();

        if(amount >  0) //0n echange un certain nombre de message
        {
            int countSelf =0;
            int countOther =0;
            while(countSelf<amount && countOther<amount)
            {
                if(countSelf < amount)
                {
                    String newMessage = other.messages.get(random.nextInt(other.messages.size()));
                    if(!this.messages.contains(newMessage))
                    {
                        this.messages.add(newMessage);
                        countSelf++;
                    }
                }
                else if(countOther <amount)
                {
                    String newMessage = this.messages.get(random.nextInt(this.messages.size()));
                    if(!other.messages.contains(newMessage))
                    {
                        other.messages.add(newMessage);
                        countOther++;
                    }
                }
            }
        }
        else // On echange tout les messages
        {
            for(var message : other.messages) // for self
            {
                if(!this.messages.contains(message))
                {
                    this.messages.add(message);
                }
            }

            for(var message : this.messages) // for other
            {
                if(!other.messages.contains(message))
                {
                    other.messages.add(message);
                }
            }
        }

        amount = amount==0? other.messages.size():amount;
        System.out.println(other.Name+" meet "+ this.Name +" and exchange "+ amount+ " messages.");
        return other.messages;
    }
}

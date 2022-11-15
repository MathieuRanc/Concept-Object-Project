package com.example.conceptobjectproject;
import Enums.Direction;

import Enums.ZoneTypes;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class CommonBeing extends Being {
    private int energyPoints = 100;
    private int maxNumberOfMessages = 20;
    private int numberOfMessages = 0;
    private int x = 0;
    private int y = 0;
    private Team team;
    private Direction direction;

    private int _maxEnergyPoints = 100;
    private int _energyPointspLosingStep = 2;
    private int _energyPoints ;

    public CommonBeing(Map map, ZoneTypes zoneType)
    {
        super(map,zoneType);

        Text t = new Text("I");
        t.setFont(Font.font(null, FontWeight.BOLD, 20));
        t.setFill(zoneType.objectColor);
        graphObj = t;
        objectType = zoneType;

        _energyPoints = _maxEnergyPoints;

        _actualTile = map.GetFreeRandomMapTileOfType(zoneType);
        _actualTile.SetTileObject(this,zoneType);
    }

    Team getTeam() {
        return team;
    }

    

    void setTeam(Team team) {

    }
    boolean isEnemyTeam() {

        return true;
    }
    void move() {
        Random random = new Random();
        Tile movementTile = _actualTile;
        Tile lastTile = _actualTile;

        //LetsMove
        int moveDist =random.nextInt(5)+1;
        for (int i = 0; i < moveDist; i++)
        {
            while(true)
            {
                Direction rndDirection = Direction.GetRandomDirection();
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
                    }
                    break;
                }

                switch(tileToTest.GetTileObject().objectType)
                {
                    case Obstacle:
                        DecreaseEP(moveDist - i);
                        break;
                    default:
                        var meetedBeing = (Being)tileToTest.GetTileObject();
                        if(meetedBeing.isEnnemy(this))//EnnemyTeam
                        {
                            //fight
                            Random isWin = new Random();
                            if(isWin.nextBoolean())
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
                                messages = commonBeing.ExchangeMessages(messages, meetedBeing.objectType == this.objectType? 0 : random.nextInt(2));
                            }
                        }
                        break;
                }
                break;
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
    private void increaseEP()
    {}
    private void DecreaseEP(int amount)
    {
        _energyPoints -= (amount)*_energyPointspLosingStep;
    }
    private ArrayList<String> ExchangeMessages(ArrayList<String> messages, int amount)
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
                    String newMessage = messages.get(random.nextInt(messages.size()));
                    if(!this.messages.contains(newMessage))
                    {
                        this.messages.add(newMessage);
                        countSelf++;
                    }
                }
                else if(countOther <amount)
                {
                    String newMessage = this.messages.get(random.nextInt(this.messages.size()));
                    if(!messages.contains(newMessage))
                    {
                        messages.add(newMessage);
                        countOther++;
                    }
                }
            }
        }
        else // On echange tout les messages
        {
            for(var message : messages) // for self
            {
                if(!this.messages.contains(message))
                {
                    this.messages.add(message);
                }
            }

            for(var message : this.messages) // for self
            {
                if(!messages.contains(message))
                {
                    messages.add(message);
                }
            }
        }

        return messages;
    }
}

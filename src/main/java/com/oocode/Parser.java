package com.oocode;

import java.util.StringTokenizer;

public class Parser {
    private Robot robot;
    public Parser(Robot robot) {
        this.robot = robot;
    }

    public void parse(String input) {
        String[] rows = input.split("\\n");
        for(String row: rows) {
            parseRow(row);
        }
    }

    private void parseRow(String input) {
        String[] result = input.split("\\s");
        if(result[0].equals("move") && result[2].equals("onto"))
            robot.moveOnto(Integer.parseInt(result[1]), Integer.parseInt(result[3]));
        else if(result[0].equals("move") && result[2].equals("over"))
            robot.moveOver(Integer.parseInt(result[1]), Integer.parseInt(result[3]));
        else if(result[0].equals("pile") && result[2].equals("over"))
            robot.pileOver(Integer.parseInt(result[1]), Integer.parseInt(result[3]));
        else if(result[0].equals("pile") && result[2].equals("onto"))
            robot.pileOnto(Integer.parseInt(result[1]), Integer.parseInt(result[3]));
    }
}

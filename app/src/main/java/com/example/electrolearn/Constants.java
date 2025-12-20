package com.example.electrolearn;

import java.util.ArrayList;

public class Constants {

    public static ArrayList<QuestionModel> getQuestions() {
        ArrayList<QuestionModel> list = new ArrayList<>();

        list.add(new QuestionModel(
                "Which component is used to store electrical charge?",
                "Resistor", "Capacitor", "Diode", "Transistor",
                "Capacitor"
        ));

        list.add(new QuestionModel(
                "What is the unit of electrical resistance?",
                "Volt", "Ampere", "Ohm", "Watt",
                "Ohm"
        ));

        list.add(new QuestionModel(
                "Which tool is commonly used to join two metal pieces using a filler metal?",
                "Soldering Iron", "Multimeter", "Wire Stripper", "Oscilloscope",
                "Soldering Iron"
        ));

        list.add(new QuestionModel(
                "What does LED stand for?",
                "Light Emitting Diode", "Low Energy Device", "Linear Electric Drive", "Long Emission Data",
                "Light Emitting Diode"
        ));

        list.add(new QuestionModel(
                "Which device allows current to flow in only one direction?",
                "Resistor", "Switch", "Diode", "Fuse",
                "Diode"
        ));


        //copy paste it and paste to add new questions haha

        list.add(new QuestionModel(
                "What is the primary function of a transformer?",
                "To change DC to AC",
                "To increase or decrease AC voltage",
                "To store energy",
                "To stop current flow",
                "To increase or decrease AC voltage"
        ));

        // ADDING QUESTION #7
        list.add(new QuestionModel(
                "Which of these is an active component?",
                "Resistor",
                "Capacitor",
                "Inductor",
                "Transistor",
                "Transistor"
        ));



        return list;
    }
}
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

        return list;
    }
}
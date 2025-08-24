package com.solvd.project.utils;

import com.solvd.project.model.Witness;
import com.solvd.project.model.Adjuster;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InsuranceParser {

    public static List<Witness> parseWitnesses(InputStream xmlStream) throws XMLStreamException {
        List<Witness> witnesses = new ArrayList<>();
        Witness current = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(xmlStream);

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                String name = event.asStartElement().getName().getLocalPart();

                switch (name) {
                    case "Witness":
                        current = new Witness();
                        break;
                    case "idWitnesses":
                        current.setId(Integer.parseInt(reader.nextEvent().asCharacters().getData()));
                        break;
                    case "Contact_Info":
                        current.setContactInfo(reader.nextEvent().asCharacters().getData());
                        break;
                    case "Statement_Summary":
                        current.setStatementSummary(reader.nextEvent().asCharacters().getData());
                        break;
                }
            } else if (event.isEndElement() &&
                    event.asEndElement().getName().getLocalPart().equals("Witness")) {
                witnesses.add(current);
            }
        }

        return witnesses;
    }

    public static List<Adjuster> parseAdjusters(InputStream xmlStream) throws XMLStreamException {
        List<Adjuster> adjusters = new ArrayList<>();
        Adjuster current = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(xmlStream);

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                String name = event.asStartElement().getName().getLocalPart();

                switch (name) {
                    case "Adjuster":
                        current = new Adjuster();
                        break;
                    case "idAdjusters":
                        current.setId(Integer.parseInt(reader.nextEvent().asCharacters().getData()));
                        break;
                    case "Name":
                        current.setName(reader.nextEvent().asCharacters().getData());
                        break;
                    case "Contact":
                        current.setContact(reader.nextEvent().asCharacters().getData());
                        break;
                    case "Assigned_Case":
                        current.setAssignedCase(reader.nextEvent().asCharacters().getData());
                        break;
                }
            } else if (event.isEndElement() &&
                    event.asEndElement().getName().getLocalPart().equals("Adjuster")) {
                adjusters.add(current);
            }
        }

        return adjusters;
    }
}
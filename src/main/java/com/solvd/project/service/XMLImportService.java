package com.solvd.project.service;

import com.solvd.project.model.Witness;
import com.solvd.project.model.Adjuster;
import com.solvd.project.utils.InsuranceParser;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;
import java.util.List;

public class XMLImportService {

    public List<Witness> loadWitnesses(InputStream xmlStream) {
        try {
            return InsuranceParser.parseWitnesses(xmlStream);
        } catch (XMLStreamException e) {
            System.err.println("❌ Error parsing Witnesses XML: " + e.getMessage());
            return List.of();
        }
    }

    public List<Adjuster> loadAdjusters(InputStream xmlStream) {
        try {
            return InsuranceParser.parseAdjusters(xmlStream);
        } catch (XMLStreamException e) {
            System.err.println("❌ Error parsing Adjusters XML: " + e.getMessage());
            return List.of();
        }
    }
}